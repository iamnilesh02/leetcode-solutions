import java.util.*;

class Solution {
    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        
        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                set.remove(word.substring(i));
            }
        }
        
        int len = 0;
        for (String word : set) {
            len += word.length() + 1;
        }
        
        return len;
    }
}