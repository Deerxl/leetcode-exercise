package org.example.leetcode;

import java.util.*;

/**
 * @author jialu.yxl
 * @date 6/27/24 5:53 PM
 */
public class DivideAndConquer {

    /**
     * <a href="https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/description/">395. Longest Substring with At Least K Repeating Characters</a>
     * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.
     * if no such substring exists, return 0.
     * Example 1:
     *
     * Input: s = "aaabb", k = 3
     * Output: 3
     * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
     * @param s
     * @param k
     * @return
     */
    public static int longestSubstring(String s, int k) {
        return longestSubstring(s, 0, s.length() - 1, k, 0);
    }

    private static int longestSubstring(String s, int start, int end, int k, int tmpResult) {
        if (start > end || k > end - start + 1) {
            return 0;
        }

        if (end - start + 1 <= tmpResult) {
            return tmpResult;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = start; i <= end; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        Set<Character> disableChars = new HashSet<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() < k) {
                disableChars.add(entry.getKey());
            }
        }

        if (disableChars.isEmpty()) {
            return end - start + 1;
        }

        if (disableChars.size() >= s.length()) {
            return 0;
        }

        int l = start, r = start;

        List<String> list = new ArrayList<>();
        while (r <= end) {
            if (disableChars.contains(s.charAt(r))) {
                if (r - l + 1 > Math.max(k, tmpResult)) {
                    list.add(s.substring(l, r));
                }
                r++;
                l = r;
            } else {
                r++;
            }
        }
        if (r - l + 1 > Math.max(k, tmpResult)) {
            list.add(s.substring(l, r));
        }

        int result = tmpResult;
        for (String str : list) {
            result = Math.max(result, longestSubstring(str, 0, str.length() - 1, k, result));
        }

        return result;
    }
}
