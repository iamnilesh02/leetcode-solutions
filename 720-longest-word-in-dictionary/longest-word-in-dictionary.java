class Solution {
    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> builtWords = new HashSet<>();
        builtWords.add("");
        String longest = "";

        for (String word : words) {
            String prefix = word.substring(0, word.length() - 1);
            if (builtWords.contains(prefix)) {
                builtWords.add(word);
                if (word.length() > longest.length()) {
                    longest = word;
                }
            }
        }

        return longest;
    }
}