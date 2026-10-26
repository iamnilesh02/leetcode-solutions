import java.util.*;

class Solution {
    private int[] tree;

    public List<Boolean> getResults(int[][] queries) {
        int maxCoord = 0;
        for (int[] q : queries) {
            maxCoord = Math.max(maxCoord, q[1]);
        }
        int m = maxCoord + 1;
        tree = new int[4 * (m + 1)];

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);

        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {
            int type = q[0];
            int x = q[1];

            if (type == 1) {
                Integer prev = obstacles.lower(x);
                Integer next = obstacles.higher(x);

                obstacles.add(x);
                update(1, 0, m, x, x - prev);
                if (next != null) {
                    update(1, 0, m, next, next - x);
                }
            } else {
                int sz = q[2];
                int p = obstacles.floor(x);
                int maxGap = Math.max(query(1, 0, m, 0, p), x - p);
                ans.add(maxGap >= sz);
            }
        }

        return ans;
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
    }

    private int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return Math.max(query(2 * node, start, mid, l, r), query(2 * node + 1, mid + 1, end, l, r));
    }
}