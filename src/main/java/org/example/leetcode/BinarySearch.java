package org.example.leetcode;

/**
 * @author jialu.yxl
 * @date 02/02/2023 14:51
 */
public class BinarySearch {

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
