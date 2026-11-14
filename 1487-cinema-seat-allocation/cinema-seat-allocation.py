from collections import defaultdict

class Solution:
    def maxNumberOfFamilies(self, n: int, reservedSeats: list[list[int]]) -> int:
        rows = defaultdict(int)
        for r, c in reservedSeats:
            if 2 <= c <= 9:
                rows[r] |= (1 << (c - 2))

        ans = (n - len(rows)) * 2

        left_mask = 0b00001111
        mid_mask  = 0b00111100
        right_mask = 0b11110000

        for mask in rows.values():
            can_left = (mask & left_mask) == 0
            can_right = (mask & right_mask) == 0
            can_mid = (mask & mid_mask) == 0

            if can_left and can_right:
                ans += 2
            elif can_left or can_right or can_mid:
                ans += 1

        return ans