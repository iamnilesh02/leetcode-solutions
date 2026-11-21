import java.util.*;

class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> arr[a[0]] * arr[b[1]] - arr[b[0]] * arr[a[1]]
        );

        for (int i = 0; i < n - 1; i++) {
            pq.offer(new int[]{i, n - 1});
        }

        while (--k > 0) {
            int[] curr = pq.poll();
            int i = curr[0];
            int j = curr[1];
            if (j - 1 > i) {
                pq.offer(new int[]{i, j - 1});
            }
        }

        int[] res = pq.poll();
        return new int[]{arr[res[0]], arr[res[1]]};
    }
}