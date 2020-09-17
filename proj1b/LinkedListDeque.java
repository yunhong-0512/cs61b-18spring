public class LinkedListDeque<T> implements Deque<T> {
    private Node<T> sentinel;
    private int size;

    /**
     * Create Node class
     */

    private class Node<T> {
        private Node prev;
        private T item;
        private Node next;

        /**
         * Node constructor
         */
        public Node(Node p, T item, Node n) {
            this.prev = p;
            this.item = item;
            this.next = n;
        }

        public Node() {
            this(null, null, null);
        }
    }

    /**
     * constructor, create an empty LinkedListDeque
     */
    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Adds an item to the front of the deque.
     */
    @Override
    public void addFirst(T item) {
        Node first = new Node(sentinel, item, sentinel.next);
        sentinel.next = first;
        first.next.prev = first;
        size += 1;
    }

    /**
     * Adds an item to the back of the deque.
     */
    @Override
    public void addLast(T item) {
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    @Override
    public void printDeque() {
        Node ptr = new Node(null, null, sentinel.next);
        ptr.next = sentinel.next;
        while (ptr.next != sentinel) {
            System.out.print(ptr.next.item + " ");
            ptr.next = ptr.next.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removeItem = (T) sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return removeItem;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T removeItem = (T) sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return  removeItem;
    }

    /**
     * Gets the item at the given index.
     */
    @Override
    public T get(int index) {
        if (index > size) {
            return null;
        }
        Node ptr = new Node(null, null, sentinel.next);
        for (int i = 0; i < index; i++) {
            ptr.next = ptr.next.next;
        }
        return (T) ptr.next.item;
    }

    /**
     * Gets the item at the given index using recursion.
     */
    public T getRecursive(int index) {
        if (index > size) {
            return null;
        }
        Node ptr = new Node(null, null, sentinel.next);
        return recursiveHelper(index, ptr);
    }

    private T recursiveHelper(int index, Node ptr) {
        if (index == 0) {
            return (T) ptr.next.item;
        }
        ptr.next = ptr.next.next;
        return recursiveHelper(index - 1, ptr);
    }


}
