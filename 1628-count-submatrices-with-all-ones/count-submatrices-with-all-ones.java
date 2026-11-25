import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] heights = new int[n];
        int total = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                heights[j] = mat[i][j] == 0 ? 0 : heights[j] + 1;
            }
            total += countRowSubmatrices(heights);
        }

        return total;
    }

    private int countRowSubmatrices(int[] heights) {
        int n = heights.length;
        int[] sum = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        int rowTotal = 0;

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                int prev = stack.peek();
                sum[i] = sum[prev] + heights[i] * (i - prev);
            } else {
                sum[i] = heights[i] * (i + 1);
            }

            stack.push(i);
            rowTotal += sum[i];
        }

        return rowTotal;
    }
}