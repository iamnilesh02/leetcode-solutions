class Solution {
    static class SegmentTree {
        int n;
        char[] s;
        int[] maxLen;
        int[] prefLen;
        int[] suffLen;

        SegmentTree(String str) {
            this.n = str.length();
            this.s = str.toCharArray();
            int treeSize = 4 * n;
            maxLen = new int[treeSize];
            prefLen = new int[treeSize];
            suffLen = new int[treeSize];
            build(1, 0, n - 1);
        }

        private void merge(int node, int l, int r, int mid) {
            int leftChild = 2 * node;
            int rightChild = 2 * node + 1;

            maxLen[node] = Math.max(maxLen[leftChild], maxLen[rightChild]);
            prefLen[node] = prefLen[leftChild];
            suffLen[node] = suffLen[rightChild];

            int leftLen = mid - l + 1;
            int rightLen = r - mid;

            if (s[mid] == s[mid + 1]) {
                maxLen[node] = Math.max(maxLen[node], suffLen[leftChild] + prefLen[rightChild]);

                if (prefLen[leftChild] == leftLen) {
                    prefLen[node] = leftLen + prefLen[rightChild];
                }
                if (suffLen[rightChild] == rightLen) {
                    suffLen[node] = rightLen + suffLen[leftChild];
                }
            }
        }

        private void build(int node, int l, int r) {
            if (l == r) {
                maxLen[node] = 1;
                prefLen[node] = 1;
                suffLen[node] = 1;
                return;
            }
            int mid = l + (r - l) / 2;
            build(2 * node, l, mid);
            build(2 * node + 1, mid + 1, r);
            merge(node, l, r, mid);
        }

        public void update(int node, int l, int r, int idx, char ch) {
            if (l == r) {
                s[idx] = ch;
                return;
            }
            int mid = l + (r - l) / 2;
            if (idx <= mid) {
                update(2 * node, l, mid, idx, ch);
            } else {
                update(2 * node + 1, mid + 1, r, idx, ch);
            }
            merge(node, l, r, mid);
        }

        public int getMaxLen() {
            return maxLen[1];
        }
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int k = queryIndices.length;
        int[] ans = new int[k];
        SegmentTree tree = new SegmentTree(s);

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            tree.update(1, 0, s.length() - 1, idx, ch);
            ans[i] = tree.getMaxLen();
        }

        return ans;
    }
}