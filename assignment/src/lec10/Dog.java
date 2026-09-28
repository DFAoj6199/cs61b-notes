package lec10;

import java.util.Comparator;

public class Dog {
    private String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println("Woof! My name is " + name + " and my size is " + size);
    }

    private static class sizeComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog d1, Dog d2) {
            return d1.size - d2.size;
        }
    }

    private static class nameComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    public static Comparator<Dog> getSizeComparator() {
        return new sizeComparator();
    }

    public static Comparator<Dog> getNameComparator() {
        return new nameComparator();
    }
}
