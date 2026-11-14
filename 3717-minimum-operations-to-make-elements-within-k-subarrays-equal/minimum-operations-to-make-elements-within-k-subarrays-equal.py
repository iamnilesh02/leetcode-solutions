from sortedcontainers import SortedList

class Solution:
    def minOperations(self, nums: List[int], x: int, k: int) -> int:
        n = len(nums)
        m = n - x + 1
        cost = [0] * m
        
        left = SortedList()
        right = SortedList()
        sum_l = 0
        sum_r = 0
        
        def rebalance():
            nonlocal sum_l, sum_r
            while len(left) > len(right) + 1:
                val = left.pop()
                sum_l -= val
                right.add(val)
                sum_r += val
            while len(right) > len(left):
                val = right.pop(0)
                sum_r -= val
                left.add(val)
                sum_l += val
                
        def add(val):
            nonlocal sum_l, sum_r
            if not left or val <= left[-1]:
                left.add(val)
                sum_l += val
            else:
                right.add(val)
                sum_r += val
            rebalance()
            
        def remove(val):
            nonlocal sum_l, sum_r
            if val <= left[-1]:
                left.remove(val)
                sum_l -= val
            else:
                right.remove(val)
                sum_r -= val
            rebalance()
            
        for i in range(n):
            add(nums[i])
            if i >= x:
                remove(nums[i - x])
            if i >= x - 1:
                med = left[-1]
                cost[i - x + 1] = (med * len(left) - sum_l) + (sum_r - med * len(right))
                
        INF = float('inf')
        dp = [0] * (m + x)
        
        for _ in range(k):
            new_dp = [INF] * (m + x)
            for i in range(m - 1, -1, -1):
                take = cost[i] + dp[i + x]
                new_dp[i] = min(new_dp[i + 1], take)
            dp = new_dp
            
        return dp[0]