package org.example.leetcode_sg;

import java.util.*;

public class SlidingWindows {


    /**
     * <a href="https://leetcode.com/problems/substring-with-concatenation-of-all-words/?envType=study-plan-v2&envId=top-interview-150">30. Substring with Concatenation of All Words</a>
     * You are given a string s and an array of strings words. All the strings of words are of the same length.
     *
     * A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
     *
     * For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
     * Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.
     * @param s
     *  1 <= s.length <= 104
     * 1 <= words.length <= 5000
     * 1 <= words[i].length <= 30
     * s and words[i] consist of lowercase English letters.
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> expectedMap = new HashMap<>();

        int wordsLen = 0;
        int singleWordLen = words[0].length();
        for (String word : words) {
            expectedMap.put(word, expectedMap.getOrDefault(word, 0) + 1);
            wordsLen += word.length();
        }
        if (wordsLen > s.length()) {
            return result;
        }
        Map<String, Integer> curMap = new HashMap<>();

        for (int offsite = 0; offsite < singleWordLen; offsite++) {
            int left = offsite, right = left;
            curMap.clear();
            while (right + singleWordLen <= s.length()) {
                String curWord = s.substring(right, right + singleWordLen);
                if (!expectedMap.containsKey(curWord)) {
                    curMap.clear();
                    left += singleWordLen;
                    right = left;
                    continue;
                }
                int curWordCount = curMap.getOrDefault(curWord, 0) + 1;
                curMap.put(curWord, curWordCount);
                right += singleWordLen;
                if (curWordCount == expectedMap.get(curWord) && right - left == wordsLen) {
                    result.add(left);
                    String firstWord = s.substring(left, left + singleWordLen);
                    curMap.put(firstWord, curMap.get(firstWord) - 1);
                    left += singleWordLen;
                } else if (curWordCount > expectedMap.get(curWord)) {
                    while (curMap.get(curWord) > expectedMap.get(curWord)) {
                        String firstWord = s.substring(left, left + singleWordLen);
                        curMap.put(firstWord, curMap.get(firstWord) - 1);
                        left += singleWordLen;
                    }
                }
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/description/?envType=study-plan-v2&envId=top-interview-150">3. Longest Substring Without Repeating Characters</a>
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }

        int result = 1;
        Set<Character> set = new HashSet<>();
        int i = 0, j = 1;
        set.add(s.charAt(i));
        while (i < s.length() && j < s.length()) {
            if (set.contains(s.charAt(j))) {
                result = Math.max(result, j - i);

                do {
                    set.remove(s.charAt(i));
                    if (s.charAt(i) == s.charAt(j)) {
                        i++;
                        break;
                    }
                    i++;
                } while (true);

            }
            set.add(s.charAt(j));
            j++;
        }
        result = Math.max(result, j - i);

        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/minimum-size-subarray-sum/?envType=study-plan-v2&envId=top-interview-150">209. Minimum Size Subarray Sum</a>
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int i = 0, j = 0, curSum = nums[i];
        while (i < nums.length && j < nums.length) {
            if (curSum >= target) {
                result = Math.min(result, j - i + 1);
                curSum -= nums[i];
                i++;
            } else {
                j++;
                if (j < nums.length) {
                    curSum += nums[j];
                }
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
