import java.util.*;

class Solution {
    private static final int MAX_BITS = 18;
    private int[][] trie;
    private int[] count;
    private int ptr;
    private List<Integer>[] adj;
    private List<int[]>[] nodeQueries;
    private int[] ans;

    @SuppressWarnings("unchecked")
    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        int n = parents.length;
        int q = queries.length;

        adj = new ArrayList[n];
        nodeQueries = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            nodeQueries[i] = new ArrayList<>();
        }

        int root = -1;
        for (int i = 0; i < n; i++) {
            if (parents[i] == -1) {
                root = i;
            } else {
                adj[parents[i]].add(i);
            }
        }

        for (int i = 0; i < q; i++) {
            nodeQueries[queries[i][0]].add(new int[]{queries[i][1], i});
        }

        int maxNodes = n * (MAX_BITS + 1) + 5;
        trie = new int[maxNodes][2];
        count = new int[maxNodes];
        ptr = 1;
        ans = new int[q];

        dfs(root);

        return ans;
    }

    private void dfs(int u) {
        insert(u, 1);

        for (int[] query : nodeQueries[u]) {
            ans[query[1]] = queryMaxXor(query[0]);
        }

        for (int v : adj[u]) {
            dfs(v);
        }

        insert(u, -1);
    }

    private void insert(int val, int delta) {
        int curr = 0;
        for (int b = MAX_BITS - 1; b >= 0; b--) {
            int bit = (val >> b) & 1;
            if (trie[curr][bit] == 0) {
                trie[curr][bit] = ptr++;
            }
            curr = trie[curr][bit];
            count[curr] += delta;
        }
    }

    private int queryMaxXor(int val) {
        int curr = 0;
        int maxXor = 0;
        for (int b = MAX_BITS - 1; b >= 0; b--) {
            int bit = (val >> b) & 1;
            int desired = bit ^ 1;
            int next = trie[curr][desired];
            if (next != 0 && count[next] > 0) {
                maxXor |= (1 << b);
                curr = next;
            } else {
                curr = trie[curr][bit];
            }
        }
        return maxXor;
    }
}