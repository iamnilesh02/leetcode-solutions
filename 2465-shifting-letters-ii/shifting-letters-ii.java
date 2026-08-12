class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] diff = new int[n + 1];

        for (int[] shift : shifts) {
            int start = shift[0];
            int end = shift[1];
            int direction = shift[2];
            int val = (direction == 1) ? 1 : -1;

            diff[start] += val;
            diff[end + 1] -= val;
        }

        char[] chars = s.toCharArray();
        int cumulativeShift = 0;

        for (int i = 0; i < n; i++) {
            cumulativeShift += diff[i];

            int netShift = (cumulativeShift % 26 + 26) % 26;
            chars[i] = (char) ('a' + (chars[i] - 'a' + netShift) % 26);
        }

        return new String(chars);
    }
}