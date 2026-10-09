package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {

    private static class FindMax implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    private static class FindSmall implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    private static class FindLongest implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }

    private static class FindShortest implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length();
        }
    }

    public MaxArrayDeque<Integer> intArray(Comparator<Integer> c) {
        MaxArrayDeque<Integer> integer = new MaxArrayDeque<>(c);
        integer.addFirst(3);
        integer.addFirst(2);
        integer.addLast(1);
        integer.addFirst(4);
        integer.addFirst(75);
        integer.addFirst(42);
        return integer;
    }

    public MaxArrayDeque<String> stringArray(Comparator<String> c) {
        MaxArrayDeque<String> string = new MaxArrayDeque<>(c);
        string.addFirst("ZhangSan");
        string.addFirst("LiSi");
        string.addFirst("MaxArrayDeque");
        string.addFirst("ArrayDeque");
        string.addFirst("Java");
        string.addFirst("Go");
        string.addFirst("Deepseek");
        string.addFirst("ChatGPT");
        string.addFirst("Google");
        string.addFirst("Gzhu");
        string.addFirst("Baidu");
        return string;
    }
    @Test
    public void findMax() {
        Comparator<Integer> cmp = new FindMax();
        MaxArrayDeque<Integer> mad = intArray(cmp);
        assertEquals(Integer.valueOf(75), mad.max());

        Comparator<Integer> cmp2 = new FindSmall();
        MaxArrayDeque<Integer> mad2 = intArray(cmp2);
        assertEquals(Integer.valueOf(75), mad2.max(cmp));
    }

    @Test
    public void findSmall() {
        Comparator<Integer> big = new FindMax();
        Comparator<Integer> small = new FindSmall();

        MaxArrayDeque<Integer> bigArray = intArray(big);
        MaxArrayDeque<Integer> smallArray = intArray(small);


        assertEquals(Integer.valueOf(1), bigArray.max(small));
        assertEquals(Integer.valueOf(1), smallArray.max());
    }

    @Test
    public void findLongest() {
        Comparator<String> longest = new FindLongest();
        Comparator<String> shortest = new FindShortest();

        MaxArrayDeque<String> Long = stringArray(longest);
        MaxArrayDeque<String> Short = stringArray(shortest);

        assertEquals("MaxArrayDeque", Long.max());
        assertEquals("MaxArrayDeque", Short.max(longest));
    }

    @Test
    public void findShortest() {
        Comparator<String> longest = new FindLongest();
        Comparator<String> shortest = new FindShortest();

        MaxArrayDeque<String> Long = stringArray(longest);
        MaxArrayDeque<String> Short = stringArray(shortest);

        assertEquals("Go", Long.max(shortest));
        assertEquals("Go", Short.max());
    }

    @Test
    public void findLexicographicallyLargest() {
        MaxArrayDeque<String> big = stringArray((a, b) -> a.compareTo(b));
        MaxArrayDeque<String> small = stringArray((a, b) -> b.compareTo(a));

        assertEquals("ZhangSan", big.max());
        assertEquals("ZhangSan", small.max((a, b) -> a.compareTo(b)));
    }

    @Test
    public void findLexicographicallySmallest() {
        MaxArrayDeque<String> big = stringArray((a, b) -> a.compareTo(b));
        MaxArrayDeque<String> small = stringArray((a, b) -> b.compareTo(a));

        assertEquals("ArrayDeque", big.max((a, b) -> b.compareTo(a)));
        assertEquals("ArrayDeque", small.max());
    }
}
