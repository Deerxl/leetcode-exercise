package org.example.leetcode_sg.classic;

import java.util.*;

public class HashMapProblems {


    /**
     * <a href="https://leetcode.com/problems/group-anagrams/?envType=study-plan-v2&envId=top-interview-150">49. Group Anagrams</a>
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String sortedStr = new String(arr);
            if (map.containsKey(sortedStr)) {
                map.get(sortedStr).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sortedStr, list);
            }
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/valid-anagram/?envType=study-plan-v2&envId=top-interview-150">242. Valid Anagram</a>
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            arr[index]++;
        }
        for (char c : t.toCharArray()) {
            int index = c - 'a';
            arr[index]--;
            if (arr[index] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * <a href="https://leetcode.com/problems/word-pattern/?envType=study-plan-v2&envId=top-interview-150">290. Word Pattern</a>
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        String[] arr = s.split(" ");
        if (pattern.length() != arr.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = arr[i];

            if (map.containsKey(c)) {
                if (map.get(c).equals(word)) {
                    continue;
                } else {
                    return false;
                }
            }

            if (map.containsValue(word)) {
                return false;
            }
            map.put(c, word);
        }
        return true;
    }

    /**
     * <a href="https://leetcode.com/problems/ransom-note/?envType=study-plan-v2&envId=top-interview-150">383. Ransom Note</a>
     * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
     *
     * Each letter in magazine can only be used once in ransomNote.
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] arr = new int[26];
        for (char c : magazine.toCharArray()) {
            arr[c - 'a']++;
        }

        for (char c : ransomNote.toCharArray()) {
            arr[c - 'a']--;
            if (arr[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
