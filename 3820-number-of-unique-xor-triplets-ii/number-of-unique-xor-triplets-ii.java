class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] present = new boolean[2048];
        int maxVal = 0;
        for (int num : nums) {
            present[num] = true;
            if (num > maxVal) {
                maxVal = num;
            }
        }

        int limit = 1;
        while (limit <= maxVal) {
            limit <<= 1;
        }

        int[] uniqueNums = new int[limit];
        int count = 0;
        for (int i = 0; i < limit; i++) {
            if (present[i]) {
                uniqueNums[count++] = i;
            }
        }

        boolean[] pairXor = new boolean[limit];
        for (int i = 0; i < count; i++) {
            for (int j = i; j < count; j++) {
                pairXor[uniqueNums[i] ^ uniqueNums[j]] = true;
            }
        }

        boolean[] tripletXor = new boolean[limit];
        int ans = 0;
        for (int p = 0; p < limit; p++) {
            if (pairXor[p]) {
                for (int i = 0; i < count; i++) {
                    int val = p ^ uniqueNums[i];
                    if (!tripletXor[val]) {
                        tripletXor[val] = true;
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}