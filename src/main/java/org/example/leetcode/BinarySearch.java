package org.example.leetcode;

/**
 * @author jialu.yxl
 * @date 02/02/2023 14:51
 */
public class BinarySearch {

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
    public static int findMin(int[] nums) {
        if (nums.length == 1 || nums[nums.length - 1] > nums[0]) {
            return nums[0];
        }

        return findMin(nums, 0, nums.length - 1);
    }

    private static int findMin(int[] nums, int start, int end) {
        if (start < 0 || start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (mid > 0 && nums[mid] < nums[mid - 1]) {
            return nums[mid];
        }
        if (nums[mid] > nums[start]) {
            int result = findMin(nums, mid + 1, end);
            if (result != -1) {
                return result;
            }
            return findMin(nums, start, mid - 1);
        } else if (nums[mid] < nums[start]) {
            return findMin(nums, start, mid - 1);
        } else {
            return findMin(nums, mid + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {4,5,6,7,0,1,2};
        System.out.println(findMin(nums));
    }
}
