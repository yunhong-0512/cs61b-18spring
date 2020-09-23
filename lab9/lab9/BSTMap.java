package lab9;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Yun Hong
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null || key == null) {
            return null;
        }
        if (key.equals(p.key)) {
            return p.value;
        } else if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        } else if (key.equals(p.key)) {
            p.value = value;
        } else if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.right = putHelper(key, value, p.right);
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        traverse(root, set);
        return set;
    }

    /**
     * traverse each node in the tree to record all keys
     * @param root
     * @param set
     */
    private void traverse(Node root, Set<K> set) {
        if (root == null) {
            return;
        }
        traverse(root.left, set);
        set.add(root.key);
        traverse(root.right, set);
    }


    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        size -= 1;
        V res = get(key);
        removeHelper(key, root);
        return res;
    }

    private Node removeHelper(K key, Node root) {
        if (key == null || root == null) {
            return null;
        }

        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = removeHelper(key, root.left);
        } else if (cmp > 0) {
            root.right = removeHelper(key, root.right);
        } else {
            if (root.right == null) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }
            root = removeTopHelper(root);
        }
        return root;
    }

    private Node removeTopHelper(Node root) {
        if (root == null) {
            return null;
        }
        Node ptr = root;
        //find min node
        while(root.left != null) {
            root = root.left;
        }
        root.left = ptr.left;
        root.right = removeMin(ptr.right);
        return root;
    }

    private Node removeMin(Node root) {
        if (root.left == null) {
            return root.right;
        }
        root.left = removeMin(root.left);
        return root;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (containsKey(key) && get(key).equals(value)) {
            return remove(key);
        } else {
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("starChild", 5);
        b.put("Child", 5);
        b.put("star", 5);
        b.remove("star");
        System.out.println(b.keySet());
    }
}
