package algorithm.week04;

public class CountSubstrings {

    public int countSubstrings(String s) {
        if (s == null || s.equals("")) return 0;
        int n = s.length(), result = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new CountSubstrings().countSubstrings("abc");
    }
}
