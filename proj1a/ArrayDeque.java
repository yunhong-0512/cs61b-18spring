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

        int oldStart = (nextFirst + 1) % items.length;
        int end = (nextLast - 1) % items.length;
        int newFirst = capacity- (items.length - nextFirst);
        int newStart = (newFirst + 1) % capacity;

        if (oldStart < end) {
            System.arraycopy(items, oldStart , arr, oldStart, size);
        } else if (oldStart == 0) {
            System.arraycopy(items, oldStart , arr, oldStart, size);
            nextFirst = newFirst;
        } else {
            System.arraycopy(items, oldStart , arr, newStart, size - end - 1);
            System.arraycopy(items, 0 , arr, 0, end + 1);
            nextFirst = newFirst;
        }

        items = arr;
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(5);
        a.addLast(6);
        a.addFirst(4);
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(1);
        a.addLast(7);
        a.addLast(8);
        a.addLast(8);
        a.addLast(8);
        a.printDeque();
        a.removeLast();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        System.out.println();
        a.printDeque();
        a.removeLast();
        a.removeLast();
        System.out.println();
        a.printDeque();
        a.removeLast();
        System.out.println();
        a.printDeque();
        a.removeLast();
        System.out.println();
        a.printDeque();
    }

}
