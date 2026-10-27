#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

class Solution {
public:
    long long minSumSquareDiff(vector<int>& nums1, vector<int>& nums2, int k1, int k2) {
        int n = nums1.size();
        long long k = (long long)k1 + k2;
        int max_d = 0;

        vector<int> diff(n);
        for (int i = 0; i < n; ++i) {
            diff[i] = abs(nums1[i] - nums2[i]);
            max_d = max(max_d, diff[i]);
        }

        vector<int> count(max_d + 1, 0);
        for (int d : diff) {
            count[d]++;
        }

        for (int v = max_d; v > 0 && k > 0; --v) {
            if (count[v] == 0) {
                continue;
            }

            if (k >= count[v]) {
                k -= count[v];
                count[v - 1] += count[v];
                count[v] = 0;
            } else {
                count[v - 1] += k;
                count[v] -= k;
                k = 0;
            }
        }

        long long ans = 0;
        for (long long v = 1; v <= max_d; ++v) {
            if (count[v] > 0) {
                ans += v * v * count[v];
            }
        }

        return ans;
    }
};