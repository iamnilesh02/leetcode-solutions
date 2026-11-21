import java.util.*;

class Solution {
    public int maxSubarray(int[] nums) {
        int n = nums.length;
        int maxLen = 0;
        int left = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> pairSums = new HashMap<>();

        for (int right = 0; right < n; right++) {
            int x = nums[right];

            for (int val : freq.keySet()) {
                int s = val + x;
                pairSums.put(s, pairSums.getOrDefault(s, 0) + freq.get(val));
            }
            freq.put(x, freq.getOrDefault(x, 0) + 1);

            while (!isValid(freq, pairSums)) {
                int y = nums[left];
                freq.put(y, freq.get(y) - 1);
                if (freq.get(y) == 0) {
                    freq.remove(y);
                }
                for (int val : freq.keySet()) {
                    int s = val + y;
                    int cnt = pairSums.get(s) - freq.get(val);
                    if (cnt == 0) {
                        pairSums.remove(s);
                    } else {
                        pairSums.put(s, cnt);
                    }
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    private boolean isValid(Map<Integer, Integer> freq, Map<Integer, Integer> pairSums) {
        for (int k : freq.keySet()) {
            int totalPairs = pairSums.getOrDefault(k, 0);
            if (totalPairs > 0) {
                int selfPairs = 0;
                if (k % 2 == 0) {
                    int half = k / 2;
                    int c = freq.getOrDefault(half, 0);
                    if (half == k) {
                        selfPairs = c * (c - 1) / 2;
                    }
                }
                int zeroCount = freq.getOrDefault(0, 0);
                if (k == 0) {
                    selfPairs = zeroCount * (zeroCount - 1) / 2;
                } else {
                    selfPairs = zeroCount;
                }

                if (totalPairs > selfPairs) {
                    return false;
                }
            }
        }
        return true;
    }
}