class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        int[] targetCount = new int[128];
        for (char c : t.toCharArray()) {
            targetCount[c]++;
        }

        int[] windowCount = new int[128];
        int required = 0;
        for (int i = 0; i < 128; i++) {
            if (targetCount[i] > 0) required++;
        }

        int left = 0, right = 0, formed = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            windowCount[c]++;

            if (targetCount[c] > 0 && windowCount[c] == targetCount[c]) {
                formed++;
            }

            while (left <= right && formed == required) {
                c = s.charAt(left);

                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                windowCount[c]--;
                if (targetCount[c] > 0 && windowCount[c] < targetCount[c]) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}