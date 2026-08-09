class Solution {
    public int splitArray(int[] nums, int k) {
        long left = 0;
        long right = 0;

        for (int num : nums) {
            left = Math.max(left, (long) num);
            right += num;
        }

        long result = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (canSplit(nums, k, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return (int) result;
    }

    private boolean canSplit(int[] nums, int k, long maxSum) {
        int count = 1;
        long currentSum = 0;

        for (int num : nums) {
            if (currentSum + num > maxSum) {
                count++;
                currentSum = num;
                if (count > k) {
                    return false;
                }
            } else {
                currentSum += num;
            }
        }

        return true;
    }
}