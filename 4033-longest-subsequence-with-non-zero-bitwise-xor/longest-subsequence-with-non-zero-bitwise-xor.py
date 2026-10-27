class Solution:
    def longestSubsequence(self, nums: List[int]) -> int:
        total_xor = 0
        for x in nums:
            total_xor ^= x

        if total_xor != 0:
            return len(nums)

        if all(x == 0 for x in nums):
            return 0

        return len(nums) - 1