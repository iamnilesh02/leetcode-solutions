class Solution {
    public int bestClosingTime(String customers) {
        int currentPenalty = 0;
        int minPenalty = 0;
        int earliestHour = 0;

        for (int i = 0; i < customers.length(); i++) {
            if (customers.charAt(i) == 'Y') {
                currentPenalty--;
            } else {
                currentPenalty++;
            }

            if (currentPenalty < minPenalty) {
                minPenalty = currentPenalty;
                earliestHour = i + 1;
            }
        }

        return earliestHour;
    }
}