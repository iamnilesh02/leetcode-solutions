class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int[] remainderCount = new int[k];
        remainderCount[0] = 1;

        int runningSum = 0;
        int count = 0;

        for (int num : nums) {
            runningSum += num;
            int remainder = runningSum % k;

            if (remainder < 0) {
                remainder += k;
            }

            count += remainderCount[remainder];
            remainderCount[remainder]++;
        }

        return count;
    }
}