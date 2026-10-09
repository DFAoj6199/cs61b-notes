package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
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

    private class LinkedListDequeIterator implements Iterator<T> {
        Node<T> current = sentinelNode.next;

        @Override
        public boolean hasNext() {
            return current != sentinelNode;
        }

        @Override
        public T next() {
            T itemsToReturn = current.data;
            current = current.next;
            return itemsToReturn;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    /**
     * 递归获取链表第 index 个元素的值
     *
     * @param n     当前指针指向的元素
     * @param index 需要找到的索引
     * @return 元素的值
     */
    private T recursive(Node<T> n, int index) {
        if (index == 0) {
            return n.data;
        }
        return recursive(n.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return recursive(sentinelNode.next, index);
    }

    @Override
    public void addFirst(T item) {
        Node<T> newNode = new Node<>();
        newNode.data = item;

        newNode.next = sentinelNode.next;
        newNode.pre = sentinelNode;
        newNode.next.pre = newNode;
        sentinelNode.next = newNode;

        size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> newNode = new Node<>();
        newNode.data = item;

        newNode.pre = sentinelNode.pre;
        newNode.next = sentinelNode;
        newNode.pre.next = newNode;
        sentinelNode.pre = newNode;

        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (Node<T> p = sentinelNode.next; p != sentinelNode; p = p.next) {
            System.out.print(p.data + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Node<T> itemsToReturn = sentinelNode.next;

        sentinelNode.next = itemsToReturn.next;
        itemsToReturn.next.pre = sentinelNode;

        itemsToReturn.pre = null;
        itemsToReturn.next = null;

        size--;
        return itemsToReturn.data;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        Node<T> itemsToReturn = sentinelNode.pre;

        sentinelNode.pre = itemsToReturn.pre;
        itemsToReturn.pre.next = sentinelNode;

        itemsToReturn.pre = null;
        itemsToReturn.next = null;

        size--;
        return itemsToReturn.data;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Deque)) {
            return false;
        }

        Deque<?> dq = (Deque<?>) o;

        if (dq.size() != this.size()) {
            return false;
        }

        Iterator<T> thisIterator = this.iterator();
        Iterator<?> dqIterator = dq.iterator();

        while (thisIterator.hasNext() && dqIterator.hasNext()) {
            if (!thisIterator.next().equals(dqIterator.next())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node<T> p = sentinelNode.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }

        return p.data;
    }
}
