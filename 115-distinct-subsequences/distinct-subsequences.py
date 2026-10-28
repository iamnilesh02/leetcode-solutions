class Solution:
    def numDistinct(self, s: str, t: str) -> int:
        m, n = len(s), len(t)
        dp = [0] * (n + 1)
        dp[0] = 1

        for c_s in s:
            for j in range(n - 1, -1, -1):
                if c_s == t[j]:
                    dp[j + 1] += dp[j]

        return dp[n]