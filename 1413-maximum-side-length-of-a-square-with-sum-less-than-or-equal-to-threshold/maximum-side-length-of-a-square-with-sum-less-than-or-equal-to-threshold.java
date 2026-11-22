class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] prefix = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = mat[i - 1][j - 1]
                             + prefix[i - 1][j]
                             + prefix[i][j - 1]
                             - prefix[i - 1][j - 1];
            }
        }

        int maxSide = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int nextSide = maxSide + 1;
                if (i >= nextSide && j >= nextSide) {
                    int sum = prefix[i][j]
                            - prefix[i - nextSide][j]
                            - prefix[i][j - nextSide]
                            + prefix[i - nextSide][j - nextSide];
                    if (sum <= threshold) {
                        maxSide = nextSide;
                    }
                }
            }
        }

        return maxSide;
    }
}