package lec10;

import java.util.Comparator;

public class Maximizer {
    public static <T> T max(T[] items, Comparator<T> comparator) {
        int maxIndex = 0;
        for (int i = 0; i < items.length; i++) {
            int cmp = comparator.compare(items[i], items[maxIndex]);
            if (cmp > 0) {
                maxIndex = i;
            }
        }

        return items[maxIndex];
    }


}
