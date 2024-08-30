package org.example.leetcode.classification;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jialu.yxl
 * @date 5/10/24 2:14 PM
 */
public class SlidingWindow {


    public static void main(String[] args) {
        System.out.println(maxSumTwoNoOverlap(new int[] {1,0,3}, 1, 2));
    }

    /**
     * <a href="https://leetcode.cn/problems/maximum-sum-of-two-non-overlapping-subarrays/">1031. Maximum Sum of Two Non-Overlapping Subarrays</a>
     * Given an integer array nums and two integers firstLen and secondLen, return the maximum sum of elements in two non-overlapping subarrays with lengths firstLen and secondLen.
     * The array with length firstLen could occur before or after the array with length secondLen, but they have to be non-overlapping.
     * A subarray is a contiguous part of an array.
     * Example 1:
     * Input: nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
     * Output: 20
     * Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
     * @param nums 0 <= nums[i] <= 1000
     * @param firstLen 1 <= firstLen, secondLen <= 1000
     * 2 <= firstLen + secondLen <= 1000
     * firstLen + secondLen <= nums.length <= 1000
     * @param secondLen
     * @return
     */
    public static int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        // 采用滚动数组（dp）+ 滑动窗口实现
        return Math.max(maxSumTwoNoOverlapFunc(nums, firstLen, secondLen),
                maxSumTwoNoOverlapFunc(nums, secondLen, firstLen));
    }

    private static int maxSumTwoNoOverlapFunc(int[] nums, int firstLen, int secondLen) {
        int lSum = 0, rSum = 0;
        for (int i = 0; i < firstLen; i++) {
            lSum += nums[i];
        }
        for (int i = firstLen; i < secondLen + firstLen; i++) {
            rSum += nums[i];
        }

        int maxLSum = lSum;
        int result = maxLSum + rSum;
        for (int i = firstLen + secondLen, j = firstLen; i < nums.length; i++, j++) {
            lSum += nums[j] - nums[j - firstLen];
            maxLSum = Math.max(maxLSum, lSum);
            rSum += nums[i] - nums[i - secondLen];
            result = Math.max(result, maxLSum + rSum);
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/">LCR 167. 招式拆解 I</a>
     * 某套连招动作记作序列 arr，其中 arr[i] 为第 i 个招式的名字。请返回 arr 中最多可以出连续不重复的多少个招式。
     * 示例 1:
     * 输入: arr = "dbascDdad"
     * 输出: 6
     * 解释: 因为连续且最长的招式序列是 "dbascD" 或 "bascDd"，所以其长度为 6。
     * @param arr
     * @return
     */
    public int dismantlingAction(String arr) {
        int result = 0;

        int l = 0, r = 0;
        Set<Character> set = new HashSet<>();
        while (r < arr.length()) {
            if (set.contains(arr.charAt(r))) {
                result = Math.max(result, r - l);
                while (arr.charAt(l) != arr.charAt(r)) {
                    set.remove(arr.charAt(l));
                    l++;
                }
                set.remove(arr.charAt(l));
                l++;
            } else {
                set.add(arr.charAt(r));
                r++;
            }
        }
        result = Math.max(result, r - l);

        return result;
    }

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
