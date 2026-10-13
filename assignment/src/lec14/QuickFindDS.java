package lec14;

public class QuickFindDS implements DisjointSets{
    private int[] id;

    public QuickFindDS(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    @Override
    public void connect(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
//        System.out.println("=====Debug Message=====");
//        for (int i : id) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        System.out.println("=====E     N     D=====");
        return id[p] == id[q];
    }
}
