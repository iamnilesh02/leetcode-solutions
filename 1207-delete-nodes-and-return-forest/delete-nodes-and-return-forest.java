import java.util.*;

class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDeleteSet = new HashSet<>();
        for (int val : to_delete) {
            toDeleteSet.add(val);
        }
        List<TreeNode> forest = new ArrayList<>();
        root = dfs(root, toDeleteSet, forest);
        if (root != null) {
            forest.add(root);
        }
        return forest;
    }

    private TreeNode dfs(TreeNode node, Set<Integer> toDeleteSet, List<TreeNode> forest) {
        if (node == null) {
            return null;
        }
        node.left = dfs(node.left, toDeleteSet, forest);
        node.right = dfs(node.right, toDeleteSet, forest);

        if (toDeleteSet.contains(node.val)) {
            if (node.left != null) {
                forest.add(node.left);
            }
            if (node.right != null) {
                forest.add(node.right);
            }
            return null;
        }
        return node;
    }
}