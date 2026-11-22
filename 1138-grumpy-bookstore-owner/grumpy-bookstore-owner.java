class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int baseSatisfied = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                baseSatisfied += customers[i];
            }
        }

        int currentWindowGain = 0;
        for (int i = 0; i < minutes; i++) {
            if (grumpy[i] == 1) {
                currentWindowGain += customers[i];
            }
        }

        int maxGain = currentWindowGain;
        for (int i = minutes; i < n; i++) {
            if (grumpy[i] == 1) {
                currentWindowGain += customers[i];
            }
            if (grumpy[i - minutes] == 1) {
                currentWindowGain -= customers[i - minutes];
            }
            maxGain = Math.max(maxGain, currentWindowGain);
        }

        return baseSatisfied + maxGain;
    }
}