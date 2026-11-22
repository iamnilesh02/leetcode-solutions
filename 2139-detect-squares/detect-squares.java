import java.util.*;

class DetectSquares {
    private int[][] counts;
    private List<int[]> points;

    public DetectSquares() {
        counts = new int[1001][1001];
        points = new ArrayList<>();
    }

    public void add(int[] point) {
        counts[point[0]][point[1]]++;
        points.add(point);
    }

    public int count(int[] point) {
        int x1 = point[0];
        int y1 = point[1];
        int total = 0;

        for (int[] p : points) {
            int x3 = p[0];
            int y3 = p[1];

            if (x1 != x3 && Math.abs(x1 - x3) == Math.abs(y1 - y3)) {
                total += counts[x1][y3] * counts[x3][y1];
            }
        }

        return total;
    }
}
