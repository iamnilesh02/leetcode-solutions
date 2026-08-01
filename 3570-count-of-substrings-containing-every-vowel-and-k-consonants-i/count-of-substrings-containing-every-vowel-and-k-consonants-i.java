import java.util.HashMap;
import java.util.Map;

class Solution {
    public int countOfSubstrings(String word, int k) {
        return atLeast(word, k) - atLeast(word, k + 1);
    }

    private int atLeast(String word, int k) {
        int count = 0;
        int left = 0;
        int consonants = 0;
        Map<Character, Integer> vowels = new HashMap<>();

        for (int right = 0; right < word.length(); right++) {
            char ch = word.charAt(right);
            if (isVowel(ch)) {
                vowels.put(ch, vowels.getOrDefault(ch, 0) + 1);
            } else {
                consonants++;
            }

            while (vowels.size() == 5 && consonants >= k) {
                count += word.length() - right;
                char leftChar = word.charAt(left);
                if (isVowel(leftChar)) {
                    vowels.put(leftChar, vowels.get(leftChar) - 1);
                    if (vowels.get(leftChar) == 0) {
                        vowels.remove(leftChar);
                    }
                } else {
                    consonants--;
                }
                left++;
            }
        }

        return count;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}