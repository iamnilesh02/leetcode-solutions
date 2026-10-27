#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int maxActiveSectionsAfterTrade(string s) {
        int original_ones = 0;
        for (char c : s) {
            if (c == '1') {
                original_ones++;
            }
        }

        string t = "1" + s + "1";
        vector<int> zero_blocks;
        int n = t.size();
        int i = 0;

        while (i < n) {
            if (t[i] == '1') {
                while (i < n && t[i] == '1') {
                    i++;
                }
            } else {
                int start = i;
                while (i < n && t[i] == '0') {
                    i++;
                }
                zero_blocks.push_back(i - start);
            }
        }

        if (zero_blocks.size() < 2) {
            return original_ones;
        }

        int max_gain = 0;
        for (size_t j = 0; j + 1 < zero_blocks.size(); j++) {
            max_gain = max(max_gain, zero_blocks[j] + zero_blocks[j + 1]);
        }

        return original_ones + max_gain;
    }
};