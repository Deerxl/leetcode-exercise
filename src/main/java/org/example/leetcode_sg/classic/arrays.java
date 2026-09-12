package org.example.leetcode_sg.classic;

import org.example.leetcode_sg.common.Interval;
import org.testng.collections.Maps;

import java.util.*;

public class arrays {

    public static void main(String[] args) {
        int[] nums = new int[]{-1,-1,0,-3,-3};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    /**
     * <a href="https://leetcode.com/problems/text-justification/description/?envType=study-plan-v2&envId=top-interview-150">68. Text Justification</a>
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int curWordsLength = 0, avgSpaceLen = 0, modSpaceLen = 0, curSpaceLen = 0;
        List<String> curWords = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            curWordsLength += word.length();
            curWords.add(word);
            if (i == words.length - 1 || curWordsLength + curWords.size() + words[i + 1].length() > maxWidth) {
                StringBuilder sb = new StringBuilder();
                if (curWords.size() == 1) {
                    sb.append(curWords.get(0));
                    for (int j = 0; j < maxWidth - curWords.get(0).length(); j++) {
                        sb.append(" ");
                    }
                } else {
                    avgSpaceLen = (maxWidth - curWordsLength) / (curWords.size() - 1);
                    modSpaceLen = (maxWidth - curWordsLength) % (curWords.size() - 1);

                    for (int j = 0; j < curWords.size(); j++) {
                        sb.append(curWords.get(j));
                        if (i == words.length - 1) {
                            if (j < curWords.size() - 1) {
                                sb.append(" ");
                            } else {
                                curSpaceLen = maxWidth - curWordsLength - curWords.size() + 1;
                                for (int k = 0; k < curSpaceLen; k++) {
                                    sb.append(" ");
                                }
                            }
                        } else {
                            if (j < curWords.size() - 1) {
                                curSpaceLen = avgSpaceLen + (modSpaceLen-- > 0 ? 1 : 0);
                                for (int k = 0; k < curSpaceLen; k++) {
                                    sb.append(" ");
                                }
                            }
                        }
                    }
                }
                result.add(sb.toString());
                curWordsLength = 0;
                curWords.clear();
            }
        }


        return result;
    }


    /**
     * <a href="https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/?envType=study-plan-v2&envId=top-interview-150">28. Find the Index of the First Occurrence in a String</a>
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        // if (!haystack.contains(needle)) {
        //     return -1;
        // }

        return haystack.indexOf(needle);
    }

    /**
     * <a href="https://leetcode.com/problems/zigzag-conversion/description/?envType=study-plan-v2&envId=top-interview-150">6. Zigzag Conversion</a>
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int batchNumCount = 2 * numRows - 2;
        int batchColCount = numRows - 1;
        Map<Integer, StringBuilder> map = new HashMap<>();
        for (int i = 0; i < numRows; i++) {
            map.put(i, new StringBuilder());
        }
        int index = 0, curRow = 0;
        for (int i = 0; i <= s.length() / batchNumCount; i++) {
            index = i * batchNumCount;
            for (curRow = 0; curRow < numRows && index < s.length(); curRow++) {
                map.put(curRow, map.get(curRow).append(s.charAt(index)));
                index++;
            }
            for (curRow = numRows - 2; curRow > 0 && index < s.length(); curRow--) {
                map.put(curRow, map.get(curRow).append(s.charAt(index)));
                index++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            sb.append(map.get(i));
        }
        return sb.toString();
    }

    /**
     * <a href="https://leetcode.com/problems/reverse-words-in-a-string/?envType=study-plan-v2&envId=top-interview-150">151. Reverse Words in a String</a>
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        while (i >= 0) {
            char c = s.charAt(i);
            if (c == ' ') {
                i--;
                continue;
            }
            int j = i - 1;
            while (j >= 0 && s.charAt(j) != ' ') {
                j--;
            }
            sb.append(s.substring(j + 1, i + 1)).append(' ');
            i = j - 1;
        }
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

    /**
     * <a href="https://leetcode.com/problems/longest-common-prefix/?envType=study-plan-v2&envId=top-interview-150">14. Longest Common Prefix</a>
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();
        boolean same = true;
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    same = false;
                    break;
                }
            }
            if (!same) {
                break;
            }
            result.append(c);
        }
        return result.toString();
    }

    /**
     * <a href="https://leetcode.com/problems/length-of-last-word/description/?envType=study-plan-v2&envId=top-interview-150">58. Length of Last Word</a>
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        s = s.trim();
        if (s.length() < 3) {
            return s.length();
        }
        String[] arr = s.split(" ");
        return arr[arr.length - 1].length();
    }

    /**
     * <a href="https://leetcode.com/problems/integer-to-roman/?envType=study-plan-v2&envId=top-interview-150">12. Integer to Roman</a>
     *
     * @param num
     * @return
     *
     * time & space complexity: O(1)
     */
    public String intToRoman(int num) {
        String[] symbols = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] numbers = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int index = 0;
        StringBuilder sb = new StringBuilder();

        while (index < numbers.length && num > 0) {
            while (num >= numbers[index]) {
                sb.append(symbols[index]);
                num -= numbers[index];
            }
            index++;
        }

        return sb.toString();
    }


    /**
     * <a href="https://leetcode.com/problems/roman-to-integer/description/?envType=study-plan-v2&envId=top-interview-150">13. Roman to Integer</a>
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>() {
            {
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }
        };
        int i = 0;
        int result = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (i < s.length() - 1 && map.get(c) < map.get(s.charAt(i + 1))) {
                result -= map.get(c);
            } else {
                result += map.get(c);
            }

            i++;
        }
        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/trapping-rain-water/description/?envType=study-plan-v2&envId=top-interview-150">42. Trapping Rain Water</a>
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int[] left = new int[height.length];
        left[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        int[] right = new int[height.length];
        right[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        int result = 0;
        for (int i = 0; i < height.length; i++) {
            result += Math.min(left[i], right[i]) - height[i];
        }
        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/candy/description/?envType=study-plan-v2&envId=top-interview-150">135. Candy</a>
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int result = 0;
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
        }

        for (int val : candies) {
            result += val;
        }

        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/gas-station/?envType=study-plan-v2&envId=top-interview-150">134. Gas Station</a>
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int remain = 0;
        int index = 0;
        int sum = 0;
        boolean find = false;
        for (int i = 0; i < gas.length; i++) {
            remain = remain - cost[i] + gas[i];
            if (sum + gas[i] - cost[i] >= 0) {
                if (!find) {
                    index = i;
                    find = true;
                }
                sum += gas[i] - cost[i];
            } else {
                sum = 0;
                find = false;
            }
        }
        if (remain < 0) {
            return -1;
        }

        return index;
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
