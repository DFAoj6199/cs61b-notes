package lec14;

public class DSDemo {
    static void main() {
        QuickUnionDS qf = new QuickUnionDS(7);
        qf.connect(0, 1);
        qf.connect(0, 1);
        qf.connect(0, 4);
        System.out.println(qf.isConnected(4, 2));

        qf.connect(3, 5);
        System.out.println(qf.isConnected(2, 3));

        qf.connect(2, 3);
        System.out.println(qf.isConnected(4, 5));
    }
}
