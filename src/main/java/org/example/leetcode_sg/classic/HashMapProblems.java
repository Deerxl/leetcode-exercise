package org.example.leetcode_sg.classic;

public class HashMapProblems {

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
