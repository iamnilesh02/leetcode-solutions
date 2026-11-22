import java.util.*;

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(b[1], a[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int[] tails = new int[envelopes.length];
        int size = 0;

        for (int[] env : envelopes) {
            int height = env[1];
            int idx = Arrays.binarySearch(tails, 0, size, height);
            if (idx < 0) {
                idx = -(idx + 1);
            }
            tails[idx] = height;
            if (idx == size) {
                size++;
            }
        }

        return size;
    }
}