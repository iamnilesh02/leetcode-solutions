class Solution:

  def checkDivisibility(self, n: int) -> bool:
    temp = n
    digit_sum = 0
    digit_prod = 1

    while temp > 0:
      digit = temp % 10
      digit_sum += digit
      digit_prod *= digit
      temp //= 10

    total = digit_sum + digit_prod
    return n % total == 0