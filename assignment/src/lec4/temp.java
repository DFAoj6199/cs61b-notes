package lec4;

public class temp {
    static void main() {
        IntList l = new IntList(15);
        l = new IntList(10, l);
        l = new IntList(5, l);
        l = new IntList(114, l);

        System.out.println(l.getSize());
        System.out.println(l.iterativeSize());
        System.out.println(l.get(4));
    }
}
