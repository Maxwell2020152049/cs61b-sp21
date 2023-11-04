package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import java.util.ArrayDeque;

public class TestArrayDequeEC {
    private String Operations = "";

    @Test
    public void testArrayDeque() {
        final int N = 1000000;

        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();

        int Operation = 0;
        for (int i = 0; i < N; i++) {
            Operation = StdRandom.uniform(0, 4);

            if (Operation == 0) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                ads1.addFirst(randVal);
                sad1.addFirst(randVal);

                Operations += "addFirst(" + randVal + ")\n";
            } else if (Operation == 1) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                ads1.addLast(randVal);
                sad1.addLast(randVal);

                Operations += "addLast(" + randVal + ")\n";
            } else if (Operation == 2) {
                // removeFirst
                if (ads1.isEmpty() || sad1.isEmpty()) {
                    continue;
                }

                Operations += "removeFirst()\n";

                assertEquals(Operations, ads1.removeFirst(), sad1.removeFirst());
            } else if (Operation == 3) {
                // removeLast
                if (ads1.isEmpty() || sad1.isEmpty()) {
                    continue;
                }

                Operations += "removeLast()\n";

                assertEquals(Operations, ads1.removeLast(), sad1.removeLast());
            }
        }
    }
}
