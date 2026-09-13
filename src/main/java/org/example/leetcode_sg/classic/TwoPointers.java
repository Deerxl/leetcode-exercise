package org.example.leetcode_sg.classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointers {


    /**
     * <a href="https://leetcode.com/problems/3sum/?envType=study-plan-v2&envId=top-interview-150">15. 3Sum</a>
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }


            int target = -nums[i];
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    j++;
                    continue;
                }
                if (nums[j] + nums[k] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    result.add(list);
                    j++;
                    k--;
                } else if (nums[j] + nums[k] > target) {
                    k--;
                } else {
                    j++;
                }

            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/container-with-most-water/?envType=study-plan-v2&envId=top-interview-150">11. Container With Most Water</a>
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int result = 0;
        while (i < j) {
            result = Math.max(result, Math.min(height[i], height[j]) * (j - i));
            if (height[j] > height[i]) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/?envType=study-plan-v2&envId=top-interview-150">167. Two Sum II - Input Array Is Sorted</a>
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        int[] result = new int[2];
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                result[0] = i + 1;
                result[1] = j + 1;
                break;
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/is-subsequence/?envType=study-plan-v2&envId=top-interview-150">392. Is Subsequence</a>
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        if (s == t) {
            return true;
        }
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            char cI = s.charAt(i);
            char cJ = t.charAt(j);
            if (cI == cJ) {
                i++;
                j++;
                continue;
            }
            j++;
        }
        return i == s.length();
    }

    /**
     * <a href="https://leetcode.com/problems/valid-palindrome/description/?envType=study-plan-v2&envId=top-interview-150">125. Valid Palindrome</a>
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            char cI = s.charAt(i);
            if (isNumberOrWord(cI)) {
                i++;
                continue;
            }

            char cJ = s.charAt(j);
            if (isNumberOrWord(cJ)) {
                j--;
                continue;
            }

            if (cI != cJ) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    boolean isNumberOrWord(char c) {
        return (c < '0' || c > '9') && (c < 'a' || c > 'z');
    }
}
