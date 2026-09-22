package lec7;

public class test {
    static void main() {
        AList list = new AList();

        for (int i = 0; i < 100; i++) {
            list.addLast(i);
        }

        System.out.println(list.getLast());
        for (int i = 0; i < 100; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();

        System.out.println(list.getSize());
        System.out.println(list.empty());
    }
}
