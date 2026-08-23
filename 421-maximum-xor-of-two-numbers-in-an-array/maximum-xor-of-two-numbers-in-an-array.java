class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[2];
    }

    private final TrieNode root = new TrieNode();

    private void insert(int num) {
        TrieNode node = root;
        for (int i = 30; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (node.children[bit] == null) {
                node.children[bit] = new TrieNode();
            }
            node = node.children[bit];
        }
    }

    private int getMaxXOR(int num) {
        TrieNode node = root;
        int maxVal = 0;
        for (int i = 30; i >= 0; i--) {
            int bit = (num >> i) & 1;
            int oppositeBit = 1 - bit;
            if (node.children[oppositeBit] != null) {
                maxVal |= (1 << i);
                node = node.children[oppositeBit];
            } else {
                node = node.children[bit];
            }
        }
        return maxVal;
    }

    public int findMaximumXOR(int[] nums) {
        for (int num : nums) {
            insert(num);
        }

        int maxXor = 0;
        for (int num : nums) {
            maxXor = Math.max(maxXor, getMaxXOR(num));
        }

        return maxXor;
    }
}