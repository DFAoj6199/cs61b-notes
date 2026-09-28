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

        Iterator<Integer> seer = set.iterator();
        while(seer.hasNext()) {
            System.out.println(seer.next());
        }

        // for - each
        for (int i : set) {
            System.out.println(i);
        }
    }
}
