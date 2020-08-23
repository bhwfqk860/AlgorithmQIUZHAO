package algorithm.week06;

public class FirstUniqChar {

    public int firstUniqChar(String s) {
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (table[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        new FirstUniqChar().firstUniqChar("leetcode");
    }
}
