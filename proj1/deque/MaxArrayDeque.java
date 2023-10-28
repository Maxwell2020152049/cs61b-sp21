package deque;

import java.util.Comparator;

/**
 *
 * @param <T>
 */
public class MaxArrayDeque<T> extends ArrayDeque<T>{
    Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        cmp = c;
    }

    public T max() {
        return max(cmp);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }

        int h = head;
        T maxx = arr[h];
        while (arr[h] != null) {
            if (c.compare(maxx, arr[h]) < 0) {
                maxx = arr[h];
            }
            h = (h + 1) % arr.length;
        }

        return maxx;
    }
}
