package deque;

/**
 * 使用数组实现的双端队列
 * arr[head]是第一个元素
 * arr[last - 1]是最后一个元素 ( mod arr.length条件下)
 * 当 head == last 时，队列空
 * 当 (head == last + 1) mod arr.length 时，队列满
 * @param <T>
 */
public class ArrayDeque<T> {
    private T[] arr;
    private int head;
    private int last;

    ArrayDeque() {
        arr = (T[]) new Object[8];
        head = 0;
        last = 0;
    }

    public void addFirst(T x) {
        if (isFull()) {
            resize(arr.length * 2);
        }

        head = (head - 1 + arr.length) % arr.length;
        arr[head] = x;
    }

    public void addLast(T x) {
        if (isFull()) {
            resize(arr.length * 2);
        }

        arr[last] = x;
        last = (last + 1) % arr.length;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T x = arr[head];
        arr[head] = null;
        head = (head + 1) % arr.length;
        return x;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        last = (last - 1 + arr.length) % arr.length;
        T x = arr[last];
        arr[last] = null;
        return x;
    }

    public boolean isEmpty() {
        return head == last;
    }

    public boolean isFull() {
        return head == (last + 1) % arr.length;
    }

    public int size() {
        return (last - head + arr.length) % arr.length;
    }

    public void resize(int n) {
        T[] new_arr = (T[]) new Object[n];
        int h = head;
        int i = 0;
        while (arr[h] != null) {
            new_arr[i] = arr[h];
            h = (h + 1) % arr.length;
            i++;
        }
        head = 0;
        last = h;
        arr = new_arr;
    }

    public T get(int i) {
        if (isEmpty() || i >= size()) {
            return null;
        }

        return arr[(head + i) % arr.length];
    }

    public void printDeque() {
        int h = head;
        while (arr[h] != null) {
            System.out.print(arr[h] + " ");
            h = (h + 1) % arr.length;
        }
        System.out.println();
    }
}