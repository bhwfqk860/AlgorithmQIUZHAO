package algorithm.week02;

import java.util.ArrayList;
import java.util.List;

public class Permute {

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        boolean[] visited = new boolean[len];
        helper(nums, len, 0, new ArrayList<Integer>(), visited, res);
        return res;
    }

    private void helper(int[] nums, int len, int depth, List<Integer> path, boolean[] visited, List<List<Integer>> res) {
        if (len == depth) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len ; i++) {
            if (!visited[i]) {
                path.add(nums[i]);
                visited[i] = true;
                helper(nums, len, depth+1, path, visited, res);
                visited[i] = false;
                path.remove(path.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        new Permute().permute(nums);
    }
}
