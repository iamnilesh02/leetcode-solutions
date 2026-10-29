import sys

sys.setrecursionlimit(200000)

class Solution:
    def palindromePath(self, n: int, edges: list[list[int]], s: str, queries: list[str]) -> list[bool]:
        adj = [[] for _ in range(n)]
        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)

        parent = [0] * n
        depth = [0] * n
        heavy = [-1] * n
        size = [0] * n

        def dfs(u, p, d):
            parent[u] = p
            depth[u] = d
            size[u] = 1
            max_c_size = 0
            for v in adj[u]:
                if v != p:
                    dfs(v, u, d + 1)
                    size[u] += size[v]
                    if size[v] > max_c_size:
                        max_c_size = size[v]
                        heavy[u] = v

        dfs(0, 0, 0)

        head = [0] * n
        pos = [0] * n
        cur_pos = 0

        def decompose(u, h):
            nonlocal cur_pos
            head[u] = h
            pos[u] = cur_pos
            cur_pos += 1
            if heavy[u] != -1:
                decompose(heavy[u], h)
            for v in adj[u]:
                if v != parent[u] and v != heavy[u]:
                    decompose(v, v)

        decompose(0, 0)

        bit = [0] * (n + 1)

        def bit_update(idx, val):
            idx += 1
            while idx <= n:
                bit[idx] ^= val
                idx += idx & (-idx)

        def bit_query(idx):
            idx += 1
            res = 0
            while idx > 0:
                res ^= bit[idx]
                idx -= idx & (-idx)
            return res

        def query_range(l, r):
            return bit_query(r) ^ bit_query(l - 1)

        current_mask = [1 << (ord(c) - ord('a')) for c in s]
        for i in range(n):
            bit_update(pos[i], current_mask[i])

        def query_path(u, v):
            xor_sum = 0
            while head[u] != head[v]:
                if depth[head[u]] > depth[head[v]]:
                    xor_sum ^= query_range(pos[head[u]], pos[u])
                    u = parent[head[u]]
                else:
                    xor_sum ^= query_range(pos[head[v]], pos[v])
                    v = parent[head[v]]

            if depth[u] > depth[v]:
                u, v = v, u
            xor_sum ^= query_range(pos[u], pos[v])
            return xor_sum

        ans = []
        for q in queries:
            parts = q.split()
            if parts[0] == "update":
                u = int(parts[1])
                new_c = parts[2]
                new_mask = 1 << (ord(new_c) - ord('a'))
                diff = current_mask[u] ^ new_mask
                if diff != 0:
                    bit_update(pos[u], diff)
                    current_mask[u] = new_mask
            else:
                u = int(parts[1])
                v = int(parts[2])
                mask = query_path(u, v)
                ans.append((mask & (mask - 1)) == 0)

        return ans