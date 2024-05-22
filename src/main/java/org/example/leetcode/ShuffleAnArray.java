package org.example.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * <a href="https://leetcode.cn/problems/shuffle-an-array/">384. Shuffle an Array</a>
 * Given an integer array nums, design an algorithm to randomly shuffle the array.
 * All permutations of the array should be equally likely as a result of the shuffling.
 * Implement the Solution class:
 * Solution(int[] nums) Initializes the object with the integer array nums.
 * int[] reset() Resets the array to its original configuration and returns it.
 * int[] shuffle() Returns a random shuffling of the array.
 * @author jialu.yxl
 * @date 5/22/24 7:37 PM
 */
public class ShuffleAnArray {

    int[] nums;
    int[] shuffles;
    Random random;

    public ShuffleAnArray(int[] nums) {
        this.nums = nums;
        shuffles = new int[nums.length];
        System.arraycopy(nums, 0, shuffles, 0, nums.length);
        random = new Random();
    }

    public int[] reset() {
        System.arraycopy(nums, 0, shuffles, 0, nums.length);
        return nums;
    }

    public int[] shuffle() {
        for (int i = 0; i < shuffles.length; i++) {
            int index = random.nextInt(i, shuffles.length);
            int tmp = shuffles[i];
            shuffles[i] = shuffles[index];
            shuffles[index] = tmp;
        }
        return shuffles;
    }
}
