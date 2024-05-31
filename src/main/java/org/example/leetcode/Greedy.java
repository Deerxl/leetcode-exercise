package org.example.leetcode;

import java.util.Arrays;

/**
 * @author jialu.yxl
 * @date 5/22/24 7:05 PM
 */
public class Greedy {

    public static void main(String[] args) {
        System.out.println(candy2(new int[] {1,0,2}));
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
