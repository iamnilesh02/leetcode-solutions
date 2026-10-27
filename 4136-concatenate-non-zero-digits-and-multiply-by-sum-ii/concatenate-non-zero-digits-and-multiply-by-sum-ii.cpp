#include <vector>
#include <string>

using namespace std;

class Solution {
public:
    vector<int> sumAndMultiply(string s, vector<vector<int>>& queries) {
        int m = s.size();
        const int MOD = 1e9 + 7;

        vector<long long> pow10(m + 1, 1);
        for (int i = 1; i <= m; ++i) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        vector<int> pref_count(m + 1, 0);
        vector<int> pref_sum(m + 1, 0);
        vector<long long> pref_val(m + 1, 0);

        for (int i = 0; i < m; ++i) {
            int d = s[i] - '0';
            pref_count[i + 1] = pref_count[i] + (d > 0 ? 1 : 0);
            pref_sum[i + 1] = pref_sum[i] + d;
            if (d > 0) {
                pref_val[i + 1] = (pref_val[i] * 10 + d) % MOD;
            } else {
                pref_val[i + 1] = pref_val[i];
            }
        }

        vector<int> ans;
        ans.reserve(queries.size());

        for (const auto& q : queries) {
            int l = q[0];
            int r = q[1];

            int count = pref_count[r + 1] - pref_count[l];
            if (count == 0) {
                ans.push_back(0);
                continue;
            }

            long long sum = pref_sum[r + 1] - pref_sum[l];
            long long x = (pref_val[r + 1] - pref_val[l] * pow10[count]) % MOD;
            if (x < 0) {
                x += MOD;
            }

            long long res = (x * (sum % MOD)) % MOD;
            ans.push_back(res);
        }

        return ans;
    }
};