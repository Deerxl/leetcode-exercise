package org.example.leetcode;

import java.util.*;

/**
 * @author jialu.yxl
 * @date 10/02/2023 11:34
 */
public class SortArray {

    public static void main(String[] args) {
        System.out.println(reversePairs(new int[] {9,7,5,4,6}));
        System.out.println(Arrays.toString(quickSortArray(new int[]{5,4,4,2,1,32,3,52,1,32,4,4,1,1,3,2,1})));
    }

    /**
     * <a href="https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/">LCR 170. 交易逆序对的总数</a>
     * 在股票交易中，如果前一天的股价高于后一天的股价，则可以认为存在一个「交易逆序对」。
     * 请设计一个程序，输入一段时间内的股票交易记录 record，返回其中存在的「交易逆序对」总数。
     * 示例 1:
     * 输入：record = [9, 7, 5, 4, 6]
     * 输出：8
     * 解释：交易中的逆序对为 (9, 7), (9, 5), (9, 4), (9, 6), (7, 5), (7, 4), (7, 6), (5, 4)。
     * @param record
     * @return
     */
    static int reversePairsResult = 0;
    public static int reversePairs(int[] record) {
        if (record.length <= 1) {
            return 0;
        }

        int[] tmpArr = new int[record.length];
        reversePairsMergeSort(record, 0, record.length - 1, tmpArr);

        return reversePairsResult;
    }

    private static void reversePairsMergeSort(int[] record, int start, int end, int[] tmpArr) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        reversePairsMergeSort(record, start, mid, tmpArr);
        reversePairsMergeSort(record, mid + 1, end, tmpArr);

        if (record[mid] <= record[mid + 1]) {
            return;
        }

        reversePairsMergeSortMerge(record, start, mid, mid + 1, end, tmpArr);
    }

    private static void reversePairsMergeSortMerge(int[] record, int start1, int end1, int start2, int end2, int[] tmpArr) {
        int i = start1, j = start2;
        int index = i;
        while (i <= end1 || j <= end2) {
            if (i <= end1 && j <= end2) {
                if (record[i] <= record[j]) {
                    tmpArr[index++] = record[i++];
                } else {
                    reversePairsResult += end1 - i + 1;
                    tmpArr[index++] = record[j++];
                }
            } else if (i <= end1) {
                tmpArr[index++] = record[i++];
            } else {
                tmpArr[index++] = record[j++];
            }
        }
        System.arraycopy(tmpArr, start1, record, start1, end2 - start1 + 1);
    }

    /**
     * <a href="https://leetcode.cn/problems/sort-an-array/description/">912. 排序数组</a>
     * 快排
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        quickSortArray(nums);
        return nums;
    }

    /**
     * 基本思路：快速排序每一次都排定一个元素（这个元素呆在了它最终应该呆的位置），然后递归地去排它左边的部分和右边的部分，依次进行下去，直到数组有序；
     * 一定要随机化选择切分元素（pivot）
     * @param nums
     * @return
     */
    public static int[] quickSortArray(int[] nums) {
        quickSortArray(nums, 0, nums.length - 1);
        return nums;
    }

    private static void quickSortArray(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        int partition = quickSortArrayPartition(nums, start, end);
        quickSortArray(nums, start, partition - 1);
        quickSortArray(nums, partition + 1, end);
    }

    private static int quickSortArrayPartition(int[] nums, int start, int end) {
        Random random = new Random();
        int index = random.nextInt(end - start + 1) + start;
        int pivot = nums[index];
        swap(nums, start, index);

        int lt = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] < pivot) {
                lt++;
                swap(nums, i, lt);
            }
        }
        swap(nums, start, lt);

        return lt;
    }

    /**
     * 归并排序（重点）
     * 基本思路：借助额外空间，合并两个有序数组，得到更长的有序数组。
     * @param nums
     * @return
     */
    public static int[] mergeSortArray(int[] nums) {
        int[] tempArr = new int[nums.length];
        mergeSortArray(nums, 0, nums.length - 1, tempArr);
        return nums;
    }

    private static void mergeSortArray(int[] nums, int start, int end, int[] tempArr) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSortArray(nums, start, mid, tempArr);
        mergeSortArray(nums, mid + 1, end, tempArr);

        if (nums[mid] <= nums[mid + 1]) {
            return;
        }

        mergeSortArray(nums, start, mid, end, tempArr);
    }

    private static void mergeSortArray(int[] nums, int start, int mid, int end, int[] tempArr) {
        int m = start;
        int n = mid + 1;
        for (int i = start; i <= end; i++) {
            if (m <= mid && n <= end) {
                if (nums[m] <= nums[n]) {
                    tempArr[i] = nums[m];
                    m++;
                } else {
                    tempArr[i] = nums[n];
                    n++;
                }
            } else if (m <= mid) {
                tempArr[i] = nums[m];
                m++;
            } else if (n <= end) {
                tempArr[i] = nums[n];
                n++;
            }
        }
        System.arraycopy(tempArr, start, nums, start, end - start + 1);
    }

    /**
     * 选择排序
     * 每一轮选取未排定的部分中最小的部分交换到未排定部分的最开头，经过若干个步骤，就能排定整个数组。即：先选出最小的，再选出第 2 小的，以此类推。
     * @param nums
     * @return
     */
    public static int[] selectionSortArray(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            swap(nums, min, i);
        }
        return nums;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 插入排序
     * 每次将一个数字插入一个有序的数组里，成为一个长度更长的有序数组，有限次操作以后，数组整体有序。
     * @param nums
     * @return
     */
    public static int[] insertionSortArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            int index = i - 1;
            while (index >= 0) {
                if (nums[index] > temp) {
                    nums[index + 1] = nums[index];
                } else {
                    break;
                }
                index--;
            }
            nums[index + 1] = temp;
        }

        return nums;
    }

    /**
     * <a href="https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/">剑指 Offer 21. 调整数组顺序使奇数位于偶数前面</a>
     * @param nums
     * @return
     */
    public static int[] exchange(int[] nums) {
        int exchangeIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                if (i != exchangeIndex) {
                    int temp = nums[i];
                    nums[i] = nums[exchangeIndex];
                    nums[exchangeIndex] = temp;
                    exchangeIndex++;
                } else {
                    exchangeIndex++;
                }
            }
        }
        return nums;
    }

    /**
     * <a href="https://leetcode.cn/problems/sort-colors/">75. 颜色分类</a>
     * @param nums 输入：nums = [2,0,2,1,1,0]
     * 输出：[0,0,1,1,2,2]
     */
    public static void sortColors(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    private static void mergeSortArray(int[] nums, int start, int mid, int end) {
        int i = start, j = mid + 1;
        int[] tempNums = new int[end - start + 1];
        int index = 0;
        while (i <= mid || j <= end) {
            if (i <= mid && j <= end) {
                if (nums[i] <= nums[j]) {
                    tempNums[index++] = nums[i];
                    i++;
                } else {
                    tempNums[index++] = nums[j];
                    j++;
                }
            } else if (i <= mid) {
                tempNums[index++] = nums[i];
                i++;
            } else if (j <= end) {
                tempNums[index++] = nums[j];
                j++;
            }
        }
        System.arraycopy(tempNums, 0, nums, start, tempNums.length);
    }


}
