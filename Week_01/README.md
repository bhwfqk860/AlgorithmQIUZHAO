# 学习笔记

## 时间复杂度

| 数据结构 | 查询      | 插入      | 删除      |
| -------- | --------- | --------- | --------- |
| 数组     | O(n)      | O(n)      | O(n)      |
| 栈       | O(n)      | O(1)      | O(1)      |
| 队列     | O(n)      | O(1)      | O(1)      |
| 链表     | O(n)      | O(1)      | O(1)      |
| 跳表     | O(log(n)) | O(log(n)) | O(log(n)) |
| 哈希表   | O(1)      | O(1)      | O(1)      |

## HashMap源码分析

底层数据结构：

JDK1.7：数组+链表

JDK1.8:  数组+链表+红黑树

### JDK 1.7中HashMap

常量和变量说明

```java
// 默认初始容量，必须是2的幂次方，默认为16
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

// 最大容量
static final int MAXIMUM_CAPACITY = 1 << 30;

// 负载因子
static final float DEFAULT_LOAD_FACTOR = 0.75f;

// 数组中的空节点
static final Entry<?,?>[] EMPTY_TABLE = {}; // 常量

// 数组，大小必须是2的幂次方
transient Entry<K,V>[] table = (Entry<K,V>[]) EMPTY_TABLE;

// hashMap存放了多少个元素，与数组大小区分
transient int size; 

// 扩容的阈值
int threshold;
```

构造方法

```java
public HashMap() {
    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
}

public HashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0)
        throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
    if (initialCapacity > MAXIMUM_CAPACITY)
        initialCapacity = MAXIMUM_CAPACITY;
    if (loadFactor <= 0 || Float.isNaN(loadFactor))
        throw new IllegalArgumentException("Illegal load factor: " +
                                           loadFactor);

    this.loadFactor = loadFactor;
    threshold = initialCapacity;
    init();  // 空方法，LinkedHashMap中有实现
}
```

put

```java
public V put(K key, V value) {
    if (table == EMPTY_TABLE) {
        inflateTable(threshold);
    }
    if (key == null)
        return putForNullKey(value); // hashMap中key可以为null
    int hash = hash(key); // 计算hash值
    int i = indexFor(hash, table.length);//根据hash值和数组长度，得到数组下标
    // 遍历table[i]开始的链表
    for (Entry<K,V> e = table[i]; e != null; e = e.next) {
        Object k;
        if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
            V oldValue = e.value;
            e.value = value;
            // LinkedHashMap有使用
            e.recordAccess(this);
            return oldValue;
        }
    }

    modCount++; // 修改次数
    addEntry(hash, key, value, i);
    return null;
}

void addEntry(int hash, K key, V value, int bucketIndex) {
    // 扩容有关
    if ((size >= threshold) && (null != table[bucketIndex])) {
        resize(2 * table.length);
        hash = (null != key) ? hash(key) : 0;
        bucketIndex = indexFor(hash, table.length);
    }

    createEntry(hash, key, value, bucketIndex);
}

void createEntry(int hash, K key, V value, int bucketIndex) {
    Entry<K,V> e = table[bucketIndex];
    // new Entry()，插入到table[bucketIndex]前面，头插法
    table[bucketIndex] = new Entry<>(hash, key, value, e);
    size++;
}
```

hash

```java
final int hash(Object k) {
    int h = hashSeed;
    if (0 != h && k instanceof String) {
        return sun.misc.Hashing.stringHash32((String) k);
    }

    h ^= k.hashCode();

    // This function ensures that hashCodes that differ only by
    // constant multiples at each bit position have a bounded
    // number of collisions (approximately 8 at default load factor).
    // 这个函数确保在每个位位置上只相差常数倍的散列码具有有限的碰撞次数
    // 在默认负载因子下大约为8次
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
}
```

indexFor(hash, table.length)

```java
// 返回索引
static int indexFor(int h, int length) {
    // assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
    return h & (length-1); // 通过&操作计算在数组中的索引
}
```

get

```java
public V get(Object key) {
    if (key == null)
        return getForNullKey();
    Entry<K,V> entry = getEntry(key);

    return null == entry ? null : entry.getValue();
}

final Entry<K,V> getEntry(Object key) {
    if (size == 0) {
        return null;
    }

    int hash = (key == null) ? 0 : hash(key);
    // 计算数组索引，然后遍历对应链表
    for (Entry<K,V> e = table[indexFor(hash, table.length)];
         e != null;
         e = e.next) {
        Object k;
        if (e.hash == hash &&
            ((k = e.key) == key || (key != null && key.equals(k))))
            return e;
    }
    return null
}
```

size

```java
public int size() {
    // Try a few times to get accurate count. On failure due to
    // continuous async changes in table, resort to locking.
    final Segment<K,V>[] segments = this.segments;
    int size;
    boolean overflow; // true if size overflows 32 bits
    long sum;         // sum of modCounts
    long last = 0L;   // previous sum
    int retries = -1; // first iteration isn't retry
    try {
        for (;;) {
            if (retries++ == RETRIES_BEFORE_LOCK) {
                for (int j = 0; j < segments.length; ++j)
                    ensureSegment(j).lock(); // force creation
            }
            sum = 0L;
            size = 0;
            overflow = false;
            for (int j = 0; j < segments.length; ++j) {
                Segment<K,V> seg = segmentAt(segments, j);
                if (seg != null) {
                    sum += seg.modCount;
                    int c = seg.count;
                    if (c < 0 || (size += c) < 0)
                        overflow = true;
                }
            }
            if (sum == last)
                break;
            last = sum;
        }
    } finally {
        if (retries > RETRIES_BEFORE_LOCK) {
            for (int j = 0; j < segments.length; ++j)
                segmentAt(segments, j).unlock();
        }
    }
    return overflow ? Integer.MAX_VALUE : size;
}
```

### JDK 1.7和1.8对比

1. JDK7：数组+链表，JDK8:  数组+链表+红黑树。当元素个数小于一个阈值时，链表整体的插入查询效率要高于红黑树，当元素个数大于此阈值时，链表整体的插入查询效率要低于红黑树。此阈值在HashMap中为8。
2. jdk1.7使用头插法，1.8之后使用尾插法。1.7使用单链表进行的纵向延伸，当采用头插法时会出现逆序且环形链表死循环的问题。1.8之后加入了红黑树使用尾插法，避免了出现逆序且链表死循环的问题。
3. 扩容后数据存储位置的计算方法也不一样：1.7使用hash值和需要扩容的二进制数进行&（这里是为什么扩容的时候一定必须是2的幂次方，因为只有2的幂次方的情况时最后一位二进制数才一定是1，能最大程度减少hash碰撞）hash值&length-1。
4. 1.8计算扩容位置，扩容前的原始位置+扩容的大小值，只需要判断hash值的新增参与运算的位是0还是1就直接迅速计算出扩容后的位置，1.7用异或。
5. 计算hash值，1.7用了9次扰动处理=4次位运算+5次异或，1.8只用了2次扰动处理=1次位运算+1次异或。

