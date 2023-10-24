package randomizedtest;

import edu.princeton.cs.algs4.In;
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
        AListNoResizing<Integer> a = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();

        a.addLast(4); b.addLast(4);
        a.addLast(5); b.addLast(5);
        a.addLast(6); b.addLast(6);

        assertEquals(a.size(), b.size());   // 3 == 3?

        assertEquals(a.removeLast(), b.removeLast());   // 6 == 6?
        assertEquals(a.removeLast(), b.removeLast());   // 5 == 5?
        assertEquals(a.removeLast(), b.removeLast());   // 4 == 4?
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
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = BL.size();
                assertEquals(size, size2);
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() == 0) {
                    continue;
                }
                int last = L.getLast();
                int last2 = BL.getLast();
                assertEquals(last, last2);
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() == 0) {
                    continue;
                }
                int last = L.removeLast();
                int last2 = BL.removeLast();
                assertEquals(last, last2);
            }
        }
    }
}
