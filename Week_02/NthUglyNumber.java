package algorithm.week02;

public class NthUglyNumber {
    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int nums[] = new int[n];
        nums[0] = 1;
        for (int i = 1; i < n; i++) {
            int x = nums[a] * 2, y = nums[b] * 3, z = nums[c] * 5;
            nums[i] = Math.min(Math.min(x, y), z);
            if (nums[i] == x) a++;
            if (nums[i] == y) b++;
            if (nums[i] == z) c++;
        }
        return nums[n-1];
    }

    public static void main(String[] args) {
        new NthUglyNumber().nthUglyNumber(10);
    }
}
