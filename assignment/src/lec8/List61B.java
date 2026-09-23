package lec8;

public interface List61B<T> {
    public static class node<T> {
        T val;
        DLLIST.node<T> next;
        DLLIST.node<T> pre;
    }

    public void addLast(T val);

    public T removeLast();

    public T getFront();

    public T getBack();

    public void printList();

    public int size();

    public boolean empty();
}
