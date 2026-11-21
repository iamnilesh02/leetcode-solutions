import java.util.*;

class Solution {
    public List<List<Integer>> aggregateTimeSeries(int[][] series1, int[][] series2) {
        List<List<Integer>> res = new ArrayList<>();
        int i = 0, j = 0;
        int n = series1.length, m = series2.length;

        while (i < n && j < m) {
            int t1 = series1[i][0], v1 = series1[i][1];
            int t2 = series2[j][0], v2 = series2[j][1];

            if (t1 == t2) {
                res.add(Arrays.asList(t1, v1 + v2));
                i++;
                j++;
            } else if (t1 < t2) {
                res.add(Arrays.asList(t1, v1 + v2));
                i++;
            } else {
                res.add(Arrays.asList(t2, v1 + v2));
                j++;
            }
        }

        while (i < n) {
            res.add(Arrays.asList(series1[i][0], series1[i][1]));
            i++;
        }

        while (j < m) {
            res.add(Arrays.asList(series2[j][0], series2[j][1]));
            j++;
        }

        return res;
    }
}