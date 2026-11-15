class Solution:
    def braceExpansionII(self, expression: str) -> list[str]:
        def parse(i: int) -> tuple[set[str], int]:
            union_set = set()
            curr_list = [""]

            while i < len(expression) and expression[i] != '}':
                if expression[i] == ',':
                    union_set.update(curr_list)
                    curr_list = [""]
                    i += 1
                elif expression[i] == '{':
                    sub_set, i = parse(i + 1)
                    curr_list = [a + b for a in curr_list for b in sub_set]
                else:
                    ch = expression[i]
                    curr_list = [a + ch for a in curr_list]
                    i += 1

            union_set.update(curr_list)
            return union_set, i + 1

        res, _ = parse(0)
        return sorted(res)