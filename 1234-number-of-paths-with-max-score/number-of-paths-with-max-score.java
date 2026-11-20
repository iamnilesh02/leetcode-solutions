import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int mod = 1_000_000_007;

        int[][] maxScore = new int[n][n];
        int[][] paths = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxScore[i][j] = -1;
            }
        }

        maxScore[n - 1][n - 1] = 0;
        paths[n - 1][n - 1] = 1;

        int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};

        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (r == n - 1 && c == n - 1) continue;
                char ch = board.get(r).charAt(c);
                if (ch == 'X') continue;

                int val = (ch == 'E') ? 0 : (ch - '0');

                for (int[] d : dirs) {
                    int pr = r + d[0];
                    int pc = c + d[1];
                    if (pr < n && pc < n && maxScore[pr][pc] != -1) {
                        int candidateScore = maxScore[pr][pc] + val;
                        if (candidateScore > maxScore[r][c]) {
                            maxScore[r][c] = candidateScore;
                            paths[r][c] = paths[pr][pc];
                        } else if (candidateScore == maxScore[r][c]) {
                            paths[r][c] = (paths[r][c] + paths[pr][pc]) % mod;
                        }
                    }
                }
            }
        }

        if (maxScore[0][0] == -1) {
            return new int[]{0, 0};
        }

        return new int[]{maxScore[0][0], paths[0][0]};
    }
}