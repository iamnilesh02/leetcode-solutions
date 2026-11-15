import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squares);

        int negCount = n / 2;
        long ans = 0;
        for (int i = 0; i < negCount; i++) {
            ans -= squares[i];
        }
        for (int i = negCount; i < n; i++) {
            ans += squares[i];
        }

        return ans;
    }
}