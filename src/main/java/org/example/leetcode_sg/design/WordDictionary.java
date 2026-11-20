package org.example.leetcode_sg.design;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/design-add-and-search-words-data-structure/?envType=study-plan-v2&envId=top-interview-150">211. Design Add and Search Words Data Structure</a>
 *
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 * There will be at most 2 dots in word for search queries.
 */
public class WordDictionary {


    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("addd");
        wordDictionary.addWord("ass");
        System.out.println(wordDictionary.search("a."));
    }

    WordDictionary[] children;
    boolean isEnd;

    public WordDictionary() {
        this.children = new WordDictionary[26];
    }

    public void addWord(String word) {
        WordDictionary curWordDic = this;
        for (int i = 0; i < word.length(); i++) {
            if (curWordDic.children[word.charAt(i) - 'a'] == null) {
                curWordDic.children[word.charAt(i) - 'a'] = new WordDictionary();
            }
            curWordDic = curWordDic.children[word.charAt(i) - 'a'];
        }
        curWordDic.isEnd = true;
    }

    public boolean search(String word) {
        Queue<WordDictionary> queue = new ArrayDeque<>();
        queue.offer(this);
        for (int m = 0; m < word.length(); m++) {
            char c = word.charAt(m);
            if (queue.isEmpty()) {
                return false;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                WordDictionary poll = queue.poll();
                if (m == word.length() - 1) {

                }
                if (c == '.') {
                    for (int j = 0; j < 26; j++) {
                        if (poll != null && poll.children != null && poll.children[j] != null) {
                            queue.offer(poll.children[j]);
                        }
                    }
                } else {
                    if (poll != null && poll.children != null && poll.children[c - 'a'] != null) {
                        queue.offer(poll.children[c - 'a']);
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            WordDictionary poll = queue.poll();
            if (poll.isEnd) {
                return true;
            }
        }

        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */