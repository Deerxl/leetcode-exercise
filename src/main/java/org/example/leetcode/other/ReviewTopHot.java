package org.example.leetcode.other;

import java.util.Arrays;
import java.util.Random;

/**
 * @author jialu.yxl
 * @date 8/10/24 4:09 PM
 */
public class ReviewTopHot {


    public static void main(String[] args) {
        mergeSort(new int[] {5,3,5,3,1,2,3,5,7,8,9});
    }

    /**
     * 快排
     * 时间复杂度：
     * 最好情况：O(n log n)
     * 平均情况：O(n log n)
     * 最坏情况：O(n²)（通常发生在选择的基准值极端不平衡时）
     * 空间复杂度：O(log n)（栈空间，递归的深度）
     * <a href="https://leetcode.cn/problems/sort-an-array/solutions/179489/fu-xi-ji-chu-pai-xu-suan-fa-java-by-liweiwei1419/">复习基础排序算法（Java）</a>
     * @param nums
     */
    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    static Random random = new Random();
    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int k = start + random.nextInt(end - start + 1);
        int pivot = nums[k];
        nums[k] = nums[start];

        int i = start, j = end;
        while (i < j) {
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            nums[i] = nums[j];
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            nums[j] = nums[i];
        }
        nums[i] = pivot;

        quickSort(nums, start, i - 1);
        quickSort(nums, i + 1, end);
    }

    /**
     * 归并排序
     * 时间复杂度：
     * 最好情况：O(n log n)
     * 平均情况：O(n log n)
     * 最坏情况：O(n log n)
     * 空间复杂度：O(n)（需要额外的数组存储临时结果）
     * @param nums
     */
    public static void mergeSort(int[] nums) {
        int[] mergeSortTmpArr = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, mergeSortTmpArr);
        System.out.println(Arrays.toString(nums));
    }

    private static void mergeSort(int[] nums, int start, int end, int[] mergeSortTmpArr) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid, mergeSortTmpArr);
        mergeSort(nums, mid + 1, end, mergeSortTmpArr);
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }

        mergeSort(nums, start, end, mid, mergeSortTmpArr);
    }

    private static void mergeSort(int[] nums, int start, int end, int mid, int[] mergeSortTmpArr) {
        int index = start;
        int i = start, j = mid + 1;
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) {
                mergeSortTmpArr[index++] = nums[i++];
            } else {
                mergeSortTmpArr[index++] = nums[j++];
            }
        }
        while (i <= mid) {
            mergeSortTmpArr[index++] = nums[i++];
        }
        while (j <= end) {
            mergeSortTmpArr[index++] = nums[j++];
        }
        System.arraycopy(mergeSortTmpArr, start, nums, start, end - start + 1);
    }
}
