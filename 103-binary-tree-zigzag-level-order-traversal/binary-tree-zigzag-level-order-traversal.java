class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(Collections.nCopies(levelSize, 0));

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                int index = leftToRight ? i : (levelSize - 1 - i);
                currentLevel.set(index, node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            leftToRight = !leftToRight;
            result.add(currentLevel);
        }

        return result;
    }
}