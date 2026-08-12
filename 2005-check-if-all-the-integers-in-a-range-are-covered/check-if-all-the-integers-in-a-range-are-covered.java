class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] line = new int[52];

        for (int[] range : ranges) {
            line[range[0]]++;
            line[range[1] + 1]--;
        }

        int currentCoverage = 0;
        for (int i = 1; i <= 50; i++) {
            currentCoverage += line[i];
            if (i >= left && i <= right && currentCoverage <= 0) {
                return false;
            }
        }

        return true;
    }
}