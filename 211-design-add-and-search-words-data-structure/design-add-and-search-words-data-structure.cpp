class TrieNode {
public:
    TrieNode* children[26];
    bool isEnd;

    TrieNode() {
        isEnd = false;
        for (int i = 0; i < 26; i++) {
            children[i] = nullptr;
        }
    }
};

class WordDictionary {
private:
    TrieNode* root;

    bool searchInNode(const string& word, int index, TrieNode* node) {
        if (!node) return false;
        if (index == word.length()) return node->isEnd;

        char c = word[index];
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (node->children[i] && searchInNode(word, index + 1, node->children[i])) {
                    return true;
                }
            }
            return false;
        } else {
            int idx = c - 'a';
            return searchInNode(word, index + 1, node->children[idx]);
        }
    }

public:
    WordDictionary() {
        root = new TrieNode();
    }
    
    void addWord(string word) {
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
    
    bool search(string word) {
        return searchInNode(word, 0, root);
    }
};