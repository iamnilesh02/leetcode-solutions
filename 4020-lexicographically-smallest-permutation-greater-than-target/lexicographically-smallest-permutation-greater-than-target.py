class Solution:
    def lexGreaterPermutation(self, s: str, target: str) -> str:
        counts = [0] * 26
        for ch in s:
            counts[ord(ch) - ord('a')] += 1

        n = len(s)
        match_len = 0
        for ch in target:
            idx = ord(ch) - ord('a')
            if counts[idx] > 0:
                counts[idx] -= 1
                match_len += 1
            else:
                break

        curr = match_len
        if curr == n:
            curr = n - 1
            counts[ord(target[curr]) - ord('a')] += 1

        for i in range(curr, -1, -1):
            t_val = ord(target[i]) - ord('a')
            found_char = -1
            for c in range(t_val + 1, 26):
                if counts[c] > 0:
                    found_char = c
                    break

            if found_char != -1:
                counts[found_char] -= 1
                tail = []
                for c in range(26):
                    if counts[c] > 0:
                        tail.append(chr(ord('a') + c) * counts[c])
                return target[:i] + chr(ord('a') + found_char) + "".join(tail)

            if i > 0:
                counts[ord(target[i - 1]) - ord('a')] += 1

        return ""