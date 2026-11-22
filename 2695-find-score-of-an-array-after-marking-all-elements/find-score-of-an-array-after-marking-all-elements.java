import java.util.*;

class Solution {
    public long findScore(int[] nums) {
        int n = nums.length;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }

        Arrays.sort(pairs, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        boolean[] marked = new boolean[n];
        long score = 0;

        for (int[] pair : pairs) {
            int val = pair[0];
            int idx = pair[1];
            if (!marked[idx]) {
                score += val;
                marked[idx] = true;
                if (idx - 1 >= 0) {
                    marked[idx - 1] = true;
                }
                if (idx + 1 < n) {
                    marked[idx + 1] = true;
                }
            }
        }

        return score;
    }
}