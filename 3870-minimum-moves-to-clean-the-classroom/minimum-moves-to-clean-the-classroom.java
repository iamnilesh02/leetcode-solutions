import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int[][] litterIdx = new int[m][n];
        for (int[] row : litterIdx) {
            Arrays.fill(row, -1);
        }

        int startR = 0, startC = 0, litterCount = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litterIdx[r][c] = litterCount++;
                }
            }
        }

        if (litterCount == 0) {
            return 0;
        }

        int targetMask = (1 << litterCount) - 1;
        int[][][] maxEnergy = new int[m][n][1 << litterCount];
        for (int[][] mat : maxEnergy) {
            for (int[] row : mat) {
                Arrays.fill(row, -1);
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC, 0, energy});
        maxEnergy[startR][startC][0] = energy;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0], c = curr[1], mask = curr[2], e = curr[3];

                if (mask == targetMask) {
                    return moves;
                }

                if (e == 0) {
                    continue;
                }

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    char ch = classroom[nr].charAt(nc);
                    if (ch == 'X') {
                        continue;
                    }

                    int nextE = (ch == 'R') ? energy : e - 1;
                    int nextMask = mask;
                    if (ch == 'L') {
                        nextMask |= (1 << litterIdx[nr][nc]);
                    }

                    if (nextMask == targetMask) {
                        return moves + 1;
                    }

                    if (nextE > maxEnergy[nr][nc][nextMask]) {
                        maxEnergy[nr][nc][nextMask] = nextE;
                        queue.offer(new int[]{nr, nc, nextMask, nextE});
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}