import java.util.*;

class Solution {
    private int LOG;
    private int[][] up;
    private int[] depth;
    private long[] dist;
    private List<int[]>[] adj;

    @SuppressWarnings("unchecked")
    public int[] findMedian(int n, int[][] edges, int[][] queries) {
        LOG = 18;
        up = new int[n][LOG];
        depth = new int[n];
        dist = new long[n];
        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            Arrays.fill(up[i], -1);
        }

        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            adj[e[1]].add(new int[]{e[0], e[2]});
        }

        dfs(0, -1, 0, 0L);

        int q = queries.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            int lca = getLCA(u, v);

            long totalDist = dist[u] + dist[v] - 2 * dist[lca];
            long target = (totalDist + 1) / 2;

            long distUtoLCA = dist[u] - dist[lca];
            if (distUtoLCA >= target) {
                ans[i] = liftUntilDist(u, target);
            } else {
                long rem = totalDist - target;
                ans[i] = liftMaxWithinDist(v, rem);
            }
        }

        return ans;
    }

    private void dfs(int u, int p, int d, long currentDist) {
        depth[u] = d;
        dist[u] = currentDist;
        up[u][0] = p;

        for (int j = 1; j < LOG; j++) {
            if (up[u][j - 1] != -1) {
                up[u][j] = up[up[u][j - 1]][j - 1];
            }
        }

        for (int[] edge : adj[u]) {
            int v = edge[0];
            int w = edge[1];
            if (v != p) {
                dfs(v, u, d + 1, currentDist + w);
            }
        }
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];
        for (int j = 0; j < LOG; j++) {
            if (((diff >> j) & 1) == 1) {
                u = up[u][j];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }

        return up[u][0];
    }

    private int liftUntilDist(int u, long target) {
        if (target == 0) return u;
        long startDist = dist[u];
        int curr = u;

        for (int j = LOG - 1; j >= 0; j--) {
            int anc = up[curr][j];
            if (anc != -1 && startDist - dist[anc] < target) {
                curr = anc;
            }
        }
        return up[curr][0];
    }

    private int liftMaxWithinDist(int v, long maxAllowed) {
        long startDist = dist[v];
        int curr = v;

        for (int j = LOG - 1; j >= 0; j--) {
            int anc = up[curr][j];
            if (anc != -1 && startDist - dist[anc] <= maxAllowed) {
                curr = anc;
            }
        }
        return curr;
    }
}