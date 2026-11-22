import java.util.*;

class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int maxPossibleCost = Math.min(k, m + n - 1);
        
        int[][] dp = new int[n][maxPossibleCost + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int startVal = grid[0][0];
        int startCost = startVal == 0 ? 0 : 1;
        if (startCost <= maxPossibleCost) {
            dp[0][startCost] = startVal;
        }

        for (int i = 0; i < m; i++) {
            int[][] nextDp = new int[n][maxPossibleCost + 1];
            for (int[] row : nextDp) {
                Arrays.fill(row, -1);
            }

            if (i == 0) {
                if (startCost <= maxPossibleCost) {
                    nextDp[0][startCost] = startVal;
                }
            }

            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int val = grid[i][j];
                int cost = val == 0 ? 0 : 1;

                for (int c = cost; c <= maxPossibleCost; c++) {
                    int prevMax = -1;
                    if (i > 0 && dp[j][c - cost] != -1) {
                        prevMax = Math.max(prevMax, dp[j][c - cost]);
                    }
                    if (j > 0 && nextDp[j - 1][c - cost] != -1) {
                        prevMax = Math.max(prevMax, nextDp[j - 1][c - cost]);
                    }

                    if (prevMax != -1) {
                        nextDp[j][c] = prevMax + val;
                    }
                }
            }
            dp = nextDp;
        }

        int ans = -1;
        for (int c = 0; c <= maxPossibleCost; c++) {
            ans = Math.max(ans, dp[n - 1][c]);
        }

        return ans;
    }
}