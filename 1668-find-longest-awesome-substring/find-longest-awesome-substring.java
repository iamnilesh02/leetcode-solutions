import java.util.Arrays;

class Solution {
    public int longestAwesome(String s) {
        int[] firstSeen = new int[1024];
        Arrays.fill(firstSeen, -2);
        firstSeen[0] = -1;

        int mask = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            int digit = s.charAt(i) - '0';
            mask ^= (1 << digit);

            if (firstSeen[mask] != -2) {
                maxLen = Math.max(maxLen, i - firstSeen[mask]);
            } else {
                firstSeen[mask] = i;
            }

            for (int d = 0; d < 10; d++) {
                int targetMask = mask ^ (1 << d);
                if (firstSeen[targetMask] != -2) {
                    maxLen = Math.max(maxLen, i - firstSeen[targetMask]);
                }
            }
        }

        return maxLen;
    }
}