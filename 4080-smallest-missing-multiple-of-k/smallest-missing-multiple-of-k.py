class Solution:
    def missingMultiple(self, nums: List[int], k: int) -> int:
        s = set(nums)
        mult = k
        while mult in s:
            mult += k
        return mult