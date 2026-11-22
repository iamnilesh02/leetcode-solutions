class Solution {
    private int[] parent;

    private int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    private boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
            return true;
        }
        return false;
    }

    public int maxStability(int n, int[][] edges, int k) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int mustEdgesCount = 0;
        int minMustStrength = Integer.MAX_VALUE;
        int maxStrength = 0;

        for (int[] e : edges) {
            maxStrength = Math.max(maxStrength, e[2] * 2);
            if (e[3] == 1) {
                if (!union(e[0], e[1])) {
                    return -1;
                }
                mustEdgesCount++;
                minMustStrength = Math.min(minMustStrength, e[2]);
            }
        }

        int totalComponents = n - mustEdgesCount;
        for (int[] e : edges) {
            if (e[3] == 0) {
                if (union(e[0], e[1])) {
                    totalComponents--;
                }
            }
        }
        if (totalComponents > 1) {
            return -1;
        }

        int left = 1;
        int right = Math.min(minMustStrength, maxStrength);
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canAchieve(n, edges, k, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private boolean canAchieve(int n, int[][] edges, int k, int target) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int edgesUsed = 0;

        for (int[] e : edges) {
            if (e[3] == 1) {
                if (e[2] < target) {
                    return false;
                }
                union(e[0], e[1]);
                edgesUsed++;
            }
        }

        for (int[] e : edges) {
            if (e[3] == 0 && e[2] >= target) {
                if (union(e[0], e[1])) {
                    edgesUsed++;
                }
            }
        }

        int upgradesUsed = 0;
        for (int[] e : edges) {
            if (e[3] == 0 && e[2] < target && e[2] * 2 >= target) {
                if (union(e[0], e[1])) {
                    edgesUsed++;
                    upgradesUsed++;
                    if (upgradesUsed > k) {
                        return false;
                    }
                }
            }
        }

        return edgesUsed == n - 1;
    }
}