public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /**
     * constructor, create an empty LinkedListDeque
     */
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }

    /**
     * Adds an item to the front of the deque.
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
    }

    /**
     * Adds an item to the back of the deque.
     */
    public void addLast(T item) {
        if (size >= items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = (nextLast + 1) % items.length;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size * 100 / items.length < 25) {
            resize(items.length / 2);
        }
        size -= 1;
        nextFirst = (nextFirst + 1) % items.length;
        return items[nextFirst];
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size * 100 / items.length < 25) {
            resize(items.length / 2);
        }

        size -= 1;
        nextLast = (nextLast - 1 + items.length) % items.length;
        return items[nextLast];
    }

    /**
     * Gets the item at the given index.
     */
    public T get(int index) {
        return items[(nextFirst + index + 1) % items.length];
    }

    /**
     * resize the arrayList
     */
    private void resize(int capacity) {
        T[] arr = (T[]) new Object[capacity];
        int start = (nextFirst + 1) % items.length;
        if (capacity > items.length) {
            System.arraycopy(items, start, arr, 1, size - start);
            System.arraycopy(items, 0, arr, 1 + size - start, start);

        } else {
            if (start < nextLast) {
                System.arraycopy(items, start, arr, 1, size);
            } else {
                System.arraycopy(items, start, arr, 1, items.length - start);
                System.arraycopy(items, 0, arr, items.length - start + 1, size - items.length + start);
            }
        }
        nextFirst = 0;
        nextLast = size + 1;
        items = arr;
    }

}
