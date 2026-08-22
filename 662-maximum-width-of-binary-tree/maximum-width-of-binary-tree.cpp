class Solution {
public:
    int widthOfBinaryTree(TreeNode* root) {
        if (!root) return 0;

        unsigned long long maxWidth = 0;
        queue<pair<TreeNode*, unsigned long long>> q;
        q.push({root, 0});

        while (!q.empty()) {
            int levelSize = q.size();
            unsigned long long minIndex = q.front().second;
            unsigned long long first = 0, last = 0;

            for (int i = 0; i < levelSize; i++) {
                unsigned long long currIndex = q.front().second - minIndex;
                TreeNode* node = q.front().first;
                q.pop();

                if (i == 0) first = currIndex;
                if (i == levelSize - 1) last = currIndex;

                if (node->left) q.push({node->left, 2 * currIndex + 1});
                if (node->right) q.push({node->right, 2 * currIndex + 2});
            }

            maxWidth = max(maxWidth, last - first + 1);
        }

        return maxWidth;
    }
};