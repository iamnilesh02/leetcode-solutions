import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    public List<List<Long>> splitPainting(int[][] segments) {
        TreeMap<Integer, Long> line = new TreeMap<>();

        for (int[] segment : segments) {
            int start = segment[0];
            int end = segment[1];
            int color = segment[2];

            line.put(start, line.getOrDefault(start, 0L) + color);
            line.put(end, line.getOrDefault(end, 0L) - color);
        }

        List<List<Long>> ans = new ArrayList<>();
        long runningMix = 0;
        int prevIndex = 0;

        for (Map.Entry<Integer, Long> entry : line.entrySet()) {
            int curIndex = entry.getKey();
            long change = entry.getValue();

            if (runningMix > 0) {
                ans.add(List.of((long) prevIndex, (long) curIndex, runningMix));
            }

            runningMix += change;
            prevIndex = curIndex;
        }

        return ans;
    }
}