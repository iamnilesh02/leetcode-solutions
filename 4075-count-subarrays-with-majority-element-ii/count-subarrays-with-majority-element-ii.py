class Solution:
    def countMajoritySubarrays(self, nums: List[int], target: int) -> int:
        n = len(nums)
        offset = n + 1
        bit = [0] * (2 * n + 3)

        def update(idx: int, val: int):
            while idx < len(bit):
                bit[idx] += val
                idx += idx & (-idx)

        def query(idx: int) -> int:
            total = 0
            while idx > 0:
                total += bit[idx]
                idx -= idx & (-idx)
            return total

        update(offset, 1)
        curr_sum = 0
        ans = 0

        for x in nums:
            curr_sum += 1 if x == target else -1
            idx = curr_sum + offset
            ans += query(idx - 1)
            update(idx, 1)

        return ans