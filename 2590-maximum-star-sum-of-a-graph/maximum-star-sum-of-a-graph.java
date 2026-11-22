import java.util.*;

class Solution {
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        int n = vals.length;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (vals[v] > 0) {
                adj[u].add(vals[v]);
            }
            if (vals[u] > 0) {
                adj[v].add(vals[u]);
            }
        }

        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            List<Integer> neighbors = adj[i];
            neighbors.sort(Collections.reverseOrder());

            int currentSum = vals[i];
            int limit = Math.min(k, neighbors.size());
            for (int j = 0; j < limit; j++) {
                currentSum += neighbors.get(j);
            }

            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}