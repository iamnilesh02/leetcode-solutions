class Solution:
    def validSequence(self, word1: str, word2: str) -> List[int]:
        n, m = len(word1), len(word2)
        
        last = [-1] * m
        i, j = n - 1, m - 1
        while i >= 0 and j >= 0:
            if word1[i] == word2[j]:
                last[j] = i
                j -= 1
            i -= 1
            
        ans = []
        i, j = 0, 0
        changed = False
        
        while i < n and j < m:
            if word1[i] == word2[j]:
                ans.append(i)
                i += 1
                j += 1
            else:
                if not changed:
                    if j + 1 == m or last[j + 1] > i:
                        ans.append(i)
                        changed = True
                        i += 1
                        j += 1
                    else:
                        i += 1
                else:
                    i += 1
                    
        return ans if len(ans) == m else []