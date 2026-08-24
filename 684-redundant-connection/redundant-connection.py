class Solution:

  def findRedundantConnection(self, edges: list[list[int]]) -> list[int]:
    n = len(edges)
    parent = list(range(n + 1))

    def find(node: int) -> int:
      if parent[node] != node:
        parent[node] = find(parent[node])  # Path compression
      return parent[node]

    def union(u: int, v: int) -> bool:
      root_u = find(u)
      root_v = find(v)
      if root_u == root_v:
        return False
      parent[root_u] = root_v
      return True

    for u, v in edges:
      if not union(u, v):
        return [u, v]

    return []