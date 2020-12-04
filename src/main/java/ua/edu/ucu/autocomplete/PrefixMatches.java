package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

/**
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        for (String array : strings) {
            String[] words = array.split(" ");
            for (String word : words) {
                if (word.length() > 2) {
                    this.trie.add(new Tuple(word, word.length()));
                }
            }
        }
        return this.trie.size();
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        return trie.wordsWithPrefixOfSize(pref, k);
    }

    public int size() {
        return trie.size();
    }
}
