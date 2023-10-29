package deque;

import java.util.Iterator;

/**
 * 使用数组实现的双端队列
 * arr[head]是第一个元素
 * arr[last - 1]是最后一个元素 ( mod arr.length条件下)
 * 当 head == last 时，队列空
 * 当 (head == last + 1) mod arr.length 时，队列满
 * @param <T>
 */
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] arr;
    private int head;
    private int last;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
        head = 0;
        last = 0;
    }

    @Override
    public void addFirst(T x) {
        if (isFull()) {
            resize(arr.length * 2);
        }

        head = (head - 1 + arr.length) % arr.length;
        arr[head] = x;
    }

    @Override
    public void addLast(T x) {
        if (isFull()) {
            resize(arr.length * 2);
        }

        arr[last] = x;
        last = (last + 1) % arr.length;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        if (size() > 8 && size() < arr.length / 4) {
            resize(arr.length / 4);
        }

        T x = arr[head];
        arr[head] = null;
        head = (head + 1) % arr.length;
        return x;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        if (size() > 8 && size() < arr.length / 4) {
            resize(arr.length / 4);
        }

        last = (last - 1 + arr.length) % arr.length;
        T x = arr[last];
        arr[last] = null;
        return x;
    }

    private boolean isFull() {
        return head == (last + 1) % arr.length;
    }

    @Override
    public int size() {
        return (last - head + arr.length) % arr.length;
    }

    private void resize(int n) {
        T[] newArr = (T[]) new Object[n];
        int h = head;
        int i = 0;
        while (arr[h] != null) {
            newArr[i] = arr[h];
            h = (h + 1) % arr.length;
            i++;
        }
        head = 0;
        last = i % arr.length;
        arr = newArr;
    }

    @Override
    public T get(int i) {
        if (isEmpty() || i >= size()) {
            return null;
        }

        return arr[(head + i) % arr.length];
    }

    @Override
    public void printDeque() {
        int h = head;
        while (arr[h] != null) {
            System.out.print(arr[h] + " ");
            h = (h + 1) % arr.length;
        }
        System.out.println();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int pos;
        public ArrayDequeIterator() {
            pos = head;
        }

        @Override
        public boolean hasNext() {
            return pos != last;
        }

        @Override
        public T next() {
            T x = arr[pos];
            pos = (pos + 1) % arr.length;
            return x;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ArrayDeque) {
            ArrayDeque<T> ad1 = (ArrayDeque<T>) obj;
            if (this.size() != ad1.size()) {
                return false;
            }

            Iterator<T> iterThis = this.iterator();
            Iterator<T> iterad1 = ad1.iterator();
            while (iterThis.hasNext()) {
                if (!iterThis.next().equals(iterad1.next())) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }
}
