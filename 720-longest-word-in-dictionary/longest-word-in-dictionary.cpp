class Solution {
public:
    string longestWord(vector<string>& words) {
        sort(words.begin(), words.end());
        unordered_set<string> builtWords;
        builtWords.insert("");
        string longest = "";

        for (const string& word : words) {
            string prefix = word.substr(0, word.length() - 1);
            if (builtWords.count(prefix)) {
                builtWords.insert(word);
                if (word.length() > longest.length()) {
                    longest = word;
                }
            }
        }

        return longest;
    }
};