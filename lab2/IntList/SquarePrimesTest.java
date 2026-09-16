package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes2() {
        IntList list = IntList.of(13, 15, 17, 14, 16, 18);
        boolean changed = IntListExercises.squarePrimes(list);
        assertEquals("169 -> 15 -> 289 -> 14 -> 16 -> 18", list.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes3() {
        IntList list = IntList.of(14, 15, 18, 14, 16);
        boolean changed = IntListExercises.squarePrimes(list);
        assertEquals("14 -> 15 -> 18 -> 14 -> 16", list.toString());
        assertFalse(changed);
    }
}
