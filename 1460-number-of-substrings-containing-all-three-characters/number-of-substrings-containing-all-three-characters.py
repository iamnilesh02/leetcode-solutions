class Solution:
    def numberOfSubstrings(self, s: str) -> int:
        last_pos = {'a': -1, 'b': -1, 'c': -1}
        ans = 0

        for i, ch in enumerate(s):
            last_pos[ch] = i
            ans += 1 + min(last_pos['a'], last_pos['b'], last_pos['c'])

        return ans