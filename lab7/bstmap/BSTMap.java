package bstmap;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size;

    @Override
    public Iterator<K> iterator() {
        return new mapIterator();
    }

    private class mapIterator implements Iterator<K> {
        Stack<Node> stk;

        public mapIterator() {
            stk = new Stack<>();
            pushLeftTree(BSTMap.this.root);
        }

        private void pushLeftTree(Node node) {
            while (node.left != null) {
                stk.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stk.empty();
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node cur = stk.pop();
            pushLeftTree(cur.right);
            return cur.key;
        }
    }

    @Override
    public void forEach(Consumer<? super K> action) {
        throw new UnsupportedOperationException();
    }

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public Node() {
            this.key = null;
            this.val = null;
        }
    }

    public BSTMap() {
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
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
        V retV = get(key);
        if (retV == null) return null;
        size--;
        root = delete(root, key);
        return retV;
    }

    private Node delete(Node root, K key) {
        if (root == null) return null;
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
                Node successor = findMin(root.right);
                root.key = successor.key;
                root.val = successor.val;
                root.right = deleteMin(root.right);
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

    @Override
    public V remove(K key, V value) {
        if (!containsKey(key) || get(key) != value) {
            return null;
        }
        return remove(key);
    }


}
