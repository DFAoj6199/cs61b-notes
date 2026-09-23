package lec8;

public class SLList<T> implements List61B<T> {
    public class IntNode {
        public T val;
        public IntNode next;

        public IntNode(T val, IntNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private IntNode sentinelNode = new IntNode(null, null);
    private int size = 0;

    public SLList(T x) {
        sentinelNode.next = new IntNode(x, null);
        size++;
    }

    public SLList() {
    }

    public void addFirst(T x) {
        IntNode newFirst = new IntNode(x, sentinelNode.next);
        sentinelNode.next = newFirst;
        size++;
    }

    public T getFirst() {
        if (sentinelNode.next == null) throw new RuntimeException("链表为空！");
        return sentinelNode.next.val;
    }

    @Override
    public void addLast(T x) {
        IntNode p = sentinelNode;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
        size++;
    }

    @Override
    public T removeLast() {
        if (empty()) return null;
        IntNode p = sentinelNode;
        while (p.next.next != null) {
            p = p.next;
        }
        T returnItem = p.next.val;
        p.next = null;

        size--;
        return returnItem;
    }

    @Override
    public T getFront() {
        return get(0);
    }

    @Override
    public T getBack() {
        return get(size - 1);
    }

    @Override
    public void printList() {
        IntNode p = sentinelNode.next;
        if (p == null) {
            System.out.println("链表为空！");
            return;
        }
        while (p.next != null) {
            System.out.print(p.val + " -> ");
            p = p.next;
        }
        System.out.println(p.val);
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("你这个链表没有第" + index + "个元素，检查一下吧孩子，你这个b链表索引应该从 0 到 " + (size - 1) + "! <- 是感叹号，不是阶乘");
        }
        IntNode p = sentinelNode.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.val;
    }

//    private int getSize(IntNode node) {
//        if (node == null) return 0;
//        return 1 + getSize(node.next);
//    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    public boolean removeFirst() {
        if (sentinelNode.next == null) {
            System.out.println("链表为空，无法删除元素！");
            return false;
        }
        size--;
        sentinelNode.next = sentinelNode.next.next;
        return true;
    }

}
