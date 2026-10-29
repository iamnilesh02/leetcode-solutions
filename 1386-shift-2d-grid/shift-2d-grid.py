class Solution:
    def shiftGrid(self, grid: list[list[int]], k: int) -> list[list[int]]:
        m, n = len(grid), len(grid[0])
        total = m * n
        k %= total
        
        flat = [grid[r][c] for r in range(m) for c in range(n)]
        flat = flat[-k:] + flat[:-k]
        
        return [flat[i * n : (i + 1) * n] for i in range(m)]