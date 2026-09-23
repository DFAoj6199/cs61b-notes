package lec8;

public class AList<T> implements List61B<T> {
    private T[] arr;
    private int size;

    public AList() {
        size = 0;
        arr = (T[]) new Object[10];
    }

    private void resize(int newLength) {
        T[] newArr = (T[]) new Object[newLength];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    @Override
    public void addLast(T x) {
        if (size == arr.length) {
            resize(size + 1);
        }
        arr[size] = x;
        size++;
    }

    @Override
    public T getFront() {
        return arr[0];
    }

    @Override
    public T getBack() {
        return arr[size - 1];
    }

    @Override
    public void printList() {
        if (empty()) {
            System.out.println("List is empty");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    @Override
    public int size() {
        return 0;
    }

    public T getLast() {
        return arr[size - 1];
    }

    public T get(int i) {
        return arr[i];
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public T removeLast() {
        T returnItem = getLast();
        arr[size - 1] = null;
        size--;
        return returnItem;
    }
}
