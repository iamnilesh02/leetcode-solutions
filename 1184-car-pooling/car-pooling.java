class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] delta = new int[1001];

        for (int[] trip : trips) {
            int numPassengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            delta[from] += numPassengers;
            delta[to] -= numPassengers;
        }

        int currentPassengers = 0;
        for (int change : delta) {
            currentPassengers += change;
            if (currentPassengers > capacity) {
                return false;
            }
        }

        return true;
    }
}