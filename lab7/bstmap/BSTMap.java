package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private static class BSTNode<K, V> {
        K key;      // key of map
        V value;    // value of map

        BSTNode<K, V> lchild;
        BSTNode<K, V> rchild;

        BSTNode(K k, V v) {
            key = k;
            value = v;

            lchild = null;
            rchild = null;
        }
    }

    private BSTNode<K, V> root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return contain(root, key);
    }

    private boolean contain(BSTNode<K, V> bst, K key) {
        if (bst == null) {
            return false;
        }
        if (bst.key.compareTo(key) == 0) {
            return true;
        } else if (bst.key.compareTo(key) > 0) {
            return contain(bst.lchild, key);
        } else {
            return contain(bst.rchild, key);
        }
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
        return get1(root, key);
    }

    private V get1(BSTNode<K, V> bst, K key) {
        if (bst.key.compareTo(key) == 0) {
            return bst.value;
        } else if (bst.key.compareTo(key) > 0) {
            return get1(bst.lchild, key);
        } else {
            return get1(bst.rchild, key);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = insert(root, key, value);
        size ++;
    }

    private BSTNode<K, V> insert(BSTNode<K, V> bst, K key, V value) {
        if (bst == null) {
            return new BSTNode<K, V>(key, value);
        }
        if (bst.key.compareTo(key) > 0) {
            bst.lchild = insert(bst.lchild, key, value);
        } else if (bst.key.compareTo(key) < 0) {
            bst.rchild = insert(bst.rchild, key, value);
        } else {
            bst.value = value;
        }
        return bst;
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        keySet1(root, set);
        return set;
    }

    private void keySet1(BSTNode<K, V> bst, HashSet<K> set) {
        if (bst == null) {
            return;
        }
        set.add(bst.key);
        keySet1(bst.lchild, set);
        keySet1(bst.rchild, set);
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        V value = get(key);
        if (value != null) {
            root = delete(root, key);
            size --;
        }
        return value;
    }

    private BSTNode<K, V> delete(BSTNode<K, V> bst, K key) {
        if (bst.key.compareTo(key) == 0) {
            if (bst.lchild == null) {
                bst = bst.rchild;
            } else if (bst.rchild == null) {
                bst = bst.lchild;
            } else {
                BSTNode<K, V> pre = bst.lchild;
                while (pre.rchild != null) {
                    pre = pre.rchild;
                }
                bst = delete(bst, pre.key);
                bst.key = pre.key;
                bst.value = pre.value;
            }
        } else if (bst.key.compareTo(key) > 0) {
            bst.lchild = delete(bst.lchild, key);
        } else {
            bst.rchild = delete(bst.rchild, key);
        }
        return bst;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (get(key) == value) {
            remove(key);
            size --;
            return value;
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        preorder(root);
    }

    private void preorder(BSTNode<K, V> t) {
        if (t == null) {
            return;
        }
        preorder(t.lchild);
        System.out.println("(" + t.key + ", " + t.value + ")");
        preorder(t.rchild);
    }
}
