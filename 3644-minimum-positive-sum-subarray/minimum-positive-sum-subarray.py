class Solution:
    def minimumSumSubarray(self, nums: List[int], l: int, r: int) -> int:
        n = len(nums)
        prefix = [0] * (n + 1)
        for i in range(n):
            prefix[i + 1] = prefix[i] + nums[i]
            
        ans = float('inf')
        for length in range(l, r + 1):
            for i in range(n - length + 1):
                s = prefix[i + length] - prefix[i]
                if s > 0 and s < ans:
                    ans = s
                    
        return ans if ans != float('inf') else -1