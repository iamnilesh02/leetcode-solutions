class Solution {
    private int[] parent;

    private int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent[node]); 
    }

    private boolean union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU == rootV) return false;
        parent[rootU] = rootV;
        return true;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            if (!union(edge[0], edge[1])) {
                return edge;
            }
        }

        return new int[0];
    }
}