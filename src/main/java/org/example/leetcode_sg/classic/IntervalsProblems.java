package org.example.leetcode_sg.classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class IntervalsProblems {

    public static void main(String[] args) {
        int[][] points = new int[][] {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println(findMinArrowShots(points));
    }

    /**
     * <a href="https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/?envType=study-plan-v2&envId=top-interview-150">452. Minimum Number of Arrows to Burst Balloons</a>
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));
        int result = 0;
        int i = 0;
        while (i < points.length) {
            int[] point = points[i];
            int j = i + 1;
            int maxAllowed = point[1];
            while (j < points.length && points[j][0] <= maxAllowed) {
                maxAllowed = Math.min(maxAllowed, points[j][1]);
                j++;
            }
            result++;
            i = j;
        }

        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/insert-interval/?envType=study-plan-v2&envId=top-interview-150">57. Insert Interval</a>
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        boolean leftProcessed = false;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (list.isEmpty() || interval[0] > list.get(list.size() - 1)[1]) {
                list.add(interval);
            } else {
                list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], interval[1]);
            }

            if (!leftProcessed && newInterval[0] <= list.get(list.size() - 1)[1]) {
                if (newInterval[1] < list.get(list.size() - 1)[0]) {
                    list.add(list.size() - 1, newInterval);
                } else {
                    list.get(list.size() - 1)[0] = Math.min(list.get(list.size() - 1)[0], newInterval[0]);
                    list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], newInterval[1]);
                }
                leftProcessed = true;
            }
        }

        if (!leftProcessed) {
            list.add(newInterval);
        }

        int[][] result = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

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
