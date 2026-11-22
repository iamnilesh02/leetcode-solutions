class Solution {
    public int specialArray(int[] nums) {
        int n = nums.length;
        int[] freq = new int[n + 1];

        for (int num : nums) {
            freq[Math.min(n, num)]++;
        }

        int numGreaterOrEqual = 0;
        for (int x = n; x >= 1; x--) {
            numGreaterOrEqual += freq[x];
            if (numGreaterOrEqual == x) {
                return x;
            }
        }

        return -1;
    }
}