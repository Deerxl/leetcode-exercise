package org.example.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * @author jialu.yxl
 * @date 02/02/2023 15:27
 */
public class Dp {

    public static void main(String[] args) {
        System.out.println(isMatch("aab", "c*a*b"));

        // System.out.println(maxProfit(2, new int[]{3,2,6,5,0,3}));

    }

    /**
     * <a href="https://leetcode.cn/problems/regular-expression-matching/">10. Regular Expression Matching</a>
     * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * The matching should cover the entire input string (not partial).
     * Input: s = "ab", p = ".*"
     * Output: true
     * Explanation: ".*" means "zero or more (*) of any character (.)".
     * @param s 1 <= s.length <= 20
     * @param p 1 <= p.length <= 20
     * @return
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // p匹配s前0个字符
        for (int j = 0; j < n; j++) {
            if (p.charAt(j) == '*') {
                dp[0][j + 1] = dp[0][j - 1];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 字符匹配 或 为 .
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    // p前一个字符和s匹配
                    if (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        // dp[i + 1][j - 1]：*匹配0次，p后续继续匹配；
                        // dp[i][j - 1]：*匹配1次；
                        // dp[i][j + 1]：*匹配2次或以上
                        dp[i + 1][j + 1] = dp[i + 1][j - 1] || dp[i][j - 1] || dp[i][j + 1];
                    } else {
                        // 前一个字符不匹配
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    }
                }
            }
        }

        return dp[m][n];
    }


    /**
     * <a href="https://leetcode.cn/problems/house-robber-ii/">213. House Robber II</a>
     * You are a professional robber planning to rob houses along a street.
     * Each house has a certain amount of money stashed.
     * All houses at this place are arranged in a circle.
     * That means the first house is the neighbor of the last one.
     * Meanwhile, adjacent houses have a security system connected,
     * and it will automatically contact the police if two adjacent houses were broken into on the same night.
     * Given an integer array nums representing the amount of money of each house,
     * return the maximum amount of money you can rob tonight without alerting the police.
     * Example 1:
     * Input: nums = [2,3,2]
     * Output: 3
     * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
     * @param nums 1 <= nums.length <= 100
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int result = 0;
        int[] dp = new int[nums.length + 2];
        dp[1] = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            dp[i + 1] = Math.max(dp[i - 1] + nums[i], dp[i]);
        }
        result = dp[nums.length - 1];

        dp[nums.length] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 1; i--) {
            dp[i + 1] = Math.max(dp[i + 3] + nums[i], dp[i + 2]);
        }
        result = Math.max(result, dp[2]);
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/decode-ways/">91. Decode Ways</a>
     * A message containing letters from A-Z can be encoded into numbers using the following mapping:
     * 'A' -> "1"
     * 'B' -> "2"
     * ...
     * 'Z' -> "26"
     * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
     * "AAJF" with the grouping (1 1 10 6)
     * "KJF" with the grouping (11 10 6)
     * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
     * Given a string s containing only digits, return the number of ways to decode it.
     * Input: s = "226"
     * Output: 3
     * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
     * @param s
     * @return
     */
    public static int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '0') {
                if (i > 0 && (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2')) {
                    dp[i + 1] = dp[i - 1];
                } else {
                    break;
                }
            } else {
                dp[i + 1] += dp[i];
                if (i > 0) {
                    char c0 = s.charAt(i - 1);
                    if (c0 != '0') {
                        int num = Integer.parseInt(c0 + String.valueOf(c));
                        if (num <= 26) {
                            dp[i + 1] += dp[i - 1];
                        }
                    }
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * <a href="https://mp.weixin.qq.com/s/NZPaFsFrTybO3K3s7p7EVg">圆环回原点问题</a>
     * 0~9的环，从0出发，N步后能否走回0
     * 走n步到0的方案数=走n-1步到1的方案数+走n-1步到9的方案数。
     * @param n
     * @return
     */
    public static int backToOrigin(int n) {
        int[][] dp = new int[n + 1][10];

        int len = 10;
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j] = dp[i - 1][(j - 1 + len) % len] + dp[i - 1][(j + 1) % len];
            }
        }

        return dp[n][0];
    }

    /**
     * <a href="https://leetcode.cn/problems/coin-change-ii/">518. Coin Change II</a>
     * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
     * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
     * You may assume that you have an infinite number of each kind of coin.
     * The answer is guaranteed to fit into a signed 32-bit integer.
     * Example 1:
     * Input: amount = 5, coins = [1,2,5]
     * Output: 4
     * Explanation: there are four ways to make up the amount:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.sort(coins);
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }

        return dp[amount];
    }

    /**
     * <a href="https://leetcode.cn/problems/jump-game/">55. Jump Game</a>
     * You are given an integer array nums. You are initially positioned at the array's first index,
     * and each element in the array represents your maximum jump length at that position.
     * Return true if you can reach the last index, or false otherwise.
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }

        int maxIndex = nums[0];
        int curIndex = 0;
        while (curIndex <= maxIndex && curIndex < nums.length) {
            if (curIndex == nums.length - 1 || maxIndex >= nums.length - 1) {
                return true;
            }
            maxIndex = Math.max(maxIndex, nums[curIndex] + curIndex);
            curIndex++;
        }
        return false;
    }

    /**
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/">123. Best Time to Buy and Sell Stock III</a>
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * Find the maximum profit you can achieve. You may complete at most two transactions.
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     * Example 1:
     * Input: prices = [3,3,5,0,0,3,1,4]
     * Output: 6
     * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int buy1 = prices[0], buy2 = prices[0];
        int sell1 = 0, sell2 = 0;
        for (int i = 1; i < prices.length; i++) {
            buy1 = Math.min(buy1, prices[i]);
            sell1 = Math.max(sell1, prices[i] - buy1);
            buy2 = Math.min(buy2, prices[i] - sell1);
            sell2 = Math.max(sell2, prices[i] - buy2);
        }
        return sell2;
    }

    /**
     * <a href="https://leetcode.cn/problems/word-break/">139. 单词拆分</a>
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < wordDict.size(); j++) {
                String str = wordDict.get(j);
                if (i - str.length() + 1 >= 0
                        && dp[i - str.length() + 1]
                        && str.equals(s.substring(i - str.length() + 1, i + 1))) {
                    dp[i + 1] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    /**
     * <a href="https://leetcode.cn/problems/house-robber/">198. 打家劫舍</a>
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * 示例 1：
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    /**
     * <a href="https://leetcode.cn/problems/unique-paths/">62. 不同路径</a>
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * <a href="https://leetcode.cn/problems/maximum-length-of-repeated-subarray/">718. 最长重复子数组</a>
     * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
     * 示例 1：
     * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
     * 输出：3
     * 解释：长度最长的公共子数组是 [3,2,1] 。
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return 0;
        }

        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int result = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    result = Math.max(result, dp[i + 1][j + 1]);
                }
            }
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/climbing-stairs/">70. 爬楼梯</a>
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 输入：n = 3
     * 输出：3
     * 解释：有三种方法可以爬到楼顶。
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int result = 0;
        int pre1 = 1;
        int pre2 = 2;
        for (int i = 3; i <= n; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/longest-common-subsequence/">1143. 最长公共子序列</a>
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     *
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = text1.charAt(i - 1);
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }

        return dp[m][n];
    }

    /**
     * <a href="https://leetcode.cn/problems/edit-distance/">72. 编辑距离</a>
     * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if (m == 0 || n == 0) {
            return m + n;
        }
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);

                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
            }
        }

        return dp[m][n];
    }

    /**
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/">121. 买卖股票的最佳时机</a>
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int result = 0;
        int lastMinIndex = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[i - 1]) {
                if (prices[i] <= prices[lastMinIndex]) {
                    lastMinIndex = i;
                }
            } else if (prices[i] > prices[i - 1]) {
                result = Math.max(result, prices[i] - prices[lastMinIndex]);
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/description/">188. 买卖股票的最佳时机 IV</a>
     * @param k
     * @param prices
     * @return
     */
    public static int maxProfit(int k, int[] prices) {
        if (k == 0) {
            return 0;
        }
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, prices[0]);

        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.min(buy[j], prices[i] - sell[j - 1]);
                sell[j] = Math.max(sell[j], prices[i] - buy[j]);
            }
        }
        return sell[k];
    }

    /**
     * <a href="https://leetcode.cn/problems/candy/">135. 分发糖果</a>
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int[] dp = new int[ratings.length];
        Arrays.fill(dp, 1);
        int sum = 0;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else if (ratings[i] < ratings[i - 1]) {
                if (dp[i] == dp[i - 1]) {
                    for (int j = i - 1; j >= 0; j--) {
                        if (ratings[j] > ratings[j + 1] && dp[j] == dp[j + 1]) {
                            dp[j] = dp[j + 1] + 1;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            sum += dp[i];
        }
        return sum;
    }

    /**
     * <a href="https://leetcode.cn/problems/coin-change-ii/">518. 零钱兑换 II</a>
     * 输入：amount = 5, coins = [1, 2, 5]
     * 输出：4
     * 解释：有四种方式可以凑成总金额：
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * @param amount
     * @param coins
     * @return
     */
    public static int change1(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }

        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[amount - j];
            }
        }

        return dp[amount];
    }

    /**
     * <a href="https://mp.weixin.qq.com/s/NZPaFsFrTybO3K3s7p7EVg">圆环回原点问题</a>
     * 圆环上有10个点，编号为0~9。从0点出发，每次可以逆时针和顺时针走一步，问走n步回到0点共有多少种走法。
     * @param n
     * @return
     */
    public static int backToOrigin1(int n) {
        int[][] dp = new int[n + 1][10];
        dp[0][0] = 1;
        int len = 10;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < len; j++) {
                // dp[i][j]表示从0出发，走i步到j的方案数
                dp[i][j] = dp[i - 1][(j - 1 + len) % len] + dp[i - 1][(j + 1) % len];
            }
        }
        return dp[n][0];
    }

    /**
     * <a href="https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/">剑指 Offer 10- II. 青蛙跳台阶问题</a>
     * @param n
     * @return 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     */
    public static int numWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n <= 2) {
            return n;
        }
        int first = 0, modFirst = 1, second = 0, modSecond = 2;
        int result = 0;
        long resultMod = 0;
        for (int i = 3; i <= n; i++) {
            long mod = modFirst + modSecond;
            result = first + second + (int)(mod / 1000000007);
            resultMod = mod % 1000000007;

            first = second;
            modFirst = modSecond;

            second = result;
            modSecond = (int) resultMod;
        }
        return (int) resultMod % 1000000007;
    }

}
