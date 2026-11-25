import java.util.Arrays;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 1;
        }

        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int[] arr = new int[n];
        int uniqueCount = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.binarySearch(sorted, nums[i]);
        }

        int[] sa = buildSuffixArray(arr, n);
        int[] lcp = buildLCP(arr, sa, n);

        int ans = n;
        for (int i = 0; i < n; i++) {
            int maxShared = 0;
            if (i > 0) {
                maxShared = Math.max(maxShared, lcp[i - 1]);
            }
            if (i < n - 1) {
                maxShared = Math.max(maxShared, lcp[i]);
            }
            int neededLen = maxShared + 1;
            if (sa[i] + neededLen <= n) {
                ans = Math.min(ans, neededLen);
            }
        }

        return ans;
    }

    private int[] buildSuffixArray(int[] s, int n) {
        int[] sa = new int[n];
        int[] rank = new int[n];
        int[] tmp = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = s[i];
        }

        for (int k = 1; k < n; k <<= 1) {
            final int step = k;
            final int[] r = rank;
            Integer[] order = new Integer[n];
            for (int i = 0; i < n; i++) {
                order[i] = sa[i];
            }

            Arrays.sort(order, (a, b) -> {
                if (r[a] != r[b]) {
                    return Integer.compare(r[a], r[b]);
                }
                int ra = (a + step < n) ? r[a + step] : -1;
                int rb = (b + step < n) ? r[b + step] : -1;
                return Integer.compare(ra, rb);
            });

            for (int i = 0; i < n; i++) {
                sa[i] = order[i];
            }

            tmp[sa[0]] = 0;
            for (int i = 1; i < n; i++) {
                int prev = sa[i - 1];
                int curr = sa[i];
                boolean diff = r[prev] != r[curr]
                        || ((prev + step < n) ? r[prev + step] : -1) != ((curr + step < n) ? r[curr + step] : -1);
                tmp[curr] = tmp[prev] + (diff ? 1 : 0);
            }
            System.arraycopy(tmp, 0, rank, 0, n);
            if (rank[sa[n - 1]] == n - 1) {
                break;
            }
        }
        return sa;
    }

    private int[] buildLCP(int[] s, int[] sa, int n) {
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }
        int[] lcp = new int[n - 1];
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] > 0) {
                int j = sa[rank[i] - 1];
                while (i + h < n && j + h < n && s[i + h] == s[j + h]) {
                    h++;
                }
                lcp[rank[i] - 1] = h;
                if (h > 0) {
                    h--;
                }
            }
        }
        return lcp;
    }
}