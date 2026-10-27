class Solution:
    def processStr(self, s: str, k: int) -> str:
        lengths = [0] * (len(s) + 1)
        
        for i, ch in enumerate(s):
            if ch == '*':
                lengths[i + 1] = max(0, lengths[i] - 1)
            elif ch == '#':
                lengths[i + 1] = lengths[i] * 2
            elif ch == '%':
                lengths[i + 1] = lengths[i]
            else:
                lengths[i + 1] = lengths[i] + 1

        if k < 0 or k >= lengths[len(s)]:
            return "."

        curr_k = k
        for i in range(len(s) - 1, -1, -1):
            ch = s[i]
            prev_len = lengths[i]
            
            if ch == '*':
                continue
            elif ch == '%':
                curr_k = prev_len - 1 - curr_k
            elif ch == '#':
                if curr_k >= prev_len:
                    curr_k -= prev_len
            else:
                if curr_k == prev_len:
                    return ch

        return "."