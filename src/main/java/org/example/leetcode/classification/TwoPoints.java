package org.example.leetcode.classification;

/**
 * @author jialu.yxl
 * @date 8/10/24 3:09 PM
 */
public class TwoPoints {


    /**
     * <a href="https://leetcode.cn/problems/valid-palindrome-ii/">680. Valid Palindrome II</a>
     * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
     * Input: s = "abca"
     * Output: true
     * Explanation: You could delete the character 'c'.
     * @param s 1 <= s.length <= 10^5
     * @return
     */
    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return validPalindrome(s, i + 1, j) || validPalindrome(s, i, j - 1);
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    private boolean validPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }
}
