class Solution:
    def numOfSubarrays(self, arr: list[int]) -> int:
        MOD = 10**9 + 7
        even_count = 1
        odd_count = 0
        prefix_parity = 0
        ans = 0

        for num in arr:
            prefix_parity ^= (num & 1)
            if prefix_parity == 1:
                ans = (ans + even_count) % MOD
                odd_count += 1
            else:
                ans = (ans + odd_count) % MOD
                even_count += 1

        return ans