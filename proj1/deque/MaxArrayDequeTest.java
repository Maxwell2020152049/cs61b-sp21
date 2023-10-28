package deque;

import org.junit.Test;

import java.util.Comparator;
import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    static class IntComparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            return i1 - i2;
        }
    }

    @Test
    public void maxTest() {
//        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<>(new IntComparator());
        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < 100; i++) {
            mad1.addFirst(i);
        }

        for (int i = 100; i < 200; i++) {
            mad1.addLast(i);
        }

        assertEquals(199, (int)mad1.max());
    }
}
