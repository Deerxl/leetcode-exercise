package org.example.leetcode.classification;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jialu.yxl
 * @date 8/10/24 3:09 PM
 */
public class TwoPoints {


    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("eceba"));
    }

    /**
     * <a href="https://www.lintcode.com/problem/928/">928 · 最多有两个不同字符的最长子串</a>
     * <a href="https://leetcode.cn/problems/longest-substring-with-at-most-two-distinct-characters/description/">159. 至多包含两个不同字符的最长子串</a>
     * 给定一个字符串，找出最长子串T的长度，它最多包含2个不同的字符。
     * 输入: “eceba”
     * 输出: 3
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() < 3) {
            return s.length();
        }

        Map<Character, Integer> map = new HashMap<>();
        int i = 0, j = 0;
        int result = 0;
        while (j < s.length()) {
            char c = s.charAt(j);
            map.put(c, j);
            j++;

            if (map.size() > 2) {
                int delIndex = Collections.min(map.values());
                map.remove(s.charAt(delIndex));
                i = delIndex + 1;
            }
            result = Math.max(result, j - i);
        }

        result = Math.max(result, j - i);


        return result;
    }

    /**
     * <a href="https://www.lintcode.com/problem/386/?showListFe=true&page=8&ordering=id&pageSize=50">386 · 最多有k个不同字符的最长子字符串</a>
     * <a href="https://leetcode.cn/problems/longest-substring-with-at-most-k-distinct-characters/description/">340. 至多包含 K 个不同字符的最长子字符串</a>
     * 给定字符串S，找到最多有k个不同字符的最长子串T。
     * 输入: S = "eceba" 并且 k = 3
     * 输出: 4
     * 解释: T = "eceb"
     * @param s
     * @param k
     * @return
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.isEmpty() || k <= 0) {
            return 0;
        }
        if (k >= s.length()) {
            return s.length();
        }

        int result = 0;
        Map<Character, Integer> map = new HashMap<>();

        int i = 0, j = 0;
        while (j < s.length()) {
            char c = s.charAt(j);
            map.put(c, j);
            j++;
            if (map.size() > k) {
                int delIndex = Collections.min(map.values());
                map.remove(s.charAt(delIndex));
                i = delIndex + 1;
            }

            result = Math.max(result, j - i);
        }

        return result;
    }



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
