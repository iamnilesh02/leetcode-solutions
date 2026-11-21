class Solution {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int min1 = 0, min2 = 0, idx1 = -1;

        for (int i = 0; i < n; i++) {
            int curMin1 = Integer.MAX_VALUE;
            int curMin2 = Integer.MAX_VALUE;
            int curIdx1 = -1;

            for (int j = 0; j < n; j++) {
                int val = grid[i][j] + (j == idx1 ? min2 : min1);

                if (val < curMin1) {
                    curMin2 = curMin1;
                    curMin1 = val;
                    curIdx1 = j;
                } else if (val < curMin2) {
                    curMin2 = val;
                }
            }

            min1 = curMin1;
            min2 = curMin2;
            idx1 = curIdx1;
        }

        return min1;
    }
}