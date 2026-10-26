class Solution:
    def totalWaviness(self, num1: int, num2: int) -> int:
        total = 0
        for x in range(num1, num2 + 1):
            s = str(x)
            for i in range(1, len(s) - 1):
                if s[i - 1] < s[i] > s[i + 1] or s[i - 1] > s[i] < s[i + 1]:
                    total += 1
        return total