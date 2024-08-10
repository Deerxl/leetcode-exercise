package org.example.leetcode.design;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/shuffle-an-array/">384. 打乱数组</a>
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。打乱后，数组的所有排列应该是 等可能 的。
 *
 * 实现 Solution class:
 *
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 * @author jialu.yxl
 * @date 14/03/2023 17:44
//  */
public class Solution {

    private int[] origin;
    private int[] nums;
    Random random;


    public Solution(int[] nums) {
        this.nums = new int[nums.length];
        this.origin = nums;
        random = new Random();
    }

    public int[] reset() {
        return origin;
    }

    public int[] shuffle() {
        int len = origin.length;
        List<Integer> list = new ArrayList<>(len);
        for (int j : origin) {
            list.add(j);
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            int index = random.nextInt(list.size());
            nums[i] = list.remove(index);
        }

        return nums;
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{1,2,3});
        System.out.println(Arrays.toString(solution.shuffle()));
        System.out.println(Arrays.toString(solution.reset()));
        System.out.println(Arrays.toString(solution.shuffle()));

    }
}
