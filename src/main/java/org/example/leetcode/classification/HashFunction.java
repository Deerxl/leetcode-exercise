package org.example.leetcode.classification;

import java.util.*;

/**
 * @author xiaolu
 * @date 2024/10/7 11:15
 */
public class HashFunction {


    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[] {100,4,200,1,3,2}));
    }


    /**
     * <a href="https://leetcode.cn/problems/minimum-window-substring/?envType=study-plan-v2&envId=top-100-liked">76. Minimum Window Substring</a>
     * Input: s = "ADOBECODEBANC", t = "ABC"
     * Output: "BANC"
     * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
     * @param s m == s.length n == t.length
     * @param t 1 <= m, n <= 105 s and t consist of uppercase and lowercase English letters.
     * @return
     */
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }

        if (s.contains(t)) {
            return t;
        }

        Map<Character, Integer> tMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        if (checkSMapContainTMap(sMap, tMap)) {
            return s.substring(0, t.length());
        }

        int l = 0;
        int resultLen = Integer.MAX_VALUE;
        String result = "";
        for (int i = t.length(); i < s.length(); i++) {
            char c = s.charAt(i);
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);

            if (!tMap.containsKey(c)) {
                continue;
            }

            while (checkSMapContainTMap(sMap, tMap)) {
                if (i - l + 1 < resultLen) {
                    resultLen = i - l + 1;
                    result = s.substring(l, i + 1);
                }

                sMap.put(s.charAt(l), sMap.get(s.charAt(l)) - 1);
                l++;
            }
        }

        return result;
    }

    private boolean checkSMapContainTMap(Map<Character, Integer> sMap, Map<Character, Integer> tMap) {
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            if (sMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }

        return true;
    }


    /**
     * <a href="https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-100-liked">128. Longest Consecutive Sequence</a>
     * @param nums 0 <= nums.length <= 10^5
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }


        int result = 1;
        int tmpResult = 0;
        for (int num : set) {
            // 没有比num更小的值，就说明连续序列从num开始
            if (!set.contains(num - 1)) {
                int curNum = num;
                while (set.contains(curNum)) {
                    tmpResult++;
                    curNum++;
                }
                result = Math.max(result, tmpResult);
                tmpResult = 0;
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-100-liked">49. Group Anagrams</a>
     * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
     * Example 1:
     *
     * Input: strs = ["eat","tea","tan","ate","nat","bat"]
     *
     * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * @param strs strs[i] consists of lowercase English letters.
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = Arrays.toString(charArray);
            if (map.containsKey(key)) {
                map.getOrDefault(key, new ArrayList<>()).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            }
        }

        return new ArrayList<>(map.values());
    }

    /**
     * <a href="https://leetcode.cn/problems/repeated-dna-sequences/">187. Repeated DNA Sequences</a>
     * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
     * For example, "ACGAATTCCG" is a DNA sequence.
     * When studying DNA, it is useful to identify repeated sequences within the DNA.
     * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.
     * Example 1:
     * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
     * Output: ["AAAAACCCCC","CCCCCAAAAA"]
     * @param s 1 <= s.length <= 10^5
     * @return
     */
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();

        int P = 131313;
        int n = s.length();

        int[] h = new int[n + 1];
        int[] pow = new int[n + 1];

        pow[0] = 1;

        // hash 计算公式：abc: s[0] * p^(n-1) + s[1] * p^(n-2) + ... + s[n-1]
        for (int i = 1; i <= n; i++) {
            h[i] = h[i - 1] * P + s.charAt(i - 1);
            pow[i] = pow[i - 1] * P;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n - 10 + 1; i++) {
            int j = i + 10 - 1;
            int hash = h[j] - h[i - 1] * pow[j - i + 1];
            int count = map.getOrDefault(hash, 0);
            if (count == 1) {
                result.add(s.substring(i - 1, i + 10 - 1));
            }
            map.put(hash, count + 1);
        }

        return result;
    }




    /**
     * <a href="https://leetcode.cn/problems/longest-duplicate-substring/">1044. Longest Duplicate Substring</a>
     * Given a string s, consider all duplicated substrings: (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.
     *
     * Return any duplicated substring that has the longest possible length. If s does not have a duplicated substring, the answer is "".
     * Example 1:
     * Input: s = "banana"
     * Output: "ana"
     * @param s 2 <= s.length <= 3 * 104
     * @return
     */
    public String longestDupSubstring(String s) {
        int P = 131313, n = s.length();
        long[] h = new long[n + 1];
        long[] p = new long[n + 1];
        p[0] = 1;
        for (int i = 0; i < n; i++) {
            p[i + 1] = p[i] * P;
            h[i + 1] = h[i] * P + s.charAt(i);
        }

        String result = "";

        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            String t = checkDupSubstring(s, mid, n, h, p);
            if (!t.isEmpty()) {
                l = mid;
            } else {
                r = mid - 1;
            }
            result = t.length() > result.length() ? t : result;
        }

        return result;
    }

    private String checkDupSubstring(String s, int len, int n, long[] h, long[] p) {
        Set<Long> set = new HashSet<>();
        for (int i = 1; i <= n - len + 1; i++) {
            int j = i + len - 1;
            long cur = h[j] - h[i - 1] * p[j - i + 1];
            if (set.contains(cur)) {
                return s.substring(i - 1, j);
            }
            set.add(cur);
        }
        return "";
    }


}
