package lab9;

import javax.swing.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
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
            right = left = null;
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
        if (key == null || p == null) {
            return null;
        } else {
            if (key.compareTo(p.key) < 0) {
                return getHelper(key, p.left);
            } else if (key.compareTo(p.key) > 0) {
                return getHelper(key, p.right);
            } else {
                return p.value;
            }
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
            p = new Node(key, value);
        }
        else {
            if (key.compareTo(p.key) < 0) {
                p.left = putHelper(key, value, p.left);
            } else if (key.compareTo(p.key) > 0) {
                p.right = putHelper(key, value, p.right);
            } else {
                p.value = value;
            }
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null || value == null) {
            return;
        }
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }


    private void preOrder(Set<K> s, Node p) {
        if (p == null) {
            return;
        }
        s.add(p.key);
        preOrder(s, p.left);
        preOrder(s, p.right);
    }
    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> s = new HashSet<>();
        preOrder(s, root);
        return s;
    }


    private Node findMin(Node p) {
        if (p == null) {
            return null;
        } else if (p.left == null) {
            return p;
        } else {
            return findMin(p.left);
        }
    }

    private Node removeHelper(K key, Node p) {
        Node tmp;
        if (p == null || key == null) {
            return null;
        }
        if (key.compareTo(p.key) < 0) {
            p.left = removeHelper(key, p.left);
        } else if (key.compareTo(p.key) > 0) {
            p.right = removeHelper(key, p.right);
        } else {
            if (p.left != null && p.right != null) {
                tmp = findMin(p.right);
                p.key = tmp.key;
                p.value = tmp.value;
                p.right = removeHelper(p.key, p.right);
            } else {
                if (p.left != null) {
                    p = p.right;
                } else {
                    p = p.left;
                }
            }
        }
        return p;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        V returnValue = get(key);
        removeHelper(key, root);
        return returnValue;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return this.keySet().iterator();
    }
}
