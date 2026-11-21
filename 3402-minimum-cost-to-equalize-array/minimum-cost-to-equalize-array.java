class Solution {
    public int minCostToEqualizeArray(int[] nums, int cost1, int cost2) {
        long MOD = 1_000_000_007L;
        int n = nums.length;
        long maxVal = 0;
        long minVal = Long.MAX_VALUE;
        long sum = 0;

        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            minVal = Math.min(minVal, num);
            sum += num;
        }

        long totalDiff = maxVal * n - sum;
        if (cost2 >= 2 * cost1 || n <= 2) {
            return (int) ((totalDiff * cost1) % MOD);
        }

        long minCost = Long.MAX_VALUE;

        for (long target = maxVal; target <= 2 * maxVal; target++) {
            long d = target * n - sum;
            long maxGap = target - minVal;
            long rest = d - maxGap;
            long currentCost;

            if (maxGap > rest) {
                currentCost = rest * cost2 + (maxGap - rest) * cost1;
            } else {
                currentCost = (d / 2) * cost2 + (d % 2) * cost1;
            }

            minCost = Math.min(minCost, currentCost);
        }

        return (int) (minCost % MOD);
    }
}