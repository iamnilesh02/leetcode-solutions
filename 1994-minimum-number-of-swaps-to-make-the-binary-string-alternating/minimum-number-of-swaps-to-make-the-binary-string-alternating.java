class Solution {
    public int minSwaps(String s) {
        int zeros = 0;
        int ones = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                zeros++;
            } else {
                ones++;
            }
        }

        if (Math.abs(zeros - ones) > 1) {
            return -1;
        }

        if (zeros > ones) {
            return countMismatches(s, '0');
        } else if (ones > zeros) {
            return countMismatches(s, '1');
        } else {
            return Math.min(countMismatches(s, '0'), countMismatches(s, '1'));
        }
    }

    private int countMismatches(String s, char startChar) {
        int mismatches = 0;
        char expected = startChar;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != expected) {
                mismatches++;
            }
            expected = (expected == '0') ? '1' : '0';
        }

        return mismatches / 2;
    }
}