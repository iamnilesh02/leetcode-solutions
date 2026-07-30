import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] directions = {
            {},
            {{0, -1}, {0, 1}},
            {{-1, 0}, {1, 0}},
            {{0, -1}, {1, 0}},
            {{0, 1}, {1, 0}},
            {{0, -1}, {-1, 0}},
            {{0, 1}, {-1, 0}}
        };

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            if (r == m - 1 && c == n - 1) {
                return true;
            }

            int streetType = grid[r][c];

            for (int[] dir : directions[streetType]) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    int nextStreetType = grid[nr][nc];
                    for (int[] nextDir : directions[nextStreetType]) {
                        if (nr + nextDir[0] == r && nc + nextDir[1] == c) {
                            visited[nr][nc] = true;
                            queue.add(new int[]{nr, nc});
                            break;
                        }
                    }
                }
            }
        }

        return false;
    }
}