# 学习笔记

## 堆

在堆的数据结构中，堆中的最大值（或者最小值）总是位于根节点。

大顶堆：每个节点的值都大于或者等于它的左右子节点的值。

小顶堆：每个节点的值都小于或者等于它的左右子节点的值。

常见的堆有二叉堆，Binomoial堆和Fibonacci堆等。

### 二叉堆

二叉堆是堆的一种常见的实现方式，通过完全二叉树实现。

##### find-max

找到堆顶元素，时间复杂度 O(1)

##### insert

插入操作，新元素先插入到堆的尾部，依次向上调整整个堆的结构

时间复杂度 O(logN)

##### delete-max

删除堆顶操作，将堆尾元素替换到顶部，依次从根部向下调整整个堆的结构

时间复杂度 O(logN)

## 堆排序

堆排序是指基于堆数据结构的一种排序算法，它的最好，最坏，平均时间复杂度均为O(nlogn)。

堆排序（升序）的基本思想：

1. 将输入序列构建成一个大顶堆；
2. 将堆顶元素和最后一个元素交换，然后将剩下的节点重新构建成大顶堆；
3. 重复此步骤。

### 堆排序的代码实现

```java
public class HeapSort { 
  public void sort(int arr[]) { 
    int n = arr.length; 

    // 构建初始堆，从最后一个非叶子节点开始，从下至上调整
    // 索引从0开始，所以最后一个非叶子节点的索引是n/2-1
    for (int i = n / 2 - 1; i >= 0; i--) 
      // 构建堆(重新排列数组)
      heapify(arr, n, i); 

    // 将堆顶元素和末尾元素交换，使末尾元素最大，反复进行交换、构建
    for (int i=n-1; i>0; i--) { 
      // 交换堆顶元素和最后一个元素
      int temp = arr[0]; 
      arr[0] = arr[i]; 
      arr[i] = temp; 

      // 将剩下的节点重新构建堆
      heapify(arr, i, 0); 
    } 
  } 

  // 堆化
  // n是堆的大小，i起始节点
  void heapify(int arr[], int n, int i) { 
    int largest = i; // 最大节点的索引
    int l = 2*i + 1; // left = 2*i + 1 
    int r = 2*i + 2; // right = 2*i + 2 

    // 如果左节点大于根节点，将最大节点设为左节点
    if (l < n && arr[l] > arr[largest]) 
      largest = l; 

    // 如果右节点大于根节点，将最大节点设为右节点
    if (r < n && arr[r] > arr[largest]) 
      largest = r; 

    // 如果最大值不在根节点，交换最大节点和根节点
    if (largest != i) { 
      int swap = arr[i]; 
      arr[i] = arr[largest]; 
      arr[largest] = swap; 

      // 递归变换子树
      heapify(arr, n, largest); 
    } 
  } 

  // 打印数组
  static void printArray(int arr[]) { 
    int n = arr.length; 
    for (int i=0; i<n; ++i) 
      System.out.print(arr[i]+" "); 
    System.out.println(); 
  }

  public static void main(String args[]) { 
    int arr[] = {12, 11, 13, 5, 6, 7}; 
    int n = arr.length; 

    HeapSort ob = new HeapSort(); 
    ob.sort(arr); 

    System.out.println("Sorted array is"); 
    printArray(arr); 
  }
} 
```

### 堆排序的应用

1. 合并有序小文件：

   假设有100个100MB的小文件，每个文件都是有序的字符串，将其合并成一个有序大文件。

   将每个小文件的最小字符串放入一个小顶堆中，从小顶堆中取出堆顶并放入结果文件中。并将堆顶所在的小文件取出下一个字符串放入堆顶并堆化（循环如此）。

2. 找到数组中k个最大（或最小）的元素 