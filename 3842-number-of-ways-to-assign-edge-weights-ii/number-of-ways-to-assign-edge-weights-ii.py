class Solution:
    def assignEdgeWeights(self, edges: List[List[int]], queries: List[List[int]]) -> List[int]:
        MOD = 10**9 + 7
        n = len(edges) + 1
        adj = [[] for _ in range(n + 1)]
        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)

        LOG = n.bit_length()
        up = [[0] * LOG for _ in range(n + 1)]
        depth = [0] * (n + 1)

        stack = [(1, 0, 0)]
        order = []
        while stack:
            u, p, d = stack.pop()
            up[u][0] = p
            depth[u] = d
            order.append(u)
            for v in adj[u]:
                if v != p:
                    stack.append((v, u, d + 1))

        for j in range(1, LOG):
            for i in range(1, n + 1):
                up[i][j] = up[up[i][j - 1]][j - 1]

        def get_lca(u: int, v: int) -> int:
            if depth[u] < depth[v]:
                u, v = v, u
            diff = depth[u] - depth[v]
            for j in range(LOG):
                if (diff >> j) & 1:
                    u = up[u][j]
            if u == v:
                return u
            for j in range(LOG - 1, -1, -1):
                if up[u][j] != up[v][j]:
                    u = up[u][j]
                    v = up[v][j]
            return up[u][0]

        pow2 = [1] * (n + 1)
        for i in range(1, n + 1):
            pow2[i] = (pow2[i - 1] * 2) % MOD

        ans = []
        for u, v in queries:
            lca = get_lca(u, v)
            dist = depth[u] + depth[v] - 2 * depth[lca]
            if dist == 0:
                ans.append(0)
            else:
                ans.append(pow2[dist - 1])

        return ans