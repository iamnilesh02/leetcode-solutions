class Solution {
private:
    void dfs(int city, const vector<vector<int>>& isConnected, vector<bool>& visited) {
        visited[city] = true;
        for (int neighbor = 0; neighbor < isConnected.size(); neighbor++) {
            if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
                dfs(neighbor, isConnected, visited);
            }
        }
    }

public:
    int findCircleNum(vector<vector<int>>& isConnected) {
        int n = isConnected.size();
        vector<bool> visited(n, false);
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                provinces++;
                dfs(i, isConnected, visited);
            }
        }

        return provinces;
    }
};