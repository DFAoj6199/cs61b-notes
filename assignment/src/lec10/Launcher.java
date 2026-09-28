package lec10;

public class Launcher {
    static void main() {
        Dog[] dogs = {new Dog("Bingo", 20), new Dog("Bongo", 10), new Dog("Mingo", 30)};
        Dog maxDog = (Dog) Maximizer.max(dogs);
        maxDog.bark();
    }
}
