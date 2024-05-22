package org.example.leetcode;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jialu.yxl
 * @date 06/04/2023 19:24
 */
public class Trie {

    Trie[] subChars;
    boolean isEnd;

    public Trie() {
        subChars = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie trie = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            trie = trie.subChars[c - 'a'];
            if (trie == null) {
                trie = new Trie();
                trie.isEnd = false;
            }
        }
        trie.isEnd = true;
    }

    public boolean search(String word) {
        Trie trie = findPrefix(word);
        return trie != null && trie.isEnd;
    }

    public boolean startsWith(String prefix) {
        return findPrefix(prefix) != null;
    }

    public Trie findPrefix(String prefix) {
        Trie trie = this;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            trie = trie.subChars[c - 'a'];
            if (trie == null) {
                return null;
            }
        }
        return trie;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }

}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
