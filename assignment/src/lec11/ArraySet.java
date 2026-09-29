package lec11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArraySet<T> implements Iterable<T> {
    // A set achieved by an array
    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /**
     * A method to provide a convenient way to create an ArraySet
     * @param stuff All the item to add to the set
     * @return A whole ArraySet that contain the item in the param
     * @param <E>
     */
    public static <E> ArraySet<E> of(E... stuff) {
        ArraySet<E> setToReturn = new ArraySet<>();
        for (E item : stuff) {
            setToReturn.add(item);
        }
        return setToReturn;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder("{");
//        for (int i = 0; i < size; i++) {
//            sb.append(items[i]);
//            sb.append(", ");
//        }
//        sb.append("}");
//        return sb.toString();
//    }

    /*A better version of toString method
    * use String.join() method*/
    @Override
    public String toString() {
        List<String> returnString = new ArrayList<>();
        for (T item : this) {
            returnString.add(item.toString());
        }
        return "{" + String.join(", ", returnString) + "}";
    }

//    @Override
//    public boolean equals(Object o) {
//        if (o == null) return false;
//        if (this == o) return true;
//        if (this.getClass() != o.getClass()) return false;
//        ArraySet<T> cmpSet = (ArraySet<T>) o;
//        for (T item : this) {
//            if (!cmpSet.contains(item)) return false;
//        }
//        return true;
//    }

    /* New way to override equals method*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof ArraySet oas) {
            if (oas.size != size) return false;
            for (T item : this) {
                if (!oas.contains(item)) return false;
            }
            return true;
       }
        return false;
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
            if (items[i] == null) {
                if (value == null) return true;
            }
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
