package ua.edu.ucu.tries;

import ua.edu.ucu.queue.Queue;

import java.util.Arrays;
import java.util.List;

public class RWayTrie implements Trie {
    private static final int CONVERT_INDEX = 97;
    private static final int R = 26;
    private int size = 0;
    private Node root = new Node();

    private Object get(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c - CONVERT_INDEX], key, d + 1);
    }

    @Override
    public void add(Tuple t) {
        if (t.term == null) {
            throw new IllegalArgumentException();
        }
        root = add(root, t.term, t.weight, 0);
    }

    private Node add(Node x, String key, int val, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            if (x.val == null) {
                size += 1;
            }
            x.val = val;
            return x;

        }
        char c = key.charAt(d);
        x.next[c - CONVERT_INDEX] = add(x.next[c - CONVERT_INDEX],
                key, val, d + 1);
        return x;
    }

    @Override
    public boolean contains(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        return get(word) != null;
    }

    @Override
    public boolean delete(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        if (contains(word)) {
            root = delete(root, word, 0);
            return true;
        }
        return false;
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            throw new IllegalArgumentException();
        }
        if (d == key.length()) {
            if (x.val == null) {
                size -= 1;
            }
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c - CONVERT_INDEX] = delete(x.next[c - CONVERT_INDEX],
                    key, d + 1);
        }
        if (x.val != null) {
            return x;
        }
        for (int c = 0; c < R; c++) {
            if (x.next[c - CONVERT_INDEX] != null) {
                return x;
            }
        }
        return null;
    }

    private void collect(Node x, String prefix, Queue queue, int k) {
        if (x == null) {
            return;
        }
        if ((x.val != null) && (k-- > 0)) {
            queue.enqueue(prefix);
        }
        for (int i = 0; i < R; i++) {
            collect(x.next[i], prefix + (char) (CONVERT_INDEX + i), queue, k);
        }
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        return wordsWithPrefixOfSize(s, size);
    }

    @Override
    public Iterable<String> wordsWithPrefixOfSize(String s, int k) {
        Queue results = new Queue();
        Node x = get(root, s, 0);
        collect(x, s, results, k);
        List result = Arrays.asList(results.toArray());
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }
}
