package org.example.leetcode_sg.classic;

import java.util.ArrayList;
import java.util.List;

public class IntervalsProblems {

    /**
     * <a href="https://leetcode.com/problems/summary-ranges/description/?envType=study-plan-v2&envId=top-interview-150">228. Summary Ranges</a>
     * @param nums
     * @return
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int startIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((i < nums.length - 1 && (long) nums[i + 1] - nums[i] > 1L) || i == nums.length - 1) {
                if (i - startIndex > 0) {
                    result.add(nums[startIndex] + "->" + nums[i]);
                } else {
                    result.add(String.valueOf(nums[startIndex]));
                }
                startIndex = i + 1;
            } else {
                continue;
            }
        }

        return result;
    }
}
