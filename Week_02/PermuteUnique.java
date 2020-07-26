package algorithm.week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermuteUnique {

    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        Arrays.sort(nums);
        boolean[] visited = new boolean[len];
        helper(nums, len, 0, new ArrayList<Integer>(), visited, res);
        return res;
    }

    private void helper(int[] nums, int len, int depth, List<Integer> path, boolean[] visited, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (visited[i]) continue;
            if (i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
            path.add(nums[i]);
            visited[i] = true;
            helper(nums, len, depth+1, path, visited, res);
            visited[i] = false;
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        new PermuteUnique().permuteUnique(nums);
    }
}
