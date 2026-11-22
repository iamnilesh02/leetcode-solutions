class Solution {
    public int nthUglyNumber(int n, int a, int b, int c) {
        long ab = lcm(a, b);
        long bc = lcm(b, c);
        long ac = lcm(a, c);
        long abc = lcm(ab, c);

        long left = 1;
        long right = 2_000_000_000L;

        while (left < right) {
            long mid = left + (right - left) / 2;
            long count = (mid / a) + (mid / b) + (mid / c)
                       - (mid / ab) - (mid / bc) - (mid / ac)
                       + (mid / abc);

            if (count >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return (int) left;
    }

    private long gcd(long x, long y) {
        while (y != 0) {
            long temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }

    private long lcm(long x, long y) {
        return (x / gcd(x, y)) * y;
    }
}