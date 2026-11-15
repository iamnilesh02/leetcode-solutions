import java.util.*;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }

        List<int[]> runs = new ArrayList<>();
        int[] idxToRun = new int[n];
        for (int i = 0; i < n; ) {
            int j = i;
            char ch = s.charAt(i);
            while (j < n && s.charAt(j) == ch) {
                idxToRun[j] = runs.size();
                j++;
            }
            runs.add(new int[]{ch - '0', i, j - 1});
            i = j;
        }

        int m = runs.size();
        int[] pairSums = new int[m];
        for (int k = 0; k + 2 < m; k++) {
            if (runs.get(k)[0] == 0) {
                int len1 = runs.get(k)[2] - runs.get(k)[1] + 1;
                int len2 = runs.get(k + 2)[2] - runs.get(k + 2)[1] + 1;
                pairSums[k] = len1 + len2;
            }
        }

        int LOG = 18;
        int[][] st = new int[LOG][m];
        for (int i = 0; i < m; i++) {
            st[0][i] = pairSums[i];
        }
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i + (1 << j) <= m; i++) {
                st[j][i] = Math.max(st[j - 1][i], st[j - 1][i + (1 << (j - 1))]);
            }
        }

        int[] lg = new int[m + 1];
        for (int i = 2; i <= m; i++) {
            lg[i] = lg[i >> 1] + 1;
        }

        List<Integer> ans = new ArrayList<>(queries.length);
        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int firstRun = idxToRun[l];
            int lastRun = idxToRun[r];

            int firstZero = (runs.get(firstRun)[0] == 0) ? firstRun : firstRun + 1;
            int lastZero = (runs.get(lastRun)[0] == 0) ? lastRun : lastRun - 1;

            if (firstZero >= lastZero) {
                ans.add(totalOnes);
                continue;
            }

            int bestGain = 0;
            int firstZeroLen = runs.get(firstZero)[2] - Math.max(l, runs.get(firstZero)[1]) + 1;
            int lastZeroLen = Math.min(r, runs.get(lastZero)[2]) - runs.get(lastZero)[1] + 1;

            if (firstZero + 2 == lastZero) {
                bestGain = firstZeroLen + lastZeroLen;
            } else {
                int nextZeroLen = runs.get(firstZero + 2)[2] - runs.get(firstZero + 2)[1] + 1;
                bestGain = Math.max(bestGain, firstZeroLen + nextZeroLen);

                int prevZeroLen = runs.get(lastZero - 2)[2] - runs.get(lastZero - 2)[1] + 1;
                bestGain = Math.max(bestGain, prevZeroLen + lastZeroLen);

                int midL = firstZero + 2;
                int midR = lastZero - 4;
                if (midL <= midR) {
                    int k = lg[midR - midL + 1];
                    int midMax = Math.max(st[k][midL], st[k][midR - (1 << k) + 1]);
                    bestGain = Math.max(bestGain, midMax);
                }
            }

            ans.add(totalOnes + bestGain);
        }

        return ans;
    }
}