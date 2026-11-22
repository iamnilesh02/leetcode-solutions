import java.util.*;

class Solution {
    public int largestInteger(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();

        for (char c : digits) {
            int d = c - '0';
            if (d % 2 == 0) {
                evens.add(d);
            } else {
                odds.add(d);
            }
        }

        evens.sort(Collections.reverseOrder());
        odds.sort(Collections.reverseOrder());

        int evenIdx = 0;
        int oddIdx = 0;
        int result = 0;

        for (char c : digits) {
            int d = c - '0';
            result *= 10;
            if (d % 2 == 0) {
                result += evens.get(evenIdx++);
            } else {
                result += odds.get(oddIdx++);
            }
        }

        return result;
    }
}