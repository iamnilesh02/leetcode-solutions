#include <vector>
#include <string>
#include <unordered_set>

using namespace std;

class Solution {
public:
    vector<string> removeInvalidParentheses(string s) {
        int rem_l = 0, rem_r = 0;
        for (char c : s) {
            if (c == '(') {
                rem_l++;
            } else if (c == ')') {
                if (rem_l > 0) {
                    rem_l--;
                } else {
                    rem_r++;
                }
            }
        }

        unordered_set<string> valid_set;
        string current = "";
        dfs(0, 0, rem_l, rem_r, s, current, valid_set);
        return vector<string>(valid_set.begin(), valid_set.end());
    }

private:
    void dfs(int idx, int open_count, int rem_l, int rem_r, const string& s, string& current, unordered_set<string>& valid_set) {
        if (idx == s.size()) {
            if (open_count == 0 && rem_l == 0 && rem_r == 0) {
                valid_set.insert(current);
            }
            return;
        }

        char c = s[idx];

        if (c == '(' && rem_l > 0) {
            dfs(idx + 1, open_count, rem_l - 1, rem_r, s, current, valid_set);
        }
        if (c == ')' && rem_r > 0) {
            dfs(idx + 1, open_count, rem_l, rem_r - 1, s, current, valid_set);
        }

        current.push_back(c);
        if (c != '(' && c != ')') {
            dfs(idx + 1, open_count, rem_l, rem_r, s, current, valid_set);
        } else if (c == '(') {
            dfs(idx + 1, open_count + 1, rem_l, rem_r, s, current, valid_set);
        } else if (open_count > 0) {
            dfs(idx + 1, open_count - 1, rem_l, rem_r, s, current, valid_set);
        }
        current.pop_back();
    }
};