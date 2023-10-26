package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private Item sentinel;
    private int size;
    private class Item {
        Item prev;
        T item;
        Item next;

        Item(Item p ,T i, Item n) {
            prev = p;
            item = i;
            next = n;
        }

        /**
         * Recursive version get.
         */
        public T get(int i) {
            if (i == 0) {
                return item;
            }
            return next.get(i - 1);
        }
    }
    public LinkedListDeque() {
        sentinel = new Item(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T x) {
        Item temp = new Item(sentinel, x, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;
        size ++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size --;
        return first.item;
    }

    @Override
    public void addLast(T x) {
        Item temp = new Item(sentinel.prev, x, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;
        size++;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        size --;
        return last.item;
    }

    @Override
    public T get(int i) {
        if (isEmpty() || i >= size()) {
            return null;
        }

        Item p = sentinel.next;
        while (true) {
            if (i == 0) {
                return p.item;
            }
            p = p.next;
            i --;
        }
    }

    public T getRecursive(int i) {
        return sentinel.get(i);
    }

    @Override
    public void printDeque() {
        Item p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

}
