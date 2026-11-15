import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] stats = new int[2]; // [nodes, degreeSum]
                dfs(i, adj, visited, stats);
                int k = stats[0];
                int edgeCount = stats[1] / 2;
                if (edgeCount == k * (k - 1) / 2) {
                    completeCount++;
                }
            }
        }

        return completeCount;
    }

    private void dfs(int node, List<List<Integer>> adj, boolean[] visited, int[] stats) {
        visited[node] = true;
        stats[0]++;
        stats[1] += adj.get(node).size();

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, stats);
            }
        }
    }
}