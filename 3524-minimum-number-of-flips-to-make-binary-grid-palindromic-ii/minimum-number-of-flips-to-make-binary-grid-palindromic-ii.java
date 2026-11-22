class Solution {
    public int minFlips(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int flips = 0;

        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int ones = grid[i][j] 
                         + grid[i][n - 1 - j] 
                         + grid[m - 1 - i][j] 
                         + grid[m - 1 - i][n - 1 - j];
                flips += Math.min(ones, 4 - ones);
            }
        }

        int diffPairs = 0;
        int onePairs = 0;

        if (m % 2 == 1) {
            int midRow = m / 2;
            for (int j = 0; j < n / 2; j++) {
                if (grid[midRow][j] != grid[midRow][n - 1 - j]) {
                    diffPairs++;
                } else if (grid[midRow][j] == 1) {
                    onePairs++;
                }
            }
        }

        if (n % 2 == 1) {
            int midCol = n / 2;
            for (int i = 0; i < m / 2; i++) {
                if (grid[i][midCol] != grid[m - 1 - i][midCol]) {
                    diffPairs++;
                } else if (grid[i][midCol] == 1) {
                    onePairs++;
                }
            }
        }

        if (m % 2 == 1 && n % 2 == 1) {
            if (grid[m / 2][n / 2] == 1) {
                flips++;
            }
        }

        if (diffPairs > 0) {
            flips += diffPairs;
        } else if (onePairs % 2 == 1) {
            flips += 2;
        }

        return flips;
    }
}