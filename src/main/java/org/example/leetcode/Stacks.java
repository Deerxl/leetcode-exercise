package org.example.leetcode;

import java.util.Stack;

/**
 * @author jialu.yxl
 * @date 5/11/24 8:42 PM
 */
public class Stacks {

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
