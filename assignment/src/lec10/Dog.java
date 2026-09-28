package lec10;

public class Dog implements Comparable<Dog> {
    private String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public void bark() {
        System.out.println("Woof! My name is " + name + " and my size is " + size);
    }

    @Override
    public int compareTo(Dog o) {
        Dog uddaDog = (Dog) o;
        return this.size - uddaDog.size;
    }
}
