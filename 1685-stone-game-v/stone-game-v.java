class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        int[][] dp = new int[n][n];
        int[][] maxLeft = new int[n][n];
        int[][] maxRight = new int[n][n];

        for (int i = 0; i < n; i++) {
            maxLeft[i][i] = stoneValue[i];
            maxRight[i][i] = stoneValue[i];
        }

        for (int len = 2; len <= n; len++) {
            int p = 0;
            for (int l = 0; l <= n - len; l++) {
                int r = l + len - 1;
                if (p < l) p = l;
                int total = prefix[r + 1] - prefix[l];
                
                while (p < r && (prefix[p + 1] - prefix[l]) * 2 < total) {
                    p++;
                }

                int leftSum = prefix[p + 1] - prefix[l];
                if (leftSum * 2 == total) {
                    dp[l][r] = Math.max(maxLeft[l][p], maxRight[p + 1][r]);
                } else {
                    if (p > l) {
                        dp[l][r] = Math.max(dp[l][r], maxLeft[l][p - 1]);
                    }
                    if (p < r) {
                        dp[l][r] = Math.max(dp[l][r], maxRight[p + 1][r]);
                    }
                }

                maxLeft[l][r] = Math.max(maxLeft[l][r - 1], total + dp[l][r]);
                maxRight[l][r] = Math.max(maxRight[l + 1][r], total + dp[l][r]);
            }
        }

        return dp[0][n - 1];
    }
}