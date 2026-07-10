package lec2;

public class Dog {
    public int weights;

    public Dog(int weights) {
        this.weights = weights;
    }

    public void makeNose() {
        if (weights < 10) {
            System.out.println("yiayia~");
        } else if (weights < 30) {
            System.out.println("bark.");
        } else {
            System.out.println("wooooooooooooooooooo!");
        }
    }

    public static Dog biggerDog(Dog d1, Dog d2) {
        if (d1.weights > d2.weights) {
            return d1;
        }
        return d2;
    }
}
