import java.util.*;

class Solution {
    public int closestToTarget(int[] arr, int target) {
        int minDiff = Integer.MAX_VALUE;
        Set<Integer> prevAnds = new HashSet<>();

        for (int num : arr) {
            Set<Integer> currAnds = new HashSet<>();
            currAnds.add(num);
            for (int val : prevAnds) {
                currAnds.add(val & num);
            }
            for (int val : currAnds) {
                minDiff = Math.min(minDiff, Math.abs(val - target));
            }
            if (minDiff == 0) {
                return 0;
            }
            prevAnds = currAnds;
        }

        return minDiff;
    }
}