import java.util.PriorityQueue;

class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

        for (int[] cls : classes) {
            int pass = cls[0];
            int total = cls[1];
            double gain = calculateGain(pass, total);
            maxHeap.offer(new double[]{gain, pass, total});
        }

        while (extraStudents-- > 0) {
            double[] current = maxHeap.poll();
            int pass = (int) current[1] + 1;
            int total = (int) current[2] + 1;
            double gain = calculateGain(pass, total);
            maxHeap.offer(new double[]{gain, pass, total});
        }

        double totalRatio = 0.0;
        while (!maxHeap.isEmpty()) {
            double[] current = maxHeap.poll();
            totalRatio += current[1] / current[2];
        }

        return totalRatio / classes.length;
    }

    private double calculateGain(int pass, int total) {
        return ((double) (pass + 1) / (total + 1)) - ((double) pass / total);
    }
}