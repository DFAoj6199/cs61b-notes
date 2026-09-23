package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> A = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        A.addLast(4);
        B.addLast(4);

        A.addLast(5);
        B.addLast(5);

        A.addLast(6);
        B.addLast(6);

        assertEquals(A.removeLast(), B.removeLast());
        assertEquals(A.removeLast(), B.removeLast());
        assertEquals(A.removeLast(), B.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
                // System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // getLast
                if (L.size() > 0) {
                    // System.out.println("getLast(): " + L.getLast());
                    assertEquals(L.getLast(), BL.getLast());
                }
            } else if (operationNumber == 2) {
                // removeLast
                if (L.size() > 0) {
                    assertEquals(L.removeLast(), BL.removeLast());
                    // System.out.println("removeLast()");
                }
            } else {
                // System.out.println("size: " + L.size());
                assertEquals(L.size(), BL.size());
            }
        }
    }

}
