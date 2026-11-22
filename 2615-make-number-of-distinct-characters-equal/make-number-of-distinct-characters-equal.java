class Solution {
    public boolean isItPossible(String word1, String word2) {
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        int distinct1 = 0;
        int distinct2 = 0;

        for (int i = 0; i < word1.length(); i++) {
            int idx = word1.charAt(i) - 'a';
            if (count1[idx]++ == 0) {
                distinct1++;
            }
        }

        for (int i = 0; i < word2.length(); i++) {
            int idx = word2.charAt(i) - 'a';
            if (count2[idx]++ == 0) {
                distinct2++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (count1[i] == 0) {
                continue;
            }
            for (int j = 0; j < 26; j++) {
                if (count2[j] == 0) {
                    continue;
                }
                if (i == j) {
                    if (distinct1 == distinct2) {
                        return true;
                    }
                } else {
                    int newDistinct1 = distinct1 - (count1[i] == 1 ? 1 : 0) + (count1[j] == 0 ? 1 : 0);
                    int newDistinct2 = distinct2 - (count2[j] == 1 ? 1 : 0) + (count2[i] == 0 ? 1 : 0);
                    if (newDistinct1 == newDistinct2) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}