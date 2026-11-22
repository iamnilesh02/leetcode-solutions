import java.util.*;

class Solution {
    public long minCost(int[] arr, int[] brr, long k) {
        int n = arr.length;
        long costWithoutRearrange = 0;
        for (int i = 0; i < n; i++) {
            costWithoutRearrange += Math.abs((long) arr[i] - brr[i]);
        }

        Arrays.sort(arr);
        Arrays.sort(brr);

        long costWithRearrange = k;
        for (int i = 0; i < n; i++) {
            costWithRearrange += Math.abs((long) arr[i] - brr[i]);
        }

        return Math.min(costWithoutRearrange, costWithRearrange);
    }
}