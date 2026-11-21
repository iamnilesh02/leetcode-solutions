import java.util.*;

class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;
        backtrack(res, new StringBuilder(), num, target, 0, 0, 0);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder sb, String num, int target, int pos, long eval, long multed) {
        if (pos == num.length()) {
            if (eval == target) {
                res.add(sb.toString());
            }
            return;
        }

        int len = sb.length();
        long cur = 0;

        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') break;
            cur = cur * 10 + (num.charAt(i) - '0');

            if (pos == 0) {
                sb.append(cur);
                backtrack(res, sb, num, target, i + 1, cur, cur);
                sb.setLength(len);
            } else {
                sb.append('+').append(cur);
                backtrack(res, sb, num, target, i + 1, eval + cur, cur);
                sb.setLength(len);

                sb.append('-').append(cur);
                backtrack(res, sb, num, target, i + 1, eval - cur, -cur);
                sb.setLength(len);

                sb.append('*').append(cur);
                backtrack(res, sb, num, target, i + 1, eval - multed + multed * cur, multed * cur);
                sb.setLength(len);
            }
        }
    }
}