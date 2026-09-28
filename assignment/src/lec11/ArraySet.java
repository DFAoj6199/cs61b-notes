package lec11;

import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {
    // A set achieved by an array
    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            sb.append(items[i]);
            sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        ArraySet<T> cmpSet = (ArraySet<T>) o;
        for (T item : this) {
            if (!cmpSet.contains(item)) return false;
        }
        return true;
    }

    private class arrayIterator implements Iterator<T> {
        private int index;
        public arrayIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T itemsToReturn = items[index];
            index++;
            return itemsToReturn;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new arrayIterator();
    }

    public void add(T value) {
        if (!contains(value)) {
            items[size] = value;
            size++;
        }
    }

    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
