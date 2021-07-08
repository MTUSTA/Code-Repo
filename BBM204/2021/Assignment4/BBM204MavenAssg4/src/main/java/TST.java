import java.util.ArrayList;
import java.util.List;

public class TST<Value> {
    public int n;
    public Node<Value> root;

    public static class Node<Value> {
        public char c;
        public Node<Value> left, mid, right;
        public Value val;
    }

    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public Value get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with null argument");
        }
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        Node<Value> x = get(root, key, 0);
        if (x == null) return null;
        return x.val;
    }

    // return subtrie corresponding to given key
    private Node<Value> get(Node<Value> x, String key, int d) {
        if (x == null) return null;
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        char c = key.charAt(d);
        if (c < x.c)                    return get(x.left, key, d);
        else if (c > x.c)               return get(x.right, key, d);
        else if (d < key.length() - 1)  return get(x.mid, key, d + 1);
        else                            return x;
    }

    // Inserts the key value pair into ternary search tree
    public void put(String key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with null key");
        }
        if (!contains(key)) n++;
        else if (val == null) n--;       // delete existing key
        root = put(root, key, val, 0);
    }

    private Node<Value> put(Node<Value> x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<Value>();
            x.c = c;
        }
        if (c < x.c)                    x.left = put(x.left, key, val, d);
        else if (c > x.c)               x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1)  x.mid = put(x.mid, key, val, d + 1);
        else                            x.val = val;
        return x;
    }

    // Returns a list of values using the given prefix
    public List<Value> valuesWithPrefix(String prefix) {
        /* Code here */
        if (prefix == null) {
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
        }
        List<String> arr1 = new ArrayList<>();
        Node<Value> x = get(root, prefix, 0);
        if (x == null) return new ArrayList<>();
        if (x.val != null) arr1.add(prefix);
        collect(x.mid, new StringBuilder(prefix), arr1);

        List<Value> result = new ArrayList<>();
        for (String item: arr1) {
            result.add(this.get(item));
        }
        return result;
    }

    // all keys in subtrie rooted at x with given prefix
    private void collect(Node<Value> x, StringBuilder prefix, List<String> queue) {
        if (x == null) return;
        collect(x.left,  prefix, queue);
        if (x.val != null) queue.add(prefix.toString() + x.c);
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }
}