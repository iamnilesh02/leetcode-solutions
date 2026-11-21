class Solution {
    public boolean canReachCorner(int xCorner, int yCorner, int[][] circles) {
        int n = circles.length;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            long x = circles[i][0], y = circles[i][1], r = circles[i][2];
            if (isPointInCircle(0, 0, x, y, r) || isPointInCircle(xCorner, yCorner, x, y, r)) {
                return false;
            }
            if (!visited[i] && (touchesTop(x, y, r, xCorner, yCorner) || touchesLeft(x, y, r, xCorner, yCorner))) {
                if (dfs(i, xCorner, yCorner, circles, visited)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int i, long xCorner, long yCorner, int[][] circles, boolean[] visited) {
        visited[i] = true;
        long x1 = circles[i][0], y1 = circles[i][1], r1 = circles[i][2];

        if (touchesBottom(x1, y1, r1, xCorner, yCorner) || touchesRight(x1, y1, r1, xCorner, yCorner)) {
            return true;
        }

        for (int j = 0; j < circles.length; j++) {
            if (!visited[j]) {
                long x2 = circles[j][0], y2 = circles[j][1], r2 = circles[j][2];
                long dx = x1 - x2, dy = y1 - y2, rSum = r1 + r2;
                if (dx * dx + dy * dy <= rSum * rSum) {
                    if (x1 * r2 + x2 * r1 < rSum * xCorner && y1 * r2 + y2 * r1 < rSum * yCorner) {
                        if (dfs(j, xCorner, yCorner, circles, visited)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isPointInCircle(long px, long py, long cx, long cy, long r) {
        long dx = px - cx, dy = py - cy;
        return dx * dx + dy * dy <= r * r;
    }

    private boolean touchesLeft(long x, long y, long r, long xCorner, long yCorner) {
        return Math.abs(x) <= r && y >= 0 && y <= yCorner;
    }

    private boolean touchesTop(long x, long y, long r, long xCorner, long yCorner) {
        return Math.abs(y - yCorner) <= r && x >= 0 && x <= xCorner;
    }

    private boolean touchesRight(long x, long y, long r, long xCorner, long yCorner) {
        return Math.abs(x - xCorner) <= r && y >= 0 && y <= yCorner;
    }

    private boolean touchesBottom(long x, long y, long r, long xCorner, long yCorner) {
        return Math.abs(y) <= r && x >= 0 && x <= xCorner;
    }
}