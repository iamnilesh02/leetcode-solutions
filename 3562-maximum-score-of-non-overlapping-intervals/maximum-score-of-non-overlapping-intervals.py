import bisect

class Solution:
    def maximumWeight(self, intervals: List[List[int]]) -> List[int]:
        n = len(intervals)
        events = sorted([(r, l, w, i) for i, (l, r, w) in enumerate(intervals)])
        r_list = [e[0] for e in events]

        dp = [[(0, []) for _ in range(5)] for _ in range(n + 1)]

        for i in range(1, n + 1):
            r, l, w, orig_idx = events[i - 1]
            p = bisect.bisect_left(r_list, l)

            for k in range(5):
                dp[i][k] = dp[i - 1][k]

            for k in range(1, 5):
                prev_score, prev_indices = dp[p][k - 1]
                cand_score = prev_score + w
                cand_indices = sorted(prev_indices + [orig_idx])

                curr_score, curr_indices = dp[i][k]
                if cand_score > curr_score:
                    dp[i][k] = (cand_score, cand_indices)
                elif cand_score == curr_score:
                    if not curr_indices or cand_indices < curr_indices:
                        dp[i][k] = (cand_score, cand_indices)

        best_score = -1
        best_indices = []
        for k in range(1, 5):
            score, indices = dp[n][k]
            if score > best_score:
                best_score = score
                best_indices = indices
            elif score == best_score:
                if indices < best_indices:
                    best_indices = indices

        return best_indices