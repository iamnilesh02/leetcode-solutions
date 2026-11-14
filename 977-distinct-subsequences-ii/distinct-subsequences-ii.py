class Solution:
    def distinctSubseqII(self, s: str) -> int:
        MOD = 10**9 + 7
        end_with = [0] * 26
        total = 0

        for ch in s:
            idx = ord(ch) - ord('a')
            added = (total + 1 - end_with[idx]) % MOD
            total = (total + added) % MOD
            end_with[idx] = (end_with[idx] + added) % MOD

        return total