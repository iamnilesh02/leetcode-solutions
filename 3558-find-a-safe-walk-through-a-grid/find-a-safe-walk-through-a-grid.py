from collections import deque

class Solution:
    def findSafeWalk(self, grid: List[List[int]], health: int) -> bool:
        m, n = len(grid), len(grid[0])
        dist = [[float('inf')] * n for _ in range(m)]
        
        dist[0][0] = grid[0][0]
        q = deque([(0, 0)])
        
        while q:
            r, c = q.popleft()
            
            if r == m - 1 and c == n - 1:
                return dist[r][c] < health
            
            for dr, dc in ((0, 1), (0, -1), (1, 0), (-1, 0)):
                nr, nc = r + dr, c + dc
                if 0 <= nr < m and 0 <= nc < n:
                    w = grid[nr][nc]
                    if dist[r][c] + w < dist[nr][nc]:
                        dist[nr][nc] = dist[r][c] + w
                        if w == 1:
                            q.append((nr, nc))
                        else:
                            q.appendleft((nr, nc))
                            
        return dist[m - 1][n - 1] < health