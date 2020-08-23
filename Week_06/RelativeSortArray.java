package algorithm.week06;

public class RelativeSortArray {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] arr = new int[1001];
        int[] ans = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            arr[arr1[i]]++;
        }
        int j = 0;
        for (int i = 0; i < arr2.length; i++) {
            while (arr[arr2[i]] > 0) {
                ans[j++] = arr2[i];
                arr[arr2[i]]--;
            }
        }
        for (int i = 0; i < 1001; i++) {
            while (arr[i] > 0) {
                ans[j++] = i;
                arr[i]--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        new RelativeSortArray().relativeSortArray(arr1, arr2);
    }
}
