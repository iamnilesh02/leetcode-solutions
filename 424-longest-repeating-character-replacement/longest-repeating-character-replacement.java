class Solution {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0;
        int maxFrequency = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            count[current - 'A']++;
            maxFrequency = Math.max(maxFrequency, count[current - 'A']);

            int currentWindowLength = right - left + 1;

            if (currentWindowLength - maxFrequency > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}