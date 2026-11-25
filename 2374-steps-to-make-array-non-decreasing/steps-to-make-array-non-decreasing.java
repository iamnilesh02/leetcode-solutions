import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int totalSteps(int[] nums) {
        int n = nums.length;
        int maxSteps = 0;
        Deque<int[]> stack = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {
            int steps = 0;
            while (!stack.isEmpty() && nums[i] > stack.peek()[0]) {
                steps = Math.max(steps + 1, stack.pop()[1]);
            }
            maxSteps = Math.max(maxSteps, steps);
            stack.push(new int[]{nums[i], steps});
        }

        return maxSteps;
    }
}