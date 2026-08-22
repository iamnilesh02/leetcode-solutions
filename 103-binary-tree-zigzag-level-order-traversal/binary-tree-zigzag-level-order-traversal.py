from collections import deque

class Solution:
    def zigzagLevelOrder(self, root):
        result = []
        if not root:
            return result

        queue = deque([root])
        left_to_right = True

        while queue:
            level_size = len(queue)
            current_level = [0] * level_size

            for i in range(level_size):
                node = queue.popleft()

                index = i if left_to_right else (level_size - 1 - i)
                current_level[index] = node.val

                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)

            left_to_right = not left_to_right
            result.append(current_level)

        return result