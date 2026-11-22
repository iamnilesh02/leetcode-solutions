import java.util.*;

class Solution {
    public long minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int median = nums[n / 2];

        int p1 = median;
        while (p1 > 0 && !isPalindrome(p1)) {
            p1--;
        }

        int p2 = median;
        while (!isPalindrome(p2)) {
            p2++;
        }

        long cost1 = p1 > 0 ? calculateCost(nums, p1) : Long.MAX_VALUE;
        long cost2 = calculateCost(nums, p2);

        return Math.min(cost1, cost2);
    }

    private boolean isPalindrome(int x) {
        int rev = 0;
        int orig = x;
        while (x > 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return orig == rev;
    }

    private long calculateCost(int[] nums, int target) {
        long cost = 0;
        for (int num : nums) {
            cost += Math.abs((long) num - target);
        }
        return cost;
    }
}