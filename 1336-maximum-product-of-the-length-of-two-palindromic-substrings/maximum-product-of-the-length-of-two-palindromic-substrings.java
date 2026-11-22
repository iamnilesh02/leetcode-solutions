import java.util.*;

class Solution {
    public long maxProduct(String s) {
        int n = s.length();
        int[] d = new int[n];
        int l = 0, r = -1;

        for (int i = 0; i < n; i++) {
            int k = (i > r) ? 1 : Math.min(d[l + r - i], r - i + 1);
            while (i - k >= 0 && i + k < n && s.charAt(i - k) == s.charAt(i + k)) {
                k++;
            }
            d[i] = k--;
            if (i + k > r) {
                l = i - k;
                r = i + k;
            }
        }

        int[] maxEnd = new int[n];
        Arrays.fill(maxEnd, 1);
        for (int i = 0; i < n; i++) {
            int endIdx = i + d[i] - 1;
            maxEnd[endIdx] = Math.max(maxEnd[endIdx], 2 * d[i] - 1);
        }
        for (int i = n - 2; i >= 0; i--) {
            maxEnd[i] = Math.max(maxEnd[i], maxEnd[i + 1] - 2);
        }
        for (int i = 1; i < n; i++) {
            maxEnd[i] = Math.max(maxEnd[i], maxEnd[i - 1]);
        }

        int[] maxStart = new int[n];
        Arrays.fill(maxStart, 1);
        for (int i = 0; i < n; i++) {
            int startIdx = i - d[i] + 1;
            maxStart[startIdx] = Math.max(maxStart[startIdx], 2 * d[i] - 1);
        }
        for (int i = 1; i < n; i++) {
            maxStart[i] = Math.max(maxStart[i], maxStart[i - 1] - 2);
        }
        for (int i = n - 2; i >= 0; i--) {
            maxStart[i] = Math.max(maxStart[i], maxStart[i + 1]);
        }

        long ans = 0;
        for (int i = 0; i < n - 1; i++) {
            ans = Math.max(ans, (long) maxEnd[i] * maxStart[i + 1]);
        }

        return ans;
    }
}