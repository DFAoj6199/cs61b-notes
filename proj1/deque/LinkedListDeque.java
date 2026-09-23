package deque;

public class LinkedListDeque<T> {
    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> pre;
    }

    private Node<T> sentinelNode;
    private int size;

    public LinkedListDeque() {
        sentinelNode = new Node<>();
        sentinelNode.next = sentinelNode;
        sentinelNode.pre = sentinelNode;
        size = 0;
    }

    /**
     * 递归获取链表第 index 个元素的值
     *
     * @param n 当前指针指向的元素
     * @param index 需要找到的索引
     * @return 元素的值
     */
    private T recursive(Node<T> n, int index) {
        if (index == 0) return n.data;
        return recursive(n.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) return null;
        return recursive(sentinelNode.next, index);
    }

    public void addFirst(T item) {
        Node<T> newNode = new Node<>();
        newNode.data = item;

        newNode.next = sentinelNode.next;
        newNode.pre = sentinelNode;
        newNode.next.pre = newNode;
        sentinelNode.next = newNode;

        size++;
    }

    public void addLast(T item) {
        Node<T> newNode = new Node<>();
        newNode.data = item;

        newNode.pre = sentinelNode.pre;
        newNode.next = sentinelNode;
        newNode.pre.next = newNode;
        sentinelNode.pre = newNode;

        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (Node<T> p = sentinelNode.next; p != sentinelNode; p = p.next) {
            System.out.print(p.data + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) return null;

        Node<T> itemsToReturn = sentinelNode.next;

        sentinelNode.next = itemsToReturn.next;
        itemsToReturn.next.pre = sentinelNode;

        itemsToReturn.pre = null;
        itemsToReturn.next = null;

        return itemsToReturn.data;
    }

    public T removeLast() {
        if (isEmpty()) return null;

        Node<T> itemsToReturn = sentinelNode.pre;

        sentinelNode.pre = itemsToReturn.pre;
        itemsToReturn.pre.next = sentinelNode;

        itemsToReturn.pre = null;
        itemsToReturn.next = null;

        return itemsToReturn.data;
    }

    public T get(int index) {
        if (index < 0 || index >= size) return null;

        Node<T> p = sentinelNode.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }

        return p.data;
    }
}
