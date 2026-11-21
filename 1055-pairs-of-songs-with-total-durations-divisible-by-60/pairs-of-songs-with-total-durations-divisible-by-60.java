class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] rem = new int[60];
        int count = 0;

        for (int t : time) {
            int r = t % 60;
            int target = (60 - r) % 60;
            count += rem[target];
            rem[r]++;
        }

        return count;
    }
}