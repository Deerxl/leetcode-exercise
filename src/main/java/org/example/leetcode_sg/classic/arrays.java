package org.example.leetcode_sg.classic;

import org.example.leetcode_sg.common.Interval;

import java.util.*;

public class arrays {

    public static void main(String[] args) {
        int[] nums = new int[]{-1,-1,0,-3,-3};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }




    /**
     * <a href="https://leetcode.com/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=top-interview-150">238. Product of Array Except Self</a>
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {
        int zeroCount = 0;
        int mulTotalExceptZero = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else {
                mulTotalExceptZero *= nums[i];
            }
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && zeroCount == 1) {
                result[i] = mulTotalExceptZero;
            } else if (zeroCount == 0) {
                result[i] = mulTotalExceptZero / nums[i];
            }
        }

        return result;
    }


    /**
     * <a href="https://leetcode.com/problems/h-index/solutions/4928640/python-2-approaches-sorting-counting-sum-h8le/?envType=study-plan-v2&envId=top-interview-150">274. H-Index</a>
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        int result = 0;
        int curIndex = 0;
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++) {
            curIndex = Math.min(citations[i], citations.length - i);
            result = Math.max(result, curIndex);
            if (result >= citations.length - i) {
                break;
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/jump-game-ii/?envType=study-plan-v2&envId=top-interview-150">45. Jump Game II</a>
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums.length <= 2) {
            return nums.length - 1;
        }
        int steps = 0;
        int maxReachIndex = nums[0];
        int curMaxReachIndex = nums[0];
        int i = 0;
        while (i < nums.length - 1) {
            steps++;
            curMaxReachIndex = maxReachIndex;
            if (curMaxReachIndex >= nums.length - 1) {
                break;
            }
            for (int j = i + 1; j <= curMaxReachIndex; j++) {
                maxReachIndex = Math.max(nums[j] + j, maxReachIndex);
            }
            i = curMaxReachIndex;
        }
        return steps;
    }

    /**
     * <a href="https://leetcode.com/problems/jump-game/?envType=study-plan-v2&envId=top-interview-150">55. Jump Game</a>
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int i = 0;
        int maxReachIndex = nums[0];
        while (i <= maxReachIndex && i < nums.length) {
            maxReachIndex = Math.max(maxReachIndex, nums[i] + i);
            i++;
        }
        return i >= nums.length;
    }


    /**
     * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/?envType=study-plan-v2&envId=top-interview-150">122. Best Time to Buy and Sell Stock II</a>
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                result += prices[i] - prices[i - 1];
            }
        }
        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/?envType=study-plan-v2&envId=top-interview-150">121. Best Time to Buy and Sell Stock</a>
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int result = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                result = Math.max(result, prices[i] - minPrice);
            }
        }
        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/rotate-array/description/?envType=study-plan-v2&envId=top-interview-150">189. Rotate Array</a>
     * @param nums
     * @param k
     *
     * quite tricky, with space complexity: O(1), reverse 3 times.
     */
    public void rotate(int[] nums, int k) {
        if (nums.length == 1 || k == 0) {
            return;
        }
        k = k % nums.length;
        if (k == 0) {
            return;
        }

        reverse(nums, 0, nums.length - 1);
        reverse(nums, k, nums.length - 1);
        reverse(nums, 0, k - 1);
    }


    public void reverse(int[] nums, int start, int end) {
        int tmp;
        while (start < end) {
            tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    /**
     * <a href="https://leetcode.com/problems/majority-element/?envType=study-plan-v2&envId=top-interview-150">169. Majority Element</a>
     * @param nums
     * @return
     *
     * Moore Voting Algorithm
     */
    public int majorityElement(int[] nums) {
        if (nums.length == 2) {
            return nums[0];
        }
        int count = 0;
        int num = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            } else if (count == 0) {
                count++;
                num = nums[i];
            } else {
                count--;
            }
        }
        return num;
    }


    /**
     * <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/?envType=study-plan-v2&envId=top-interview-150">80. Remove Duplicates from Sorted Array II</a>
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int currentVal = Integer.MAX_VALUE;
        int occurTimes = 0;
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (currentVal != nums[i]) {
                nums[k] = nums[i];
                k++;
                currentVal = nums[i];
                occurTimes = 1;
            } else if (occurTimes < 2) {
                occurTimes += 1;
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }


    /**
     * <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array/?envType=study-plan-v2&envId=top-interview-150">26. Remove Duplicates from Sorted Array</a>
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int currentVal = Integer.MAX_VALUE;
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != currentVal) {
                nums[k] = nums[i];
                currentVal = nums[i];
                k++;
            }
        }
        return k;
    }

    /**
     * <a href="https://leetcode.com/problems/remove-element/description/?envType=study-plan-v2&envId=top-interview-150">27. Remove Element</a>
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    /**
     * <a href="https://leetcode.com/problems/merge-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150">88. Merge Sorted Array</a>
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        n -= 1;
        m -= 1;
        while (n >= 0) {
            if (m < 0 || nums2[n] >= nums1[m]) {
                nums1[index] = nums2[n];
                n--;
            } else {
                nums1[index] = nums1[m];
                m--;
            }
            index--;
        }
    }

    /**
     * <a href="https://neetcode.io/problems/meeting-schedule/question">252. Meeting Rooms</a>
     * time complexity: O(NlogN); space complexity: O(N)
     * @param intervals
     * @return
     */
    public static boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals.size() <= 1) {
            return true;
        }
        intervals.sort(Comparator.comparingInt(o -> o.start));
        int preRight = Integer.MIN_VALUE;
        for (Interval interval : intervals) {
            if (interval.start < preRight) {
                return false;
            }
            preRight = interval.end;
        }
        return true;
    }


    /**
     * <a href="https://neetcode.io/problems/meeting-schedule-ii/question">253. Meeting Rooms II</a>
     * Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), find the minimum number of rooms required to schedule all meetings without any conflicts.
     *
     * Note: (0,8),(8,10) is NOT considered a conflict at 8.
     *
     * time complexity: O(NlogN), space complexity: O(N)
     */
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals.size() <= 1) {
            return intervals.size();
        }
        int result = 0;

        intervals.sort((o1, o2) -> {
            if (o1.start != o2.start) {
                return o1.start - o2.start;
            }
            return o1.end - o2.end;
        });

        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        for (Interval interval : intervals) {
            if (queue.isEmpty() || queue.peek() > interval.start) {
                result += 1;
            } else {
                queue.poll();
            }
            queue.offer(interval.end);
        }

        return result;
    }


    /**
     * <a href="https://leetcode.com/problems/merge-intervals/">56. Merge Intervals</a>
     * time complexity: O(NlogN), space complexity: O(N)
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> resultList = new ArrayList<>();
        int[] lastInterval = intervals[0];
        for (int[] interval : intervals) {
            if (interval[0] > lastInterval[1]) {
                resultList.add(lastInterval);
                lastInterval = interval;
            } else {
                lastInterval[1] = Math.max(lastInterval[1], interval[1]);
            }
        }
        resultList.add(lastInterval);

        return resultList.toArray(new int[resultList.size()][2]);
    }




}
