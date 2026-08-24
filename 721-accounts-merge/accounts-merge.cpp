class DSU {
public:
    vector<int> parent;
    DSU(int n) {
        parent.resize(n);
        iota(parent.begin(), parent.end(), 0);
    }

    int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    void unite(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }
};

class Solution {
public:
    vector<vector<string>> accountsMerge(vector<vector<string>>& accounts) {
        int n = accounts.size();
        DSU dsu(n);
        unordered_map<string, int> emailToId;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts[i].size(); j++) {
                string email = accounts[i][j];
                if (emailToId.find(email) == emailToId.end()) {
                    emailToId[email] = i;
                } else {
                    dsu.unite(i, emailToId[email]);
                }
            }
        }

        unordered_map<int, vector<string>> rootToEmails;
        for (auto& [email, id] : emailToId) {
            int root = dsu.find(id);
            rootToEmails[root].push_back(email);
        }

        vector<vector<string>> result;
        for (auto& [id, emails] : rootToEmails) {
            sort(emails.begin(), emails.end());
            vector<string> account = {accounts[id][0]};
            account.insert(account.end(), emails.begin(), emails.end());
            result.push_back(account);
        }

        return result;
    }
};