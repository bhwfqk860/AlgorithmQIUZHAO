package algorithm.week02;

import java.util.HashMap;
import java.util.Map;

public class BuildTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length, inorder, 0, inorder.length, map);
    }

    public TreeNode helper(int[] preorder, int pre_start, int pre_end, int[] inorder, int in_start, int in_end, Map<Integer, Integer> map) {
        if (pre_start == pre_end) return null;
        int root_val = preorder[pre_start];
        TreeNode root = new TreeNode(root_val);
        int in_root_index = map.get(root_val);
        int left_num = in_root_index - in_start;
        root.left = helper(preorder, pre_start+1, pre_start+left_num+1, inorder, in_start, in_root_index, map);
        root.right = helper(preorder, pre_start+left_num+1, pre_end, inorder, in_root_index+1, in_end, map);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        new BuildTree().buildTree(preorder, inorder);
    }
}
