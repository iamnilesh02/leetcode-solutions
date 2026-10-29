class Solution:
    def evaluate(self, s: str, knowledge: list[list[str]]) -> str:
        d = {k: v for k, v in knowledge}
        res = []
        in_bracket = False
        key = []

        for ch in s:
            if ch == '(':
                in_bracket = True
                key = []
            elif ch == ')':
                in_bracket = False
                res.append(d.get("".join(key), "?"))
            elif in_bracket:
                key.append(ch)
            else:
                res.append(ch)

        return "".join(res)