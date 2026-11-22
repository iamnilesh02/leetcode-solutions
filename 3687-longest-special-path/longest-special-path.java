import java.util.*;

class Solution {
    private int maxLen = 0;
    private int minNodes = 1;
    private List<int[]>[] adj;
    private int[] nums;
    private List<Integer> prefixDist;
    private Map<Integer, Integer> lastDepth;

    public int[] longestSpecialPath(int[][] edges, int[] nums) {
        int n = nums.length;
        this.nums = nums;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
            adj[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        prefixDist = new ArrayList<>();
        prefixDist.add(0);
        lastDepth = new HashMap<>();

        dfs(0, -1, 0, 0);

        return new int[]{maxLen, minNodes};
    }

    private void dfs(int u, int p, int depth, int startDepth) {
        int val = nums[u];
        int prevDepth = lastDepth.getOrDefault(val, -1);

        if (prevDepth >= startDepth) {
            startDepth = prevDepth + 1;
        }

        int currentLen = prefixDist.get(depth) - prefixDist.get(startDepth);
        int currentNodes = depth - startDepth + 1;

        if (currentLen > maxLen) {
            maxLen = currentLen;
            minNodes = currentNodes;
        } else if (currentLen == maxLen) {
            minNodes = Math.min(minNodes, currentNodes);
        }

        lastDepth.put(val, depth);

        for (int[] edge : adj[u]) {
            int v = edge[0];
            int w = edge[1];
            if (v != p) {
                prefixDist.add(prefixDist.get(depth) + w);
                dfs(v, u, depth + 1, startDepth);
                prefixDist.remove(prefixDist.size() - 1);
            }
        }

        if (prevDepth == -1) {
            lastDepth.remove(val);
        } else {
            lastDepth.put(val, prevDepth);
        }
    }
}