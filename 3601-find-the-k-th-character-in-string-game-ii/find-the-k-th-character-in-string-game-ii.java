class Solution {
    public char kthCharacter(long k, int[] operations) {
        int shifts = 0;
        int n = Math.min(operations.length, 62);

        for (int i = n - 1; i >= 0; i--) {
            long half = 1L << i;
            if (k > half) {
                k -= half;
                shifts += operations[i];
            }
        }

        return (char) ('a' + (shifts % 26));
    }
}