class Solution {
    public String smallestSubsequence(String s, int k, char letter, int repetition) {
        int n = s.length();
        int remainingLetter = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == letter) {
                remainingLetter++;
            }
        }

        char[] stack = new char[k];
        int top = 0;
        int inStackLetter = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            while (top > 0 && stack[top - 1] > c && (top - 1 + n - i >= k)) {
                if (stack[top - 1] == letter) {
                    if (inStackLetter - 1 + remainingLetter < repetition) {
                        break;
                    }
                    inStackLetter--;
                }
                top--;
            }

            if (top < k) {
                if (c == letter) {
                    stack[top++] = c;
                    inStackLetter++;
                } else if (k - top > repetition - inStackLetter) {
                    stack[top++] = c;
                }
            }

            if (c == letter) {
                remainingLetter--;
            }
        }

        return new String(stack);
    }
}