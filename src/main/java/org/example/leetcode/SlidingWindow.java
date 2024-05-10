package org.example.leetcode;

/**
 * @author jialu.yxl
 * @date 5/10/24 2:14 PM
 */
public class SlidingWindow {

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
