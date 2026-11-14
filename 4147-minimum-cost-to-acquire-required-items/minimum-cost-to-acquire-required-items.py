class Solution:
    def minimumCost(self, cost1: int, cost2: int, costBoth: int, need1: int, need2: int) -> int:
        c1 = min(cost1, costBoth)
        c2 = min(cost2, costBoth)
        both = min(costBoth, c1 + c2)
        
        common = min(need1, need2)
        return common * both + (need1 - common) * c1 + (need2 - common) * c2