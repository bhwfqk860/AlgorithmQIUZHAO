package algorithm.week04;

import java.util.Arrays;

public class LeastInterval {

    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int max = map[25] - 1, idle = max * n;
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            idle -= Math.min(map[i], max);
        }
        return idle > 0 ? idle + tasks.length : tasks.length;
    }

    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;
        new LeastInterval().leastInterval(tasks, n);
    }
}
