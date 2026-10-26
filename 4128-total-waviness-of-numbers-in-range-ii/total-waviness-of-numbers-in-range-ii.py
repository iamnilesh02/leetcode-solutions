from functools import lru_cache

class Solution:
    def totalWaviness(self, num1: int, num2: int) -> int:
        def count_up_to(n: int) -> int:
            if n < 100:
                return 0
            
            s = str(n)
            L = len(s)
            
            @lru_cache(maxsize=None)
            def dp(idx: int, is_less: bool, is_started: bool, prev2: int, prev1: int):
                if idx == L:
                    return 0, 1
                
                limit = 9 if is_less else int(s[idx])
                total_wav = 0
                total_cnt = 0
                
                for d in range(limit + 1):
                    nxt_less = is_less or (d < limit)
                    
                    if not is_started:
                        if d == 0:
                            sub_wav, sub_cnt = dp(idx + 1, nxt_less, False, -1, -1)
                        else:
                            sub_wav, sub_cnt = dp(idx + 1, nxt_less, True, -1, d)
                        total_wav += sub_wav
                        total_cnt += sub_cnt
                    else:
                        is_wave = 0
                        if prev2 != -1:
                            if prev2 < prev1 > d or prev2 > prev1 < d:
                                is_wave = 1
                                
                        sub_wav, sub_cnt = dp(idx + 1, nxt_less, True, prev1, d)
                        total_wav += sub_wav + is_wave * sub_cnt
                        total_cnt += sub_cnt
                        
                return total_wav, total_cnt

            wav, _ = dp(0, False, False, -1, -1)
            return wav

        return count_up_to(num2) - count_up_to(num1 - 1)