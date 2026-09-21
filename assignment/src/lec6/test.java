package lec6;

public class test {
    static void main() {
        DLLIST list = new DLLIST<>();
        list.printList();
        list.addFirst(15);
        list.addFirst("3.9");
        list.addFirst(456);
        list.addLast(15);
        list.addLast("3.9");
        list.addLast(465);
        list.printList();
        list.removeLast();
        list.printList();
        System.out.println(list.size());
        System.out.println(list.empty());
    }
}
