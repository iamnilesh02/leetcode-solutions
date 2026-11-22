import java.util.*;

class Solution {
    public int findCrossingTime(int n, int k, int[][] time) {
        Comparator<Integer> lessEfficient = (i, j) -> {
            int sumI = time[i][0] + time[i][2];
            int sumJ = time[j][0] + time[j][2];
            if (sumI != sumJ) {
                return Integer.compare(sumJ, sumI);
            }
            return Integer.compare(j, i);
        };

        PriorityQueue<Integer> leftWait = new PriorityQueue<>(lessEfficient);
        PriorityQueue<Integer> rightWait = new PriorityQueue<>(lessEfficient);

        PriorityQueue<int[]> leftWork = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> rightWork = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < k; i++) {
            leftWait.offer(i);
        }

        int currentTime = 0;
        int boxesRemaining = n;

        while (boxesRemaining > 0 || !rightWait.isEmpty() || !rightWork.isEmpty()) {
            while (!leftWork.isEmpty() && leftWork.peek()[0] <= currentTime) {
                leftWait.offer(leftWork.poll()[1]);
            }
            while (!rightWork.isEmpty() && rightWork.peek()[0] <= currentTime) {
                rightWait.offer(rightWork.poll()[1]);
            }

            if (!rightWait.isEmpty()) {
                int worker = rightWait.poll();
                currentTime += time[worker][2];
                leftWork.offer(new int[]{currentTime + time[worker][3], worker});
            } else if (boxesRemaining > 0 && !leftWait.isEmpty()) {
                int worker = leftWait.poll();
                currentTime += time[worker][0];
                boxesRemaining--;
                rightWork.offer(new int[]{currentTime + time[worker][1], worker});
            } else {
                int nextTime = Integer.MAX_VALUE;
                if (!leftWork.isEmpty()) {
                    nextTime = Math.min(nextTime, leftWork.peek()[0]);
                }
                if (!rightWork.isEmpty()) {
                    nextTime = Math.min(nextTime, rightWork.peek()[0]);
                }
                currentTime = nextTime;
            }
        }

        return currentTime;
    }
}