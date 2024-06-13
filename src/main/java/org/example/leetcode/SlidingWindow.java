package org.example.leetcode;

/**
 * @author jialu.yxl
 * @date 5/10/24 2:14 PM
 */
public class SlidingWindow {

    /**
     * <a href="https://leetcode.cn/problems/max-consecutive-ones-iii/">1004. Max Consecutive Ones III</a>
     * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
     * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
     * Output: 6
     * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
     * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
     * @param nums 1 <= nums.length <= 105
     * @param k 0 <= k <= nums.length
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int result = 0;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                if (k == 0) {
                    result = Math.max(result, right - left);
                    while (left < right && nums[left] == 1) {
                        left++;
                    }
                    left++;
                } else {
                    k--;
                }
            }
            right++;
        }
        result = Math.max(result, right - left);

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/minimum-size-subarray-sum/">209. 长度最小的子数组</a>
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
     * 如果不存在符合条件的子数组，返回 0 。
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;

        int startIndex = 0;
        int tmpSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return 1;
            }
            tmpSum += nums[i];
            if (tmpSum >= target) {
                while (tmpSum - nums[startIndex] >= target) {
                    tmpSum -= nums[startIndex];
                    startIndex++;
                }
                result = Math.min(result, i - startIndex + 1);
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
