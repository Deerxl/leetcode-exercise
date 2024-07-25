package org.example.leetcode;

import java.util.*;

/**
 * @author jialu.yxl
 * @date 5/22/24 7:05 PM
 */
public class Greedy {

    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[] {5,1,2,3,4}, new int[] {4,4,1,5,1}));
    }




    /**
     * <a href="https://leetcode.cn/problems/partition-labels/">763. Partition Labels</a>
     * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.
     * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
     * Return a list of integers representing the size of these parts.
     * Example 1:
     *
     * Input: s = "ababcbacadefegdehijhklij"
     * Output: [9,7,8]
     * Explanation:
     * The partition is "ababcbaca", "defegde", "hijhklij".
     * This is a partition so that each letter appears in at most one part.
     * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
     * Example 2:
     *
     * Input: s = "eccbbbbdec"
     * Output: [10]
     */
    public List<Integer> partitionLabels(String s) {
        int[] lastIndexArr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndexArr[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int start = 0;
        int end = 0;
        result.add(1);

        for (int i = 0; i < s.length(); i++) {
            int lastIndex = lastIndexArr[s.charAt(i) - 'a'];
            if (lastIndex > end) {
                if (i > end) {
                    start = i;
                    end = lastIndex;
                    result.add(end - start + 1);
                } else {
                    end = lastIndex;
                    result.set(result.size() - 1, end - start + 1);
                }
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/remove-duplicate-letters/">316. Remove Duplicate Letters</a>
     * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is
     * the smallest in lexicographical order among all possible results.
     * Example 1:
     * Input: s = "bcabc"
     * Output: "abc"
     * @param s 1 <= s.length <= 10^4
     * @return
     */
    public static String removeDuplicateLetters(String s) {
        if (s.length() <= 1) {
            return s;
        }

        int[] nums = new int[26];
        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'a']++;
        }

        boolean[] visited = new boolean[26];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!visited[c - 'a']) {
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > c) {
                    if (nums[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        visited[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                sb.append(c);
                visited[c - 'a'] = true;
            }
            nums[c - 'a']--;
        }

        return sb.toString();
    }

    /**
     * <a href="https://leetcode.cn/problems/gas-station/">134. Gas Station</a>
     * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
     * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique
     * Example 1:
     * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
     * Output: 3
     * Explanation:
     * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
     * Travel to station 4. Your tank = 4 - 1 + 5 = 8
     * Travel to station 0. Your tank = 8 - 2 + 1 = 7
     * Travel to station 1. Your tank = 7 - 3 + 2 = 6
     * Travel to station 2. Your tank = 6 - 4 + 3 = 5
     * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
     * Therefore, return 3 as the starting index.
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        long totalCost = 0;


        int curStartIndex = -1;
        int curBalance = 0;
        int balance = 0;

        for (int i = 0; i < len; i++) {
            balance = gas[i] - cost[i];
            totalCost += balance;

            if (balance >= 0) {
                if (curStartIndex == -1) {
                    curStartIndex = i;
                    curBalance = balance;
                } else {
                    curBalance += balance;
                }
            } else {
                curBalance += balance;
                if (curBalance < 0) {
                    curStartIndex = -1;
                    curBalance = 0;
                }
            }
        }

        if (totalCost < 0 || curBalance < 0 || curStartIndex == -1) {
            return -1;
        }

        for (int i = 0; i < curStartIndex; i++) {
            balance = gas[i] - cost[i];
            curBalance += balance;
        }

        return curBalance >= 0 ? curStartIndex : -1;
    }

    /**
     * <a href="https://leetcode.cn/problems/valid-parenthesis-string/">678. Valid Parenthesis String</a>
     * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
     * The following rules define a valid string:
     * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     * Any right parenthesis ')' must have a corresponding left parenthesis '('.
     * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
     * @param s 1 <= s.length <= 100
     * @return
     */
    public boolean checkValidString(String s) {
        int nonMatchCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                nonMatchCount++;
            } else if (c == ')' || c == '*') {
                if (nonMatchCount > 0) {
                    nonMatchCount--;
                }
            }
        }

        if (nonMatchCount > 0) {
            return false;
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '(' || c == '*') {
                if (nonMatchCount > 0) {
                    nonMatchCount--;
                }
            } else if (c == ')') {
                nonMatchCount++;
            }
        }

        return nonMatchCount == 0;
    }

    /**
     * <a href="https://leetcode.cn/problems/candy/">135. Candy</a>
     * There are n children standing in a line.
     * Each child is assigned a rating value given in the integer array ratings.
     * You are giving candies to these children subjected to the following requirements:
     * Each child must have at least one candy.
     * Children with a higher rating get more candies than their neighbors.
     * Return the minimum number of candies you need to have to distribute the candies to the children.
     * Input: ratings = [1,2,2]
     * Output: 4
     * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
     * The third child gets 1 candy because it satisfies the above two conditions.
     * @param ratings n == ratings.length 1 <= n <= 2 * 104 0 <= ratings[i] <= 2 * 104
     * @return
     */
    public static int candy1(int[] ratings) {
        int[] result = new int[ratings.length];
        Arrays.fill(result, 1);

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                result[i] = result[i - 1] + 1;
            } else if (ratings[i] < ratings[i - 1]) {
                for (int j = i - 1; j >= 0 && ratings[j] > ratings[j + 1]; j--) {
                    if (result[j] > result[j + 1]) {
                        break;
                    } else {
                        result[j] = result[j + 1] + 1;
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < result.length; i++) {
            sum += result[i];
        }
        return sum;
    }

    public static int candy2(int[] ratings) {
        int[] result = new int[ratings.length];
        Arrays.fill(result, 1);

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                result[i] = result[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && result[i] <= result[i + 1]) {
                result[i] = result[i + 1] + 1;
            }
        }

        int sum = 0;
        for (int j : result) {
            sum += j;
        }
        return sum;
    }
}
