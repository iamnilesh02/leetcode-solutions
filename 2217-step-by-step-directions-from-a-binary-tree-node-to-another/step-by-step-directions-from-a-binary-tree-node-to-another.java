class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder startPath = new StringBuilder();
        StringBuilder destPath = new StringBuilder();

        findPath(root, startValue, startPath);
        findPath(root, destValue, destPath);

        startPath.reverse();
        destPath.reverse();

        int i = 0;
        int minLen = Math.min(startPath.length(), destPath.length());
        while (i < minLen && startPath.charAt(i) == destPath.charAt(i)) {
            i++;
        }

        StringBuilder res = new StringBuilder();
        for (int j = i; j < startPath.length(); j++) {
            res.append('U');
        }
        res.append(destPath.substring(i));

        return res.toString();
    }

    private boolean findPath(TreeNode node, int target, StringBuilder path) {
        if (node == null) return false;
        if (node.val == target) return true;

        if (findPath(node.left, target, path)) {
            path.append('L');
            return true;
        }
        if (findPath(node.right, target, path)) {
            path.append('R');
            return true;
        }

        return false;
    }
}