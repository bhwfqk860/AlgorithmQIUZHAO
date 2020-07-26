package algorithm.week02;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Combine {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0 || n < k) return result;
        helper(n, k, 1, new Stack<Integer>());
        return result;
    }

    private void helper(int n, int k, int first, Stack<Integer> stack) {
        if (stack.size() == k) {
            result.add(new ArrayList<>(stack));
            return;
        }
        for (int i = first; i<= n - (k - stack.size()) + 1; i++) {
            stack.push(i);
            helper(n, k, i+1, stack);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        new Combine().combine(4, 2);
    }
}
