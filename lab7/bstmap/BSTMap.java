package bstmap;

import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public BSTMap() {
        root.key = null;
        root.val = null;
        size = 0;
    }

    @Override
    public void clear() {
        size = 0;
        root.left = null;
        root.right = null;
        root.key = null;
        root.val = null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsK(root, key);
    }

    private boolean containsK(Node root, K key) {
        if (root == null) {
            return false;
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            return containsK(root.left, key);
        }
        if (cmp > 0) {
            return containsK(root.right, key);
        }
        return true;
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
        return getVal(root, key);
    }

    private V getVal(Node root, K key) {
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            return getVal(root.left, key);
        }
        if (cmp > 0) {
            return getVal(root.right, key);
        }
        return root.val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            return;
        }
        root = putK(root, key, value);
    }

    private Node putK(Node root, K key, V value) {
        if (root == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = putK(root.left, key, value);
        }
        if (cmp > 0) {
            root.right = putK(root.right, key, value);
        }
        return root;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        root = delete(root, key);
        return get(key);
    }

    private Node delete(Node root, K key) {
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = delete(root.left, key);
        } else if (cmp > 0) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null && root.right != null) {
                return root.right;
            } else if (root.left != null && root.right == null) {
                return root.left;
            } else {
                Node tmp = root;
                root = findMin(tmp.right);
                root.right = deleteMin(tmp.right);
                root.left = tmp.left;
            }
        }
        return root;
    }

    private Node deleteMin(Node root) {
        if (root.left == null) return root.right;
        root.left = deleteMin(root.left);
        return root;
    }

    private Node findMin(Node root) {
        Node tmp = root;
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        return tmp;
    }

}
