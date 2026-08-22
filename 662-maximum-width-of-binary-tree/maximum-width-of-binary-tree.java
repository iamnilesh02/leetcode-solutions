class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        int maxWidth = 0;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int minIndex = queue.peek().getValue();
            int first = 0, last = 0;

            for (int i = 0; i < levelSize; i++) {
                Pair<TreeNode, Integer> current = queue.poll();
                TreeNode node = current.getKey();
                int currIndex = current.getValue() - minIndex;

                if (i == 0) first = currIndex;
                if (i == levelSize - 1) last = currIndex;

                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, 2 * currIndex + 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, 2 * currIndex + 2));
                }
            }

            maxWidth = Math.max(maxWidth, last - first + 1);
        }

        return maxWidth;
    }
}