class Solution {
    private static final int MOD = 1_000_000_007;

    public int distanceSum(int m, int n, int k) {
        long totalCells = (long) m * n;
        long ways = nCr((int) totalCells - 2, k - 2);

        long rowDistSum = ((long) m * (m - 1) % MOD * (m + 1) % MOD) * modInverse(6) % MOD;
        rowDistSum = rowDistSum * n % MOD * n % MOD;

        long colDistSum = ((long) n * (n - 1) % MOD * (n + 1) % MOD) * modInverse(6) % MOD;
        colDistSum = colDistSum * m % MOD * m % MOD;

        long totalPairDist = (rowDistSum + colDistSum) % MOD;
        return (int) (totalPairDist * ways % MOD);
    }

    private long nCr(int n, int r) {
        if (r < 0 || r > n) {
            return 0;
        }
        if (r == 0 || r == n) {
            return 1;
        }
        if (r > n / 2) {
            r = n - r;
        }
        long num = 1;
        long den = 1;
        for (int i = 0; i < r; i++) {
            num = num * (n - i) % MOD;
            den = den * (i + 1) % MOD;
        }
        return num * modInverse(den) % MOD;
    }

    private long modInverse(long a) {
        return power(a, MOD - 2);
    }

    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = res * base % MOD;
            }
            base = base * base % MOD;
            exp >>= 1;
        }
        return res;
    }
}