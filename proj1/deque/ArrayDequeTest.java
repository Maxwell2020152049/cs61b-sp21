package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addIsEmptySizeTest() {

        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, ad1.size());
        assertFalse("ad1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

//        System.out.println("Printing out deque: ");
//        ad1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("ad1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        assertFalse("ad1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        // should be empty
        assertTrue("ad1 should be empty after removal", ad1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String>  ad1 = new ArrayDeque<String>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        ad1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = ad1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, ad1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, ad1.removeLast());
    }

    @Test
    /* Add small number of elements to deque; check if order is correct. */
    public void smallArrayDequeTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++) {
            ad1.addLast(i);
        }

//        ad1.printDeque();

        for (double i = 0; i < 50; i++) {
            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
        }

        for (double i = 99; i > 50; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            ad1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }
    }

    @Test
    public void getTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();

        ad1.addFirst("front");
        ad1.addLast("middle");
        ad1.addLast("back");

        assertEquals("front", ad1.get(0));
        assertEquals("middle", ad1.get(1));
        assertEquals("back", ad1.get(2));

        for (String i : ad1) {
            System.out.println(i);
        }
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
                assertEquals(ad1, lld1);
            } else if (operationNumber == 7) {
                // addLast null
                lld1.addLast(null);
                ad1.addLast(null);
            } else if (operationNumber == 8) {
                // addFirst null
                lld1.addFirst(null);
                ad1.addFirst(null);
            }
        }
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<>();

        for (int i = 0; i < 1000000; i++) {
            ad1.addLast(i);
            ad2.addLast(i);
        }

        assertEquals(ad1, ad2);

        ad1.removeFirst();

        assertNotEquals(ad1, ad2);
    }

    @Test
    public void equalsTest2() {
//        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
//        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
//
//        for (int i = 0; i < 1000000; i++) {
//            ad1.addLast(i);
//            lld1.addLast(i);
//        }
//
//        assertEquals(ad1, lld1);
//
//        ad1.removeFirst();
//
//        assertNotEquals(ad1, lld1);

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

        assertEquals(ad2, lld2);
    }
}
