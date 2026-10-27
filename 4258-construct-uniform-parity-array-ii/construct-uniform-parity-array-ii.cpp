#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    bool uniformArray(vector<int>& nums1) {
        int min_val = nums1[0];
        bool has_odd = false;

        for (int x : nums1) {
            min_val = min(min_val, x);
            if (x % 2 != 0) {
                has_odd = true;
            }
        }

        if (min_val % 2 != 0) {
            return true;
        }

        return !has_odd;
    }
};