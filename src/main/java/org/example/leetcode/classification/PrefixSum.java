package org.example.leetcode.classification;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaolu
 * @date 2024/10/17 15:35
 */
public class PrefixSum {

    /**
     * <a href="https://leetcode.cn/problems/subarray-sum-equals-k/description/?envType=study-plan-v2&envId=top-100-liked">560. Subarray Sum Equals K</a>
     * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     * Example 1:
     *
     * Input: nums = [1,1,1], k = 2
     * Output: 2
     * @param nums 1 <= nums.length <= 2 * 104 -1000 <= nums[i] <= 1000
     * @param k -107 <= k <= 107
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        int result = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                result += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return result;
    }
}
