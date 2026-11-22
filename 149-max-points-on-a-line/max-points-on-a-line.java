import java.util.*;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }

        int maxPointsOnLine = 1;

        for (int i = 0; i < n; i++) {
            Map<Long, Integer> slopeCount = new HashMap<>();
            int currentMax = 0;

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;

                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                } else if (dx == 0) {
                    dy = 1;
                } else if (dy == 0) {
                    dx = 1;
                }

                long key = (((long) dx) << 32) | (dy & 0xffffffffL);
                int count = slopeCount.getOrDefault(key, 0) + 1;
                slopeCount.put(key, count);
                currentMax = Math.max(currentMax, count);
            }

            maxPointsOnLine = Math.max(maxPointsOnLine, currentMax + 1);
        }

        return maxPointsOnLine;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}