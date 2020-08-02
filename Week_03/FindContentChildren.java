package algorithm.week03;

import java.util.Arrays;

public class FindContentChildren {

    public int findContentChildren(int[] g, int[] s) {
        if (g == null || s == null) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j])
                i++;
            j++;
        }
        return i;
    }

    public static void main(String[] args) {
        int[] g = {1,1,2};
        int[] s = {1,1};
        new FindContentChildren().findContentChildren(g, s);
    }
}
