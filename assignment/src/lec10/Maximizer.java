package lec10;

public class Maximizer {
    public static OurComparable max(OurComparable[] items) {
        int maxIndex = 0;
        for (int i = 0; i < items.length; i++) {
            int cmp = items[i].compareTo(items[maxIndex]);
            if (cmp > 0) {
                maxIndex = i;
            }
        }

        return items[maxIndex];
    }

    static void main() {
        Dog[] dogs = {new Dog("Bingo", 20), new Dog("Bongo", 10), new Dog("Mingo", 30)};
        Dog maxDog = (Dog) max(dogs);
        maxDog.bark();
    }
}
