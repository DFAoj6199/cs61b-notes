package lec14;

public class QuickUnionDS implements DisjointSets{
    private int[] parent;

    public QuickUnionDS(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
        }
    }

    private int root(int i) {
        if (parent[i] == -1) {
            return i;
        }
        return root(parent[i]);
    }

    @Override
    public void connect(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        if (pRoot == qRoot) return;

        parent[pRoot] = qRoot;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }
}
