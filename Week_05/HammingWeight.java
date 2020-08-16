package algorithm.week05;

public class HammingWeight {

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= n -1;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        new HammingWeight().hammingWeight(00000000000000000000000000001011);
    }
}
