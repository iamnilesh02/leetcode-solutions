import java.util.*;

class Solution {
    private int[] parent;

    private int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        TreeMap<Integer, List<Integer>> valToNodes = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            valToNodes.computeIfAbsent(vals[i], k -> new ArrayList<>()).add(i);
        }

        int goodPaths = 0;

        for (int val : valToNodes.keySet()) {
            List<Integer> nodes = valToNodes.get(val);
            for (int u : nodes) {
                for (int v : adj[u]) {
                    if (vals[v] <= vals[u]) {
                        int rootU = find(u);
                        int rootV = find(v);
                        if (rootU != rootV) {
                            parent[rootU] = rootV;
                        }
                    }
                }
            }

            Map<Integer, Integer> groupCounts = new HashMap<>();
            for (int u : nodes) {
                int root = find(u);
                groupCounts.put(root, groupCounts.getOrDefault(root, 0) + 1);
            }

            for (int count : groupCounts.values()) {
                goodPaths += count * (count + 1) / 2;
            }
        }

        return goodPaths;
    }
}