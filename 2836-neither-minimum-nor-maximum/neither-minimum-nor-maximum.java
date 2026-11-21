import java.util.*;

class Solution {
    public int findNonMinOrMax(int[] nums) {
        if (nums.length < 3) {
            return -1;
        }
        int a = nums[0], b = nums[1], c = nums[2];
        int min = Math.min(a, Math.min(b, c));
        int max = Math.max(a, Math.max(b, c));
        return a + b + c - min - max;
    }
}