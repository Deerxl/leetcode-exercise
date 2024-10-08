package org.example.leetcode.classification;

import java.util.Stack;

/**
 * @author jialu.yxl
 * @date 5/11/24 8:42 PM
 */
public class Stacks {

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[] {2,1,5,6,2,3}));
    }


    /**
     * <a href="https://leetcode.cn/problems/score-of-parentheses/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china">856. Score of Parentheses</a>
     * Given a balanced parentheses string s, return the score of the string.
     *
     * The score of a balanced parentheses string is based on the following rule:
     *
     * "()" has score 1.
     * AB has score A + B, where A and B are balanced parentheses strings.
     * (A) has score 2 * A, where A is a balanced parentheses string.
     *
     *
     * Example 1:
     * Input: s = "()"
     * Output: 1
     * Example 2:
     * Input: s = "(())"
     * Output: 2
     * @param s 2 <= s.length <= 50
     * s consists of only '(' and ')'.
     * s is a balanced parentheses string.
     * @return
     */
    public int scoreOfParentheses(String s) {
        int result = 0;

        Stack<String> opStack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                opStack.push("(");
                i++;
            } else if (s.charAt(i) == ')') {
                if (opStack.peek().equals("(")) {
                    opStack.pop();
                    opStack.push("1");
                    i++;
                } else {
                    int num = 0;
                    while (!opStack.peek().equals("(")) {
                        num += Integer.parseInt(opStack.pop());
                    }
                    opStack.pop();
                    num *= 2;
                    opStack.push(String.valueOf(num));
                    i++;
                }
            }
        }

        while (!opStack.isEmpty()) {
            int num = Integer.parseInt(opStack.pop());
            result += num;
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/largest-rectangle-in-histogram/">84. Largest Rectangle in Histogram</a>
     * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
     * Input: heights = [2,1,5,6,2,3]
     * Output: 10
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int result = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                result = Math.max(result, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            result = Math.max(result, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/next-greater-element-ii/">503. Next Greater Element II</a>
     * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
     *
     * The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
     * Example 1:
     * Input: nums = [1,2,1]
     * Output: [2,-1,2]
     * Explanation: The first 1's next greater number is 2;
     * The number 2 can't find next greater number.
     * The second 1's next greater number needs to search circularly, which is also 2.
     * Example 2:
     * Input: nums = [1,2,3,4,3]
     * Output: [2,3,4,-1,4]
     * @param nums 1 <= nums.length <= 10^4
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];

        stack.push(0);
        for (int i = 1; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }

        for (int i = 0; i < nums.length && !stack.isEmpty(); i++) {
            if (i >= stack.peek()) {
                break;
            }
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                result[stack.pop()] = nums[i];
            }
        }

        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/largest-rectangle-in-histogram/">84. Largest Rectangle in Histogram</a>
     *Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
     * @param heights 1 <= heights.length <= 105 0 <= heights[i] <= 104
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int i = 0;
        for (; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                result = Math.max(result, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            result = Math.max(result, heights[stack.pop()] * (i - stack.peek() - 1));
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/remove-k-digits/">402. Remove K Digits</a>
     * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.
     * Example 1:
     * Input: num = "1432219", k = 3
     * Output: "1219"
     * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        if (num.length() <= 1) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        stack.push(num.charAt(0));
        int i = 1;
        while (i < num.length()) {
            char c = num.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
                i++;
            } else {
                if (c - stack.peek() >= 0) {
                    stack.push(c);
                    i++;
                } else {
                    while (k > 0 && !stack.isEmpty() && c - stack.peek() < 0) {
                        stack.pop();
                        k--;
                    }
                    stack.push(c);
                    i++;
                    if (k == 0) {
                        break;
                    }
                }
            }
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.insert(0, stack.pop());
        }
        if (i < num.length()) {
            stringBuilder.append(num.substring(i));
        }
        i = 0;
        while (i < stringBuilder.length()) {
            if (stringBuilder.charAt(i) == '0') {
                i++;
            } else {
                break;
            }
        }
        return i >= stringBuilder.length() ? "0" : stringBuilder.substring(i);
    }

    /**
     * <a href="https://leetcode.cn/problems/daily-temperatures/">739. 每日温度</a>
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
     * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * 示例 1:
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 0) {
            return new int[]{};
        }

        int[] result = new int[temperatures.length];
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] {temperatures[0], 0});
        for (int i = 1; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > stack.peek()[0]) {
                int[] pop = stack.pop();
                result[pop[1]] = i - pop[1];
            }
            stack.push(new int[] {temperatures[i], i});
        }

        return result;
    }
}
