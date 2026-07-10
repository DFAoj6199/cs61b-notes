package lec2;

public class DogLargerThanFourNeighbourTemp {
    public static void main(String[] args) {
        Dog[] dogs = new Dog[7];
        dogs[0] = new Dog(10);
        dogs[1] = new Dog(20);
        dogs[2] = new Dog(30);
        dogs[3] = new Dog(25);
        dogs[4] = new Dog(20);
        dogs[5] = new Dog(40);
        dogs[6] = new Dog(10);

        Dog[] res = DogLargerThanFourNeighbour(dogs);

        for (Dog d : res) {
            System.out.print(d.weights + " ");
        }
    }

    public static Dog[] DogLargerThanFourNeighbour(Dog[] dogs) {
        Dog[] res = new Dog[dogs.length];

        int cnt = 0;
        for (int i = 0; i < dogs.length; i++) {
            boolean biggest = true;
            for (int j = -2; j <= 2; j++) {
                if (validIndex(dogs, i + j) && (j != 0)) {
                    if (Dog.biggerDog(dogs[i], dogs[i + j]) == dogs[i + j]) {
                        biggest = false;
                    }
                }
            }
            if (biggest) {
                res[cnt] = dogs[i];
                cnt++;
            }
        }

        res = validArray(res, cnt);

        return res;
    }

    public static boolean validIndex(Dog[] dogs, int i) {
        if (i < 0) return false;
        if (i >= dogs.length) return false;
        return true;
    }

    public static Dog[] validArray(Dog[] dogs, int cnt) {
        Dog[] res = new Dog[cnt];

        for (int i = 0; i < cnt; i++) {
            res[i] = dogs[i];
        }

        return res;
    }
}
