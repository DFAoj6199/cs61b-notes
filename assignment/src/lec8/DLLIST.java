package lec8;

public class DLLIST<T> implements List61B<T> {
    public static class node<T> {
        T val;
        node<T> next;
        node<T> pre;
    }

    private node<T> sentinelNode = new node();
    private int size;

    public DLLIST() {
        sentinelNode.next = sentinelNode;
        sentinelNode.pre = sentinelNode;
        size = 0;
    }

    public void addFirst(T val) {
        node<T> newNode = new node<>();
        newNode.val = val;

        sentinelNode.next.pre = newNode;
        newNode.next = sentinelNode.next;
        newNode.pre = sentinelNode;
        sentinelNode.next = newNode;
        size++;
    }

    @Override
    public void addLast(T val) {
        node<T> newNode = new node<>();
        newNode.val = val;

        sentinelNode.pre.next = newNode;
        newNode.next = sentinelNode;
        newNode.pre = sentinelNode.pre;
        sentinelNode.pre = newNode;
        size++;
    }

    public void removeFirst() {
        if (empty()) return;

        sentinelNode.next.next.pre = sentinelNode;
        sentinelNode.next = sentinelNode.next.next;

        size--;
    }

    @Override
    public T removeLast() {
        if (empty()) return null;

        T returnItem = sentinelNode.pre.val;
        sentinelNode.pre = sentinelNode.pre.pre;
        sentinelNode.pre.next = sentinelNode;

        size--;
        return returnItem;
    }

    @Override
    public T getFront() {
        if (empty()) {
            throw new RuntimeException("List is empty");
        }

        return sentinelNode.next.val;
    }

    @Override
    public T getBack() {
        if (empty()) {
            throw new RuntimeException("List is empty");
        }

        return sentinelNode.pre.val;
    }

    @Override
    public void printList() {
        if(empty()) {
            System.out.println("List is empty");
            return;
        }
        node<T> p = sentinelNode.next;
        while (p.next != sentinelNode) {
            System.out.print(p.val + " -> ");
            p = p.next;
        }
        System.out.println(p.val);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }
}
