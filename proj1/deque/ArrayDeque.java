package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
    private T[] arr;
    private int size;
    private int nextHead;
    private int nextTail;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
        size = 0;
        nextHead = 0;
        nextTail = 1;
    }

    @Override
    public Iterator<T> iterator() {
        return new arrayDequeIterator();
    }

    private class arrayDequeIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T itemToReturn = get(index++);
            return itemToReturn;
        }
    }

    private void resize(int newSize) {
        T[] newArr = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[(nextHead + 1 + i) % arr.length];
        }
        nextHead = newSize - 1;
        nextTail = size;
        arr = newArr;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return arr[(nextHead + 1 + index) % arr.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[(nextHead + 1 + i) % arr.length] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;
        if (size < arr.length / 4 && size >= 16) {
            resize(arr.length / 2);
        }

        T itemsToRemove = arr[(nextTail - 1 + arr.length) % arr.length];
        arr[(nextTail - 1 + arr.length) % arr.length] = null;
        nextTail = (nextTail - 1 + arr.length) % arr.length;
        size--;

        return itemsToRemove;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null;
        if (size < arr.length / 4 && size >= 16) {
            resize(arr.length / 2);
        }

        T itemsToRemove = arr[(nextHead + 1) % arr.length];
        arr[(nextHead + 1) % arr.length] = null;
        nextHead = (nextHead + 1) % arr.length;
        size--;

        return itemsToRemove;
    }

    @Override
    public void addFirst(T item) {
        if (size == arr.length) {
            resize(size * 2);
        }

        arr[nextHead] = item;
        nextHead = (nextHead - 1 + arr.length) % arr.length;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == arr.length) {
            resize(size * 2);
        }

        arr[nextTail] = item;
        nextTail = (nextTail + 1) % arr.length;
        size++;
    }
}
