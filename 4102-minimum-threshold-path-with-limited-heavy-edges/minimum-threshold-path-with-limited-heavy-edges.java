import java.util.*;

class Solution {
    public int minimumThreshold(int n, int[][] edges, int source, int target, int k) {
        if (source == target) {
            return 0;
        }

        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        int maxWeight = 0;
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            adj[e[1]].add(new int[]{e[0], e[2]});
            maxWeight = Math.max(maxWeight, e[2]);
        }

        int left = 0;
        int right = maxWeight;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canReach(n, adj, source, target, k, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean canReach(int n, List<int[]>[] adj, int source, int target, int k, int threshold) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerFirst(source);

        while (!dq.isEmpty()) {
            int u = dq.pollFirst();
            if (u == target) {
                return dist[u] <= k;
            }

            for (int[] edge : adj[u]) {
                int v = edge[0];
                int weight = edge[1];
                int cost = weight > threshold ? 1 : 0;

                if (dist[u] + cost < dist[v] && dist[u] + cost <= k) {
                    dist[v] = dist[u] + cost;
                    if (cost == 0) {
                        dq.offerFirst(v);
                    } else {
                        dq.offerLast(v);
                    }
                }
            }
        }

        return dist[target] <= k;
    }
}