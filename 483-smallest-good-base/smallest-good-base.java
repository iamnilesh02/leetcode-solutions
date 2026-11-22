class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        int maxM = (int) (Math.log(num) / Math.log(2));

        for (int m = maxM; m >= 2; m--) {
            long left = 2;
            long right = (long) Math.pow(num, 1.0 / m) + 1;

            while (left <= right) {
                long k = left + (right - left) / 2;
                long sum = 1;
                long cur = 1;
                boolean overflow = false;

                for (int i = 1; i <= m; i++) {
                    if (cur > (num - sum) / k) {
                        overflow = true;
                        break;
                    }
                    cur *= k;
                    sum += cur;
                }

                if (overflow || sum > num) {
                    right = k - 1;
                } else if (sum < num) {
                    left = k + 1;
                } else {
                    return String.valueOf(k);
                }
            }
        }

        return String.valueOf(num - 1);
    }
}