import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[31];
        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if (((num >> bit) & 1) == 1) {
                    bitCounts[bit]++;
                }
            }
        }

        long ans = 0;
        long mod = 1_000_000_007L;

        for (int i = 0; i < k; i++) {
            long current = 0;
            for (int bit = 0; bit < 31; bit++) {
                if (bitCounts[bit] > 0) {
                    current |= (1L << bit);
                    bitCounts[bit]--;
                }
            }
            ans = (ans + (current % mod) * (current % mod)) % mod;
        }

        return (int) ans;
    }
}