package algorithm.week05;

public class CountBits {

    public int[] countBits(int num) {
        int[] n = new int[num+1];
        for (int i = 1; i <= num; i++) {
            n[i] = n[i & (i-1)] + 1;
        }
        return n;
    }

    public static void main(String[] args) {
        new CountBits().countBits(5);
    }
}
