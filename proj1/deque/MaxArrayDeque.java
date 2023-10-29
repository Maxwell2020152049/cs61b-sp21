package deque;

import java.util.Comparator;

/**
 *
 * @param <T>
 */
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

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

        T maxx = null;
        for (T i : this) {
            if (maxx == null || c.compare(maxx, i) < 0) {
                maxx = i;
            }
        }
        return maxx;
    }
}
