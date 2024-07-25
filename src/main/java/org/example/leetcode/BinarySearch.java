package org.example.leetcode;

/**
 * @author jialu.yxl
 * @date 02/02/2023 14:51
 */
public class BinarySearch {


    public static void main(String[] args) {
        System.out.println(splitArray(new int[] {10,5,13,4,8,4,5,11,14,9,16,10,20,8}, 8));
    }

    /**
     * <a href="https://leetcode.cn/problems/split-array-largest-sum/description/">410. Split Array Largest Sum</a>
     * Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
     * Return the minimized largest sum of the split.
     * A subarray is a contiguous part of the array.
     * Example 1:
     * Input: nums = [7,2,5,10,8], k = 2
     * Output: 18
     * Explanation: There are four ways to split nums into two subarrays.
     * The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
     * Example 2:
     * Input: nums = [1,2,3,4,5], k = 2
     * Output: 9
     * Explanation: There are four ways to split nums into two subarrays.
     * The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
     * @param nums 1 <= nums.length <= 1000 0 <= nums[i] <= 10^6
     * @param k 1 <= k <= min(50, nums.length)
     * @return
     */
    public static int splitArray(int[] nums, int k) {
        int sum = 0;
        int maxNum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            maxNum = Math.max(maxNum, nums[i]);
        }
        if (k == 1 || nums.length <= 1) {
            return sum;
        }

        int left = maxNum;
        int right = sum;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int splitCount = splitArrayCount(nums, mid);
            if (splitCount > k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static int splitArrayCount(int[] nums, int maxSum) {
        int splitCount = 1;

        int tmpSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (tmpSum + nums[i] > maxSum) {
                splitCount++;
                tmpSum = 0;
            }
            tmpSum += nums[i];
        }

        return splitCount;
    }

    /**
     * <a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/">154. Find Minimum in Rotated Sorted Array II</a>
     * Example 1:
     *
     * Input: nums = [1,3,5]
     * Output: 1
     * Example 2:
     *
     * Input: nums = [2,2,2,0,1]
     * Output: 0
     * @param nums n == nums.length
     * 1 <= n <= 5000
     * -5000 <= nums[i] <= 5000
     * nums is sorted and rotated between 1 and n times.
     * @return
     */
    int findMinResult = Integer.MAX_VALUE;
    public int findMin(int[] nums) {
        findMin(nums, 0, nums.length - 1);
        return findMinResult;
    }

    private void findMin(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        if (start == end) {
            findMinResult = nums[start];
        }
        int mid = (start + end) / 2;
        if (nums[mid] > nums[end]) {
            findMin(nums, mid + 1, end);
        } else if (nums[mid] < nums[end]) {
            findMin(nums, start, mid);
        } else {
            findMin(nums, start, end - 1);
        }
    }


    /**
     * <a href="https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/">LCR 172. 统计目标成绩的出现次数</a>
     * 某班级考试成绩按非严格递增顺序记录于整数数组 scores，请返回目标成绩 target 的出现次数。
     * 示例 1：
     * 输入: scores = [2, 2, 3, 4, 4, 4, 5, 6, 6, 8], target = 4
     * 输出: 3
     * 示例 2：
     * 输入: scores = [1, 2, 3, 5, 7, 9], target = 6
     * 输出: 0
     * @param scores 0 <= scores.length <= 10^5
     * @param target
     * @return
     */
    int countTargetResult = 0;
    public int countTarget(int[] scores, int target) {
        countTarget(scores, 0, scores.length - 1, target);
        return countTargetResult;
    }

    private void countTarget(int[] scores, int start, int end, int target) {
        if (start > end) {
            return;
        }

        int mid = start + (end - start) / 2;
        if (scores[mid] < target) {
            countTarget(scores, mid + 1, end, target);
        } else if (scores[mid] > target) {
            countTarget(scores, start, mid - 1, target);
        } else {
            countTargetResult++;
            countTarget(scores, start, mid - 1, target);
            countTarget(scores, mid + 1, end, target);
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/">153. 寻找旋转排序数组中的最小值</a>
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     * @param nums
     * @return
     */
    public int findMin153(int[] nums) {
        return findMin153(nums, 0, nums.length - 1);
    }

    private int findMin153(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }

        int mid = start + (end - start) / 2;
        if (nums[mid] >= nums[start] && nums[mid] <= nums[end]) {
            return findMin153(nums, start, mid);
        }
        if (nums[mid] >= nums[start] && nums[mid] >= nums[end]) {
            return findMin153(nums, mid + 1, end);
        }
        if (nums[mid] <= nums[start] && nums[mid] <= nums[end]) {
            return findMin153(nums, start, mid);
        }

        return -1;
    }

    /**
     * <a href="https://leetcode.cn/problems/find-peak-element/">162. 寻找峰值</a>
     * 峰值元素是指其值严格大于左右相邻值的元素。
     * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     * 你可以假设 nums[-1] = nums[n] = -∞ 。
     * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     * 示例 1：
     * 输入：nums = [1,2,3,1]
     * 输出：2
     * 解释：3 是峰值元素，你的函数应该返回其索引 2。
     * -231 <= nums[i] <= 231 - 1
     * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
     * @param nums
     * @return
     */
    int findPeakElementResult = -1;
    public int findPeakElement(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        findPeakElement(nums, 0, nums.length - 1);
        return findPeakElementResult;
    }

    private void findPeakElement(int[] nums, int start, int end) {
        if (start > end || findPeakElementResult != -1) {
            return;
        }

        int mid = start + (end - start) / 2;
        if ((mid == 0 || nums[mid] > nums[mid - 1])
                && (mid == nums.length - 1 || nums[mid] > nums[mid + 1])) {
            findPeakElementResult = mid;
            return;
        } else if ((mid == 0 || nums[mid] > nums[mid - 1])
                && (mid < nums.length - 1 && nums[mid] < nums[mid + 1])) {
            findPeakElement(nums, mid + 1, end);
        } else if ((mid > 0 && nums[mid] < nums[mid - 1])
                && (mid == nums.length - 1 || nums[mid] > nums[mid + 1])) {
            findPeakElement(nums, start, mid - 1);
        } else {
            findPeakElement(nums, start, mid - 1);
            findPeakElement(nums, mid + 1, end);
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/">153. 寻找旋转排序数组中的最小值</a>
     * @param nums
     * @return
     */
    public int findMin1(int[] nums) {
        if (nums.length == 1 || nums[nums.length - 1] > nums[0]) {
            return nums[0];
        }

        return findMin1(nums, 0, nums.length - 1);
    }

    private int findMin1(int[] nums, int start, int end) {
        if (start < 0 || start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (mid > 0 && nums[mid] < nums[mid - 1]) {
            return nums[mid];
        }
        if (nums[mid] > nums[start]) {
            int result = findMin1(nums, mid + 1, end);
            if (result != -1) {
                return result;
            }
            return findMin1(nums, start, mid - 1);
        } else if (nums[mid] < nums[start]) {
            return findMin1(nums, start, mid - 1);
        } else {
            return findMin1(nums, mid + 1, end);
        }
    }
}
