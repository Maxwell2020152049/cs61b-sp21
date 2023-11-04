package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    public void getTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        lld1.addFirst("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertEquals("front", lld1.get(0));
        assertEquals("middle", lld1.get(1));
        assertEquals("back", lld1.get(2));

        assertEquals("front", lld1.getRecursive(0));
        assertEquals("middle", lld1.getRecursive(1));
        assertEquals("back", lld1.getRecursive(2));
    }

    @Test
    public void iteratorTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        lld1.addFirst("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        for (String i : lld1) {
            System.out.println(i);
        }
    }

    @Test
    public void equalsTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();

        for (int i = 0; i < 10000; i++) {
            lld1.addLast(i);
            lld2.addLast(i);
        }

        assertEquals(lld1, lld2);

        lld1.removeFirst();

        assertNotEquals(lld1, lld2);
    }

    @Test
    public void equalsTest2() {
//        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
//        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
//
//        for (int i = 0; i < 10000; i++) {
//            lld1.addLast(i);
//            ad1.addLast(i);
//            assertEquals(lld1, ad1);
//        }
//
//        for (int i = 0; i < 10000; i++) {
//            lld1.addFirst(i);
//            ad1.addFirst(i);
//            assertEquals(lld1, ad1);
//        }

        LinkedListDeque<String> lld2 = new LinkedListDeque<>();
        ArrayDeque<String> ad2 = new ArrayDeque<>();

        lld2.addLast("20");
        lld2.addLast("20");
        lld2.addLast("15");
        lld2.addLast("20");
        lld2.addLast("49");
        lld2.addLast(null);
        lld2.addLast("20");
        lld2.addLast("20");
        lld2.addLast("15");
        lld2.addLast("20");
        lld2.addLast("49");

        ad2.addLast("20");
        ad2.addLast("20");
        ad2.addLast("15");
        ad2.addLast("20");
        ad2.addLast("49");
        ad2.addLast(null);
        ad2.addLast("20");
        ad2.addLast("20");
        ad2.addLast("15");
        ad2.addLast("20");
        ad2.addLast("49");

        assertEquals(lld2, ad2);
    }

    @Test
    public void randomTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();

        final int N = 10000;

        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 9);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                ad1.addLast(randVal);
                lld1.addLast(randVal);
            } else if (operationNumber == 1) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                ad1.addFirst(randVal);
                lld1.addFirst(randVal);
            } else if (operationNumber == 2) {
                // removeLast
                assertEquals(lld1.removeLast(), ad1.removeLast());
            } else if (operationNumber == 3) {
                // removeFirst
                assertEquals(lld1.removeFirst(), ad1.removeFirst());
            } else if (operationNumber == 4) {
                // size
                assertEquals(lld1.size(), lld1.size());
            } else if (operationNumber == 5) {
                // get
                int randVal = StdRandom.uniform(0, 10000);
                assertEquals(lld1.get(randVal), ad1.get(randVal));
            } else if (operationNumber == 6) {
                // equals
                assertEquals(lld1, ad1);
            } else if (operationNumber == 7) {
                // addLast null
                ad1.addLast(null);
                lld1.addLast(null);
            } else if (operationNumber == 8) {
                // addFirst null
                ad1.addFirst(null);
                lld1.addFirst(null);
            }
        }
    }

    @Test
    public void randomTest2() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();

        final int N = 10000;

        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 9);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                ad1.addLast(String.valueOf(randVal));
                lld1.addLast(String.valueOf(randVal));
            } else if (operationNumber == 1) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                ad1.addFirst(String.valueOf(randVal));
                lld1.addFirst(String.valueOf(randVal));
            } else if (operationNumber == 2) {
                // removeLast
                assertEquals(lld1.removeLast(), ad1.removeLast());
            } else if (operationNumber == 3) {
                // removeFirst
                assertEquals(lld1.removeFirst(), ad1.removeFirst());
            } else if (operationNumber == 4) {
                // size
                assertEquals(lld1.size(), lld1.size());
            } else if (operationNumber == 5) {
                // get
                int randVal = StdRandom.uniform(0, 10000);
                assertEquals(lld1.get(randVal), ad1.get(randVal));
            } else if (operationNumber == 6) {
                // equals
                assertEquals(lld1, ad1);
            } else if (operationNumber == 7) {
                // addLast null
                ad1.addLast(null);
                lld1.addLast(null);
            } else if (operationNumber == 8) {
                // addFirst null
                ad1.addFirst(null);
                lld1.addFirst(null);
            }
        }
    }
}
