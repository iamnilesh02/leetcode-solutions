import java.util.HashMap;
import java.util.Map;

class Solution {
    public long countBadPairs(int[] nums) {
        long n = nums.length;
        long totalPairs = n * (n - 1) / 2;
        long goodPairs = 0;

        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int diff = nums[i] - i;
            int count = freq.getOrDefault(diff, 0);
            goodPairs += count;
            freq.put(diff, count + 1);
        }

        return totalPairs - goodPairs;
    }
}