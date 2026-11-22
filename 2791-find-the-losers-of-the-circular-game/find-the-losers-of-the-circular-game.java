import java.util.*;

class Solution {
    public int[] circularGameLosers(int n, int k) {
        boolean[] visited = new boolean[n];
        int curr = 0;
        int step = 1;

        while (!visited[curr]) {
            visited[curr] = true;
            curr = (curr + step * k) % n;
            step++;
        }

        List<Integer> losers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                losers.add(i + 1);
            }
        }

        int[] ans = new int[losers.size()];
        for (int i = 0; i < losers.size(); i++) {
            ans[i] = losers.get(i);
        }

        return ans;
    }
}