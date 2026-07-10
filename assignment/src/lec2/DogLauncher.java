package lec2;

public class DogLauncher {
    public static void main(String[] args) {
        Dog smallDog = new Dog(5);
        Dog mediumDog = new Dog(25);
        Dog largeDog = new Dog(150);

        Dog[] manyDogs = new Dog[4];
        manyDogs[0] = smallDog;
        manyDogs[1] = mediumDog;
        manyDogs[2] = largeDog;
        manyDogs[3] = new Dog(45);

        Dog.biggerDog(smallDog, largeDog).makeNose();
        System.out.println("====");

        for (Dog d : manyDogs) {
            d.makeNose();
        }
    }
}
