class Solution {
    public int longestIdealString(String s, int k) {
        int[] dp = new int[26];
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i) - 'a';
            int bestPrev = 0;

            int left = Math.max(0, curr - k);
            int right = Math.min(25, curr + k);

            for (int prev = left; prev <= right; prev++) {
                bestPrev = Math.max(bestPrev, dp[prev]);
            }

            dp[curr] = bestPrev + 1;
            maxLen = Math.max(maxLen, dp[curr]);
        }

        return maxLen;
    }
}