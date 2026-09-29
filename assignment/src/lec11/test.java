package lec11;

import java.util.Iterator;

public class test
{
    static void main() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);
        System.out.println(set.contains(2));
        System.out.println(set.contains(4));

        System.out.println("========================");

        // for - each
        for (int i : set) {
            System.out.println(i);
        }

        System.out.println("========================");

        System.out.println(set);

        System.out.println("========================");

        ArraySet<String> aset1 = ArraySet.of("Hi", "I'm", "here!");
        System.out.println(aset1);
        ArraySet<String> aset2 = ArraySet.of("Hi", "I'm", "here!");
        System.out.println(aset1.equals(aset2));
    }
}
