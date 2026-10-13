package lec14;

public class WeightedQuickUnion implements DisjointSets {
    private int[] parent;
    private int[] size;

    public WeightedQuickUnion(int N) {
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
            size[i] = 1;
        }
    }

    private int find(int i) {
        if (parent[i] == -1) {
            return i;
        }
        int root = find(parent[i]);
        parent[i] = root;
        return root;
    }

    @Override
    public void connect(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootQ == rootP) {
            return;
        }
        if (size[rootQ] > size[rootP]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
