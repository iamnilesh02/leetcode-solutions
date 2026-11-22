import java.util.*;

class Solution {
    public int maxValue(int[][] events, int k) {
        int n = events.length;
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        int[] nextIdx = new int[n];
        for (int i = 0; i < n; i++) {
            nextIdx[i] = binarySearch(events, events[i][1]);
        }

        int[][] dp = new int[k + 1][n + 1];

        for (int j = 1; j <= k; j++) {
            for (int i = n - 1; i >= 0; i--) {
                int skip = dp[j][i + 1];
                int take = events[i][2] + dp[j - 1][nextIdx[i]];
                dp[j][i] = Math.max(skip, take);
            }
        }

        return dp[k][0];
    }

    private int binarySearch(int[][] events, int currentEnd) {
        int left = 0;
        int right = events.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (events[mid][0] > currentEnd) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}