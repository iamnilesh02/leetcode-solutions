import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] components = path.split("/");

        for (String dir : components) {
            if (dir.isEmpty() || dir.equals(".")) {
                continue;
            } else if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else {
                stack.addLast(dir);
            }
        }

        StringBuilder canonicalPath = new StringBuilder();
        for (String dir : stack) {
            canonicalPath.append("/").append(dir);
        }

        return canonicalPath.length() == 0 ? "/" : canonicalPath.toString();
    }
}