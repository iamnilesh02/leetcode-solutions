import java.util.HashMap;
import java.util.Map;

class Solution {
    static class UnionFind {
        int[] parent;
        int[] size;
        int count;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]);
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                if (size[rootI] < size[rootJ]) {
                    parent[rootI] = rootJ;
                    size[rootJ] += size[rootI];
                } else {
                    parent[rootJ] = rootI;
                    size[rootI] += size[rootJ];
                }
                count--;
            }
        }
    }

    public int[] groupStrings(String[] words) {
        int n = words.length;
        UnionFind uf = new UnionFind(n);
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int mask = 0;
            for (char c : words[i].toCharArray()) {
                mask |= (1 << (c - 'a'));
            }

            if (map.containsKey(mask)) {
                uf.union(i, map.get(mask));
            } else {
                map.put(mask, i);
            }
        }

        for (int mask : map.keySet()) {
            int i = map.get(mask);

            for (int j = 0; j < 26; j++) {
                if ((mask & (1 << j)) != 0) {
                    int delMask = mask ^ (1 << j);
                    if (map.containsKey(delMask)) {
                        uf.union(i, map.get(delMask));
                    }

                    for (int k = 0; k < 26; k++) {
                        if ((mask & (1 << k)) == 0) {
                            int replaceMask = delMask | (1 << k);
                            if (map.containsKey(replaceMask)) {
                                uf.union(i, map.get(replaceMask));
                            }
                        }
                    }
                }
            }
        }

        int maxGroup = 0;
        for (int i = 0; i < n; i++) {
            if (uf.parent[i] == i) {
                maxGroup = Math.max(maxGroup, uf.size[i]);
            }
        }

        return new int[]{uf.count, maxGroup};
    }
}