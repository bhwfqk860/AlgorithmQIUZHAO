package algorithm.week06;

public class ReverseStr {

    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        for (int start = 0; start < arr.length; start += 2 * k) {
            int i = start, j = Math.min(start + k - 1, arr.length - 1);
            char tmp;
            while (i < j) {
                tmp = arr[i];
                arr[i++] = arr[j];
                arr[j--] = tmp;
            }
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        new ReverseStr().reverseStr("abcdefg", 2);
    }
}
