package lec5;

public class demo {
    static void main() {
        SLList list = new SLList();
        list.addFirst("15");
        list.addFirst(15);
        list.addLast('a');
        list.addFirst(true);
        list.addLast(15);
        list.printList();
        System.out.println(list.size());
        System.out.println(list.empty());


        list.removeFirst();
        list.printList();

        System.out.println("--------------------------");

        SLList list2 = new SLList();
        list2.removeFirst();
    }
}
