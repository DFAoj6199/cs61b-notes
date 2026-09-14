package lec4;

public class IntList {
    public int val;
    public IntList next;

    public IntList(int val, IntList next) {
        this.val = val;
        this.next = next;
    }

    public IntList(int val) {
        this.val = val;
        this.next = null;
    }

    public int getSize() {
        if (next == null) return 1;
        return 1 + next.getSize();
    }

    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize++;
            p = p.next;
        }
        return totalSize;
    }

    public int get(int index) {
        IntList p = this;
        for (int i = 0; i < index; i++) {
            p = p.next;
            if (p == null) {
                throw new IndexOutOfBoundsException("你这个链表没有第" + index + "个元素，检查一下吧孩子，你这个b链表索引应该从 0 到 " + (this.getSize() - 1) + "! <- 是感叹号，不是阶乘");
            }
        }
        return p.val;
    }
}
