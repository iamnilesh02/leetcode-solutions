class Solution {
    public int countOfPairs(int[] nums) {
        int MOD = 1_000_000_007;
        int n = nums.length;
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        int[] dp = new int[maxVal + 1];
        for (int j = 0; j <= nums[0]; j++) {
            dp[j] = 1;
        }

        for (int i = 1; i < n; i++) {
            int[] prefix = new int[maxVal + 2];
            for (int j = 0; j <= maxVal; j++) {
                prefix[j + 1] = (prefix[j] + dp[j]) % MOD;
            }

            int[] nextDp = new int[maxVal + 1];
            int diff = Math.max(0, nums[i] - nums[i - 1]);

            for (int j = diff; j <= nums[i]; j++) {
                int maxPrev = j - diff;
                nextDp[j] = prefix[maxPrev + 1];
            }
            dp = nextDp;
        }

        int ans = 0;
        for (int count : dp) {
            ans = (ans + count) % MOD;
        }
        return ans;
    }
}