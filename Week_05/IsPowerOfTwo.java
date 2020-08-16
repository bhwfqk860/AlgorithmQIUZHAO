package algorithm.week05;

public class IsPowerOfTwo {

    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        long x = (long) n;
        return (x & (x-1)) == 0;
    }

    public static void main(String[] args) {
        new IsPowerOfTwo().isPowerOfTwo(5);
    }
}
