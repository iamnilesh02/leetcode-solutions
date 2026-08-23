class Solution:

  def findMaximumXOR(self, nums: list[int]) -> int:
    root = {}

    # Build the Bit Trie
    for num in nums:
      node = root
      for i in range(30, -1, -1):
        bit = (num >> i) & 1
        if bit not in node:
          node[bit] = {}
        node = node[bit]

    max_xor = 0

    # Query the Bit Trie for maximum XOR
    for num in nums:
      node = root
      curr_xor = 0
      for i in range(30, -1, -1):
        bit = (num >> i) & 1
        opposite_bit = 1 - bit
        if opposite_bit in node:
          curr_xor |= 1 << i
          node = node[opposite_bit]
        else:
          node = node[bit]
      max_xor = max(max_xor, curr_xor)

    return max_xor