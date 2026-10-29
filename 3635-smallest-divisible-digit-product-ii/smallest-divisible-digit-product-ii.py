class Solution:
    def smallestNumber(self, num: str, t: int) -> str:
        c2, c3, c5, c7 = 0, 0, 0, 0
        temp = t
        for p, count in [(2, 0), (3, 0), (5, 0), (7, 0)]:
            while temp % p == 0:
                temp //= p
                if p == 2: c2 += 1
                elif p == 3: c3 += 1
                elif p == 5: c5 += 1
                elif p == 7: c7 += 1
        if temp > 1:
            return "-1"

        DIGIT_FACTORS = {
            1: (0, 0, 0, 0),
            2: (1, 0, 0, 0),
            3: (0, 1, 0, 0),
            4: (2, 0, 0, 0),
            5: (0, 0, 1, 0),
            6: (1, 1, 0, 0),
            7: (0, 0, 0, 1),
            8: (3, 0, 0, 0),
            9: (0, 2, 0, 0),
        }

        def min_digits(r2, r3, r5, r7):
            cnt = 0
            cnt += r7
            cnt += r5

            n9 = r3 // 2
            rem3 = r3 % 2

            n8 = r2 // 3
            rem2 = r2 % 3

            if rem3 == 1 and rem2 == 1:
                cnt += n9 + n8 + 1
            elif rem3 == 1 and rem2 == 2:
                cnt += n9 + n8 + 2
            elif rem3 == 1 and rem2 == 0:
                cnt += n9 + n8 + 1
            elif rem3 == 0 and rem2 > 0:
                cnt += n9 + n8 + 1
            else:
                cnt += n9 + n8

            return cnt

        def construct_suffix(length, r2, r3, r5, r7):
            d9 = r3 // 2
            rem3 = r3 % 2

            d8 = r2 // 3
            rem2 = r2 % 3

            d7 = r7
            d5 = r5
            d6 = 0
            d4 = 0
            d3 = 0
            d2 = 0

            if rem3 == 1 and rem2 == 1:
                d6 = 1
            elif rem3 == 1 and rem2 == 2:
                d6 = 1
                d2 = 1
            elif rem3 == 1 and rem2 == 0:
                d3 = 1
            elif rem3 == 0 and rem2 == 1:
                d2 = 1
            elif rem3 == 0 and rem2 == 2:
                d4 = 1

            digits = [7] * d7 + [5] * d5 + [9] * d9 + [8] * d8 + [6] * d6 + [4] * d4 + [3] * d3 + [2] * d2
            ones = length - len(digits)
            digits.extend([1] * ones)
            digits.sort()
            return "".join(map(str, digits))

        n = len(num)
        
        pref2 = [0] * (n + 1)
        pref3 = [0] * (n + 1)
        pref5 = [0] * (n + 1)
        pref7 = [0] * (n + 1)

        first_zero = -1
        for i, ch in enumerate(num):
            d = int(ch)
            if d == 0 and first_zero == -1:
                first_zero = i
            if first_zero == -1:
                f = DIGIT_FACTORS[d]
                pref2[i + 1] = pref2[i] + f[0]
                pref3[i + 1] = pref3[i] + f[1]
                pref5[i + 1] = pref5[i] + f[2]
                pref7[i + 1] = pref7[i] + f[3]

        if first_zero == -1:
            if pref2[n] >= c2 and pref3[n] >= c3 and pref5[n] >= c5 and pref7[n] >= c7:
                return num

        limit = n - 1 if first_zero == -1 else first_zero

        for i in range(limit, -1, -1):
            cur_d = int(num[i])
            for d in range(cur_d + 1, 10):
                f = DIGIT_FACTORS[d]
                r2 = max(0, c2 - pref2[i] - f[0])
                r3 = max(0, c3 - pref3[i] - f[1])
                r5 = max(0, c5 - pref5[i] - f[2])
                r7 = max(0, c7 - pref7[i] - f[3])

                rem_len = n - 1 - i
                if min_digits(r2, r3, r5, r7) <= rem_len:
                    return num[:i] + str(d) + construct_suffix(rem_len, r2, r3, r5, r7)

        req_len = max(n + 1, min_digits(c2, c3, c5, c7))
        return construct_suffix(req_len, c2, c3, c5, c7)