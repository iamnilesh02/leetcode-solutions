class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private final TrieNode root = new TrieNode();

    private void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    private String findShortestRoot(String word) {
        TrieNode node = root;
        StringBuilder prefix = new StringBuilder();

        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                break;
            }
            prefix.append(c);
            node = node.children[idx];
            if (node.isEnd) {
                return prefix.toString();
            }
        }
        return word;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        for (String rootWord : dictionary) {
            insert(rootWord);
        }

        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = findShortestRoot(words[i]);
        }

        return String.join(" ", words);
    }
}