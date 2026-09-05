package org.example.leetcode_sg.design;

/**
 * <a href="https://leetcode.com/problems/implement-trie-prefix-tree/description/">208. Implement Trie (Prefix Tree)</a>
 */
class Trie {

    boolean exist;
    Trie[] words;
    boolean ends;

    public Trie() {
        exist = true;
        words = new Trie[26];
    }

    public void insert(String word) {
        Trie dump = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (dump.words[index] == null) {
                dump.words[index] = new Trie();
            }
            dump = dump.words[index];
        }
        dump.ends = true;
    }

    public boolean search(String word) {
        Trie dump = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (dump == null || !dump.exist || dump.words == null || dump.words[index] == null || !dump.words[index].exist) {
                return false;
            }

            dump = dump.words[index];
        }

        return dump.ends;
    }

    public boolean startsWith(String prefix) {
        Trie dump = this;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (dump == null || !dump.exist || dump.words == null || dump.words[index] == null || !dump.words[index].exist) {
                return false;
            }

            dump = dump.words[index];
        }

        return dump.exist;
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */