import java.util.*;

class Solution {
    public int countStaircase(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        for (int x : nums) {
            if (x > maxVal) {
                maxVal = x;
            }
        }

        int maxK = 0;
        while ((maxK + 1) * (maxK + 2) / 2 <= n) {
            maxK++;
        }

        int ans = 0;
        int[] freq = new int[maxVal + 1];

        for (int k = 1; k <= maxK; k++) {
            int len = k * (k + 1) / 2;
            Arrays.fill(freq, 0);
            int distinctCount = 0;
            int validFreqCount = 0;

            for (int i = 0; i < n; i++) {
                int inVal = nums[i];
                int oldIn = freq[inVal];
                if (oldIn == 0) {
                    distinctCount++;
                }
                if (oldIn >= 1 && oldIn <= k) {
                    validFreqCount--;
                }
                int newIn = oldIn + 1;
                freq[inVal] = newIn;
                if (newIn >= 1 && newIn <= k) {
                    validFreqCount++;
                }

                if (i >= len) {
                    int outVal = nums[i - len];
                    int oldOut = freq[outVal];
                    if (oldOut >= 1 && oldOut <= k) {
                        validFreqCount--;
                    }
                    int newOut = oldOut - 1;
                    freq[outVal] = newOut;
                    if (newOut == 0) {
                        distinctCount--;
                    }
                    if (newOut >= 1 && newOut <= k) {
                        validFreqCount++;
                    }
                }

                if (i >= len - 1 && distinctCount == k && validFreqCount == k) {
                    int start = i - len + 1;
                    int expected = 1;
                    boolean ok = true;
                    for (int j = start; j <= i; j++) {
                        int f = freq[nums[j]];
                        if (f == expected) {
                            expected++;
                        } else if (f > expected) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok && expected == k + 1) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}