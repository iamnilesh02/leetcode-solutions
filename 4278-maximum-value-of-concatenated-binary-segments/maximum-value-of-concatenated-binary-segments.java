import java.util.*;

class Solution {
    public int maxValue(int[] nums1, int[] nums0) {
        int n = nums1.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        Arrays.sort(idx, (a, b) -> {
            if (nums0[a] == 0 && nums0[b] == 0) return 0;
            if (nums0[a] == 0) return -1;
            if (nums0[b] == 0) return 1;
            if (nums1[a] != nums1[b]) {
                return Integer.compare(nums1[b], nums1[a]);
            }
            return Integer.compare(nums0[a], nums0[b]);
        });

        long MOD = 1_000_000_007L;
        long ans = 0;

        for (int i : idx) {
            int ones = nums1[i];
            int zeros = nums0[i];

            if (ones > 0) {
                long p2 = modPow(2, ones, MOD);
                ans = (ans * p2 + (p2 - 1 + MOD) % MOD) % MOD;
            }
            if (zeros > 0) {
                long p2 = modPow(2, zeros, MOD);
                ans = (ans * p2) % MOD;
            }
        }

        return (int) ans;
    }

    private long modPow(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}