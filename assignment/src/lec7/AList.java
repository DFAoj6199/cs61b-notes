package lec7;

public class AList<T> {
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

    public void addLast(T x) {
        if (size == arr.length) {
            resize(size + 1);
        }
        arr[size] = x;
        size++;
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

    public boolean empty() {
        return size == 0;
    }

    public T deleteBack() {
        T returnItem = getLast();
        arr[size - 1] = null;
        size--;
        return returnItem;
    }
}
