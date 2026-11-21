class Solution {
    public int minOperations(int[] nums) {
        int ops = 0;
        for (int num : nums) {
            if ((num ^ (ops & 1)) == 0) {
                ops++;
            }
        }
        return ops;
    }
}