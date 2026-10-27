import heapq

class Solution:
    def maxTotalValue(self, nums: List[int], k: int) -> int:
        n = len(nums)
        LOG = n.bit_length()
        
        st_max = [[0] * LOG for _ in range(n)]
        st_min = [[0] * LOG for _ in range(n)]
        
        for i in range(n):
            st_max[i][0] = nums[i]
            st_min[i][0] = nums[i]
            
        for j in range(1, LOG):
            for i in range(n - (1 << j) + 1):
                st_max[i][j] = max(st_max[i][j - 1], st_max[i + (1 << (j - 1))][j - 1])
                st_min[i][j] = min(st_min[i][j - 1], st_min[i + (1 << (j - 1))][j - 1])
                
        def query_val(l: int, r: int) -> int:
            length = r - l + 1
            j = length.bit_length() - 1
            mx = max(st_max[l][j], st_max[r - (1 << j) + 1][j])
            mn = min(st_min[l][j], st_min[r - (1 << j) + 1][j])
            return mx - mn

        heap = []
        for l in range(n):
            val = query_val(l, n - 1)
            heapq.heappush(heap, (-val, l, n - 1))
            
        total = 0
        for _ in range(k):
            neg_val, l, r = heapq.heappop(heap)
            total += -neg_val
            if r > l:
                next_val = query_val(l, r - 1)
                heapq.heappush(heap, (-next_val, l, r - 1))
                
        return total