class Solution:
    def sumAndMultiply(self, n: int) -> int:
        digits = [ch for ch in str(n) if ch != '0']
        if not digits:
            return 0
        x = int("".join(digits))
        s = sum(int(d) for d in digits)
        return x * s