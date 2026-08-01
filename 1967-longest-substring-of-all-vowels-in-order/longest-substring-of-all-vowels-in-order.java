class Solution {
    public int longestBeautifulSubstring(String word) {
        int maxLength = 0;
        int currentLength = 1;
        int vowelsCount = 1;

        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) >= word.charAt(i - 1)) {
                currentLength++;
                if (word.charAt(i) > word.charAt(i - 1)) {
                    vowelsCount++;
                }
            } else {
                currentLength = 1;
                vowelsCount = 1;
            }

            if (vowelsCount == 5) {
                maxLength = Math.max(maxLength, currentLength);
            }
        }

        return maxLength;
    }
}