import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> result = new ArrayList<>();
        
        for (int num : nums) {
            long current = num;
            
            while (!result.isEmpty()) {
                long top = result.get(result.size() - 1);
                long g = gcd(top, current);
                
                if (g == 1) {
                    break;
                }
                
                current = (top * current) / g;
                result.remove(result.size() - 1);
            }
            
            result.add((int) current);
        }
        
        return result;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}