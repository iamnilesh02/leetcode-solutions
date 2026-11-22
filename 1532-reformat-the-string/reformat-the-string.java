import java.util.ArrayList;
import java.util.List;

class Solution {
    public String reformat(String s) {
        List<Character> letters = new ArrayList<>();
        List<Character> digits = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                digits.add(c);
            } else {
                letters.add(c);
            }
        }

        if (Math.abs(letters.size() - digits.size()) > 1) {
            return "";
        }

        List<Character> first = letters.size() >= digits.size() ? letters : digits;
        List<Character> second = letters.size() >= digits.size() ? digits : letters;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < first.size(); i++) {
            sb.append(first.get(i));
            if (i < second.size()) {
                sb.append(second.get(i));
            }
        }

        return sb.toString();
    }
}