import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private List<Integer>[] newAdj;
    private int[] lastSeen;
    private int[] ans;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        adj = new ArrayList[n];
        newAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            newAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        dfsBuild(0, parent);

        ans = new int[n];
        dfsSize(0);
        return ans;
    }

    private void dfsBuild(int u, int[] parent) {
        int c = s.charAt(u) - 'a';
        int prev = lastSeen[c];

        if (u != 0) {
            int p = (prev != -1) ? prev : parent[u];
            newAdj[p].add(u);
        }

        lastSeen[c] = u;
        for (int v : adj[u]) {
            dfsBuild(v, parent);
        }
        lastSeen[c] = prev;
    }

    private int dfsSize(int u) {
        int size = 1;
        for (int v : newAdj[u]) {
            size += dfsSize(v);
        }
        ans[u] = size;
        return size;
    }
}
