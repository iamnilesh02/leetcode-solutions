class Solution {
    private int m, n;
    private int maxMask;
    private int[] pow3;
    private int[][][][] memo;

    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        this.m = m;
        this.n = n;
        pow3 = new int[n + 1];
        pow3[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow3[i] = pow3[i - 1] * 3;
        }
        maxMask = pow3[n];
        memo = new int[m * n][introvertsCount + 1][extrovertsCount + 1][maxMask];
        for (int i = 0; i < m * n; i++) {
            for (int j = 0; j <= introvertsCount; j++) {
                for (int k = 0; k <= extrovertsCount; k++) {
                    for (int l = 0; l < maxMask; l++) {
                        memo[i][j][k][l] = -1;
                    }
                }
            }
        }
        return dfs(0, introvertsCount, extrovertsCount, 0);
    }

    private int dfs(int pos, int intro, int extro, int mask) {
        if (pos == m * n || (intro == 0 && extro == 0)) {
            return 0;
        }
        if (memo[pos][intro][extro][mask] != -1) {
            return memo[pos][intro][extro][mask];
        }

        int r = pos / n;
        int c = pos % n;
        int up = mask / pow3[n - 1];
        int left = (c == 0) ? 0 : (mask % 3);
        int nextMaskBase = (mask % pow3[n - 1]) * 3;

        int res = dfs(pos + 1, intro, extro, nextMaskBase);

        if (intro > 0) {
            int gain = 120 + calcDiff(1, up) + calcDiff(1, left);
            res = Math.max(res, gain + dfs(pos + 1, intro - 1, extro, nextMaskBase + 1));
        }

        if (extro > 0) {
            int gain = 40 + calcDiff(2, up) + calcDiff(2, left);
            res = Math.max(res, gain + dfs(pos + 1, intro, extro - 1, nextMaskBase + 2));
        }

        return memo[pos][intro][extro][mask] = res;
    }

    private int calcDiff(int curr, int neighbor) {
        if (neighbor == 0) {
            return 0;
        }
        int diff = 0;
        if (curr == 1) {
            diff -= 30;
        } else if (curr == 2) {
            diff += 20;
        }
        if (neighbor == 1) {
            diff -= 30;
        } else if (neighbor == 2) {
            diff += 20;
        }
        return diff;
    }
}