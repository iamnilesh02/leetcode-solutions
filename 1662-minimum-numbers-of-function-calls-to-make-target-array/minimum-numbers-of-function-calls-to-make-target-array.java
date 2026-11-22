class Solution {
    public int minOperations(int[] nums) {
        int addOps = 0;
        int maxMulOps = 0;

        for (int num : nums) {
            int mulOps = 0;
            while (num > 0) {
                if ((num & 1) == 1) {
                    addOps++;
                    num--;
                } else {
                    mulOps++;
                    num >>= 1;
                }
            }
            maxMulOps = Math.max(maxMulOps, mulOps);
        }

        return addOps + maxMulOps;
    }
}