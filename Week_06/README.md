# 学习笔记

## 交换排序

### 冒泡排序

O(n2)  稳定



### 快速排序

O(nlogn)  不稳定

```java
public static void quickSort(int[] array, int begin, int end) {
    if (end <= begin) return;
    int pivot = partition(array, begin, end);
    quickSort(array, begin, pivot - 1);
    quickSort(array, pivot + 1, end);
}
static int partition(int[] a, int begin, int end) {
    // pivot: 标杆位置，counter: 小于pivot的元素的个数
    int pivot = end, counter = begin;
    for (int i = begin; i < end; i++) {
        if (a[i] < a[pivot]) {
          int temp = a[counter];
          a[counter] = a[i];
          a[i] = temp;
          counter++;
        }
    }
    int temp = a[pivot]; 
    a[pivot] = a[counter]; 
    a[counter] = temp;
    return counter;
}
```



## 插入排序

### 简单插入排序

O(n2)  稳定



### 希尔排序

O(n1.3)  不稳定



## 选择排序

### 简单选择排序

O(n2)  不稳定



### 堆排序

O(nlogn)  不稳定



## 归并排序

O(nlogn)  稳定

```java
public static void mergeSort(int[] array, int left, int right) { 
    if (right <= left) return;
    int mid = (left + right) >> 1; 
    mergeSort(array, left, mid);
    mergeSort(array, mid + 1, right);
    merge(array, left, mid, right);
}

public static void merge(int[] arr, int left, int mid, int right) { 
    int[] temp = new int[right - left + 1];
    int i = left, j = mid + 1, k = 0;
    while (i <= mid && j <= right) {
      temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
    }
    while (i <= mid)   temp[k++] = arr[i++];
    while (j <= right) temp[k++] = arr[j++];
    for (int p = 0; p < temp.length; p++) {
      arr[left + p] = temp[p];
    }
}
```



## 非比较排序

### 计数排序

O(n+k)  稳定



### 桶排序

O(n+k)  稳定



### 基数排序

O(n*k)  稳定







