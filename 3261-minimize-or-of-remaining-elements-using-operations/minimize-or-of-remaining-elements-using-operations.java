class Solution {
    public int minOrAfterOperations(int[] nums, int k) {
        int ans = 0;
        int mask = 0;

        for (int bit = 29; bit >= 0; bit--) {
            int targetMask = mask | (1 << bit);
            int ops = 0;
            int cur = -1;

            for (int num : nums) {
                cur &= (num & targetMask);
                if (cur != 0) {
                    ops++;
                } else {
                    cur = -1;
                }
            }

            if (ops <= k) {
                mask = targetMask;
            } else {
                ans |= (1 << bit);
            }
        }

        return ans;
    }
}