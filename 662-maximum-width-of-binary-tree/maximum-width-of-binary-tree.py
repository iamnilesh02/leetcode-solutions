from collections import deque


class Solution:

  def widthOfBinaryTree(self, root):
    if not root:
      return 0

    max_width = 0
    queue = deque([(root, 0)])

    while queue:
      level_size = len(queue)
      _, min_index = queue[0]
      first = last = 0

      for i in range(level_size):
        node, idx = queue.popleft()
        curr_index = idx - min_index

        if i == 0:
          first = curr_index
        if i == level_size - 1:
          last = curr_index

        if node.left:
          queue.append((node.left, 2 * curr_index + 1))
        if node.right:
          queue.append((node.right, 2 * curr_index + 2))

      max_width = max(max_width, last - first + 1)

    return max_width