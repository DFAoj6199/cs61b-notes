package lec10;

public class Launcher {
    static void main() {
        Dog[] dogs = {new Dog("Bingo", 20), new Dog("Bongo", 10), new Dog("Mingo", 30), new Dog("Ringo", 20)};
        Dog maxDog = Maximizer.max(dogs, Dog.getSizeComparator());
        maxDog.bark();
        Dog maxNameDog = Maximizer.max(dogs, Dog.getNameComparator());
        maxNameDog.bark();
    }
}
