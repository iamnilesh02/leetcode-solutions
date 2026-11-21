import java.util.*;

class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<Integer>[] adj = new ArrayList[n];
        List<List<Integer>> ans = new ArrayList<>(n);
        
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            ans.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
        }
        
        for (int i = 0; i < n; i++) {
            dfs(i, i, adj, ans, new boolean[n]);
        }
        
        return ans;
    }
    
    private void dfs(int ancestor, int curr, List<Integer>[] adj, List<List<Integer>> ans, boolean[] visited) {
        visited[curr] = true;
        for (int next : adj[curr]) {
            if (!visited[next]) {
                ans.get(next).add(ancestor);
                dfs(ancestor, next, adj, ans, visited);
            }
        }
    }
}