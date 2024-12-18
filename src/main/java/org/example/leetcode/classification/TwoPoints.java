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
     * <a href="https://leetcode.cn/problems/trapping-rain-water/?envType=study-plan-v2&envId=top-100-liked">42. Trapping Rain Water</a>
     * @param height n == height.length 1 <= n <= 2 * 104 0 <= height[i] <= 105
     * @return
     */
    public int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }

        int[] left = new int[height.length];
        int[] right = new int[height.length];

        left[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        right[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        int result = 0;
        for (int i = 1; i < height.length - 1; i++) {
            result += Math.min(left[i], right[i]) - height[i];
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/palindromic-substrings/description/">647. Palindromic Substrings</a>
     * Given a string s, return the number of palindromic substrings in it.
     * A string is a palindrome when it reads the same backward as forward.
     * A substring is a contiguous sequence of characters within the string.
     * Example 2:
     * Input: s = "aaa"
     * Output: 6
     * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
     * @param s 1 <= s.length <= 1000
     * @return
     */
    public static int countSubstrings(String s) {
        int len = s.length();
        int result = 0;
        for (int i = 0; i < len * 2 - 1; i++) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < len && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
                result++;
            }
        }
        return result;
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
