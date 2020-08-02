# 学习笔记

### 分治

分治的思想是把一个问题分解为k个规模较小的子问题，这些子问题相互独立且与原问题性质相同。将子问题的解进行组合得到原问题的解。

##### 代码模板

```java
private static int divide_conquer(Problem problem, ) {
  // recursion terminator
  if (problem == NULL) {
    int res = process_last_result();
    return res;     
  }
  // prepare data
  subProblems = split_problem(problem)
  // conquer subproblems
  res0 = divide_conquer(subProblems[0])
  res1 = divide_conquer(subProblems[1])
  // process and generate the final result
  result = process_result(res0, res1);
  // revert the current level status
  
  return result;
}
```

### 回溯

回溯法采用试错的思想，它尝试分步的去解决一个问题。在分步解决问题的过程中，当它通过尝试发现现有的分步答案不能得到有效的正确的解答的时候，它将取消上一步甚至是上几步的计算，再通过其他的可能的分步解答再次尝试寻找问题的答案。

回溯法通常使用递归来实现。

### 遍历搜索

- 每个节点都要访问一次

- 每个节点仅访问一次

- 对于节点的访问顺序不限
  - 深度优先：通常使用栈实现
  - 广度优先：通常使用队列实现

#### 深度优先搜索

##### 递归实现

```java
// 节点是否被访问
Set<Node> visited = new HashSet<>();

void dfs(Node node, Set<Node> visited) {
    // 递归终止条件
    if (visited.contains(node))
        return;
    
    // 处理当前节点
    visited.add(node) 
    // 其它处理

    // 递归到下一层
    for (Node next : node.children()) { 
				dfs(next, visited);
    }
}
```

##### 迭代实现

```java
void dfs(Node root) {
  if(root == null) return;

  Set<Node> visited = new HashSet<>();
  Stack<Node> stack = new Stack<>();
  stack.push(root);

  while (!stack.isEmpty()) {
    Node node = stack.poll();

    if (visited.contains(node)) 
      	continue;
    
    visited.add(node);

    process(node);
    
    List<Node> nodes = generate_related_nodes(node);
    stack.addAll(nodes);
  }

  return;
}
```

#### 广度优先搜索

##### 代码实现

```java
void bfs(Node root) {
  Set<Node> visited = new HashSet<>();
	Queue<Node> queue = new LinkedList<>();
	queue.add(root);
  visited.add(root);
  
	while (!queue.isEmpty()) { 
		Node node = queue.poll();
		visited.add(node);
    
		process(node);
    
		List<Node> nodes = generate_related_nodes(node);
		queue.addAll(nodes);
  }
	// other processing work 
	// ...
}
```

### 思考题

##### 使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方

假定为升序排序数组在某个点进行了旋转，数组最小值所在下标即是中间无序的地方（下标不为0）

最小值左边部分和右边部分都是单调递增，且左边每个元素大于右边

使用二分查找，若nums[mid] > nums[right] ，则最小值在右半边，收缩左边界；若nums[mid] < nums[right]，则最小值在左半边，收缩右边界

```java
public int findMin(int[] nums) {
	int left = 0;
	int right = nums.length - 1;
	while (left < right) {
    int mid = left + (right - left) / 2;
    if (nums[mid] > nums[right]) {          
      left = mid + 1;
    } else {                                
      right = mid;
    }
	}
	return left == 0 ? -1 : left;
}
```

