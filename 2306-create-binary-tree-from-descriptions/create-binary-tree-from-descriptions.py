class Solution:
    def createBinaryTree(self, descriptions: list[list[int]]) -> TreeNode | None:
        nodes = {}
        children = set()

        for parent, child, is_left in descriptions:
            if parent not in nodes:
                nodes[parent] = TreeNode(parent)
            if child not in nodes:
                nodes[child] = TreeNode(child)

            if is_left:
                nodes[parent].left = nodes[child]
            else:
                nodes[parent].right = nodes[child]

            children.add(child)

        for parent, _, _ in descriptions:
            if parent not in children:
                return nodes[parent]

        return None