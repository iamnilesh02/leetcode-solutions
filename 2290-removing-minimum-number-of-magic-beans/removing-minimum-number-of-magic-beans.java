import java.util.Arrays;

class Solution {
    public long minimumRemoval(int[] beans) {
        Arrays.sort(beans);
        long total = 0;
        for (int b : beans) {
            total += b;
        }

        long maxRemaining = 0;
        int n = beans.length;
        for (int i = 0; i < n; i++) {
            long currentRemaining = (long) beans[i] * (n - i);
            if (currentRemaining > maxRemaining) {
                maxRemaining = currentRemaining;
            }
        }

        return total - maxRemaining;
    }
}