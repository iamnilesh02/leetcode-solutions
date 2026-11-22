class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int[] remCount = new int[value];
        for (int num : nums) {
            int rem = ((num % value) + value) % value;
            remCount[rem]++;
        }

        int mex = 0;
        while (remCount[mex % value] > 0) {
            remCount[mex % value]--;
            mex++;
        }

        return mex;
    }
}