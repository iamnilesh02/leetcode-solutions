struct TrieNode {
    TrieNode* children[26] = {nullptr};
    bool isEnd = false;
};

class Solution {
private:
    TrieNode* root;

    void insert(const string& word) {
        TrieNode* node = root;
        for (char c : word) {
            int idx = c - 'a';
            if (!node->children[idx]) {
                node->children[idx] = new TrieNode();
            }
            node = node->children[idx];
        }
        node->isEnd = true;
    }

    string findShortestRoot(const string& word) {
        TrieNode* node = root;
        string prefix = "";
        for (char c : word) {
            int idx = c - 'a';
            if (!node->children[idx]) {
                break;
            }
            prefix += c;
            node = node->children[idx];
            if (node->isEnd) {
                return prefix;
            }
        }
        return word;
    }

public:
    string replaceWords(vector<string>& dictionary, string sentence) {
        root = new TrieNode();
        for (const string& rootWord : dictionary) {
            insert(rootWord);
        }

        stringstream ss(sentence);
        string word, result = "";

        while (ss >> word) {
            if (!result.empty()) result += " ";
            result += findShortestRoot(word);
        }

        return result;
    }
};