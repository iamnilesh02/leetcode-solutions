import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
        }

        int ans = 1;
        if (freq.containsKey(1L)) {
            int ones = freq.get(1L);
            ans = (ones % 2 == 1) ? ones : ones - 1;
        }

        for (long key : freq.keySet()) {
            if (key == 1L) continue;

            long curr = key;
            int len = 0;

            while (freq.containsKey(curr)) {
                int count = freq.get(curr);
                if (count >= 2) {
                    len += 2;
                } else {
                    len += 1;
                    break;
                }
                if (curr > 100000L) break;
                curr = curr * curr;
            }

            if (len % 2 == 0) {
                len -= 1;
            }
            ans = Math.max(ans, len);
        }

        return ans;
    }
}