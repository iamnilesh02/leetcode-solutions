import java.util.*;

class Solution {
    private static final int BOUND = 1_000_000;
    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked.length < 2) return true;

        Set<Long> blockedSet = new HashSet<>();
        for (int[] b : blocked) {
            blockedSet.add(encode(b[0], b[1]));
        }

        int maxArea = blocked.length * (blocked.length - 1) / 2;
        return bfs(source, target, blockedSet, maxArea) && bfs(target, source, blockedSet, maxArea);
    }

    private boolean bfs(int[] start, int[] end, Set<Long> blockedSet, int maxArea) {
        Set<Long> visited = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();
        
        long startKey = encode(start[0], start[1]);
        long endKey = encode(end[0], end[1]);
        
        queue.offer(start);
        visited.add(startKey);

        while (!queue.isEmpty() && visited.size() <= maxArea) {
            int[] curr = queue.poll();
            for (int[] d : DIRS) {
                int nx = curr[0] + d[0];
                int ny = curr[1] + d[1];
                if (nx >= 0 && nx < BOUND && ny >= 0 && ny < BOUND) {
                    long nextKey = encode(nx, ny);
                    if (nextKey == endKey) return true;
                    if (!blockedSet.contains(nextKey) && visited.add(nextKey)) {
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        return visited.size() > maxArea;
    }

    private long encode(int x, int y) {
        return ((long) x << 20) | y;
    }
}