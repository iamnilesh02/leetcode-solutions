from collections import defaultdict

class Solution:
    def largestInteger(self, nums: List[int], k: int) -> int:
        counts = defaultdict(int)
        n = len(nums)
        for i in range(n - k + 1):
            for x in set(nums[i:i + k]):
                counts[x] += 1
                
        ans = -1
        for x, c in counts.items():
            if c == 1:
                ans = max(ans, x)
                
        return ans