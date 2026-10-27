#include <vector>
#include <algorithm>
#include <unordered_map>

using namespace std;

class Solution {
public:
    vector<int> arrayRankTransform(vector<int>& arr) {
        vector<int> sorted_arr = arr;
        sort(sorted_arr.begin(), sorted_arr.end());

        unordered_map<int, int> rank;
        for (int x : sorted_arr) {
            if (rank.find(x) == rank.end()) {
                rank[x] = rank.size() + 1;
            }
        }

        vector<int> res(arr.size());
        for (size_t i = 0; i < arr.size(); ++i) {
            res[i] = rank[arr[i]];
        }

        return res;
    }
};