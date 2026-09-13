package org.example.leetcode_sg;

import java.util.HashSet;
import java.util.Set;

public class SlidingWindows {

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
