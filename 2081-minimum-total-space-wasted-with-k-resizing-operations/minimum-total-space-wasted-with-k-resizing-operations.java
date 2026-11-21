import java.util.*;

class Solution {
    public int minSpaceWastedKResizing(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n + 1][k + 2];
        int INF = 200_000_000;
        
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k + 1; j++) {
                int maxVal = 0;
                int sum = 0;
                for (int p = i; p >= 1; p--) {
                    maxVal = Math.max(maxVal, nums[p - 1]);
                    sum += nums[p - 1];
                    int wasted = maxVal * (i - p + 1) - sum;
                    dp[i][j] = Math.min(dp[i][j], dp[p - 1][j - 1] + wasted);
                }
            }
        }

        int ans = INF;
        for (int j = 1; j <= k + 1; j++) {
            ans = Math.min(ans, dp[n][j]);
        }
        return ans;
    }
}