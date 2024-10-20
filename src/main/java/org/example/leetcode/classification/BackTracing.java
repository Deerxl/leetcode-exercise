package org.example.leetcode.classification;

import java.util.*;

/**
 * @author jialu.yxl
 * @date 6/4/24 3:02 PM
 */
public class BackTracing {


    public static void main(String[] args) {

        List<List<Integer>> lists = permuteUnique(new int[]{1, 2, 3});
        System.out.println(lists.size());
        System.out.println(lists);
    }


    /**
     * <a href="https://leetcode.cn/problems/permutations-ii/description/">47. Permutations II</a>
     * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
     * Example 1:
     *
     * Input: nums = [1,1,2]
     * Output:
     * [[1,1,2],
     *  [1,2,1],
     *  [2,1,1]]
     * @param nums 1 <= nums.length <= 8
     * -10 <= nums[i] <= 10
     * @return
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);

        permuteUniqueResult = new ArrayList<>();
        permuteUniqueList = new ArrayList<>();

        for (int num : nums) {
            permuteUniqueList.add(num);
        }
        permuteUnique(0, nums.length - 1);

        return permuteUniqueResult;
    }

    static List<Integer> permuteUniqueList;
    static List<List<Integer>> permuteUniqueResult;
    static void permuteUnique(int start, int end) {
        if (start == end) {
            permuteUniqueResult.add(new ArrayList<>(permuteUniqueList));
            return;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            if (set.contains(permuteUniqueList.get(i))) {
                continue;
            }

            set.add(permuteUniqueList.get(i));
            swap(permuteUniqueList, i, start);
            permuteUnique(start + 1, end);
            swap(permuteUniqueList, i, start);
        }
    }

    static void swap(List<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * <a href="https://leetcode.cn/problems/palindrome-partitioning/">131. Palindrome Partitioning</a>
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return all possible palindrome partitioning of s.
     * Example 1:
     * Input: s = "aab"
     * Output: [["a","a","b"],["aa","b"]]
     * @param s 1 <= s.length <= 16
     * @return
     */
    public static List<List<String>> partition(String s) {

        palindromePartitionLen = s.length();
        palindromePartitionDp = new boolean[palindromePartitionLen][palindromePartitionLen];

        for (int i = 0; i < palindromePartitionLen; i++) {
            Arrays.fill(palindromePartitionDp[i], true);
        }


        // dp[i][j] s[i...j]是否为回文
        //          = true, i >= j
        //          = s[i] == s[j] && dp[i + 1][j - 1], otherwise
        for (int i = palindromePartitionLen - 1; i >= 0; i--) {
            for (int j = 0; j < palindromePartitionLen; j++) {
                if (i < j) {
                    palindromePartitionDp[i][j] = s.charAt(i) == s.charAt(j) && i < palindromePartitionLen - 1 && palindromePartitionDp[i + 1][j - 1];
                }
            }
        }

        palindromePartitionResult = new ArrayList<>();
        palindromePartitionTmpResultList = new ArrayList<>();

        partition(s, 0);

        return palindromePartitionResult;
    }

    static boolean[][] palindromePartitionDp;
    static int palindromePartitionLen;
    static List<String> palindromePartitionTmpResultList;
    static List<List<String>> palindromePartitionResult;

    private static void partition(String s, int i) {
        if (i == palindromePartitionLen) {
            palindromePartitionResult.add(new ArrayList<>(palindromePartitionTmpResultList));
            return;
        }

        for (int j = i; j < palindromePartitionLen; j++) {
            if (palindromePartitionDp[i][j]) {
                palindromePartitionTmpResultList.add(s.substring(i, j + 1));
                partition(s, j + 1);
                palindromePartitionTmpResultList.remove(palindromePartitionTmpResultList.size() - 1);
            }
        }
    }



    /**
     * <a href="https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/description/">698. Partition to K Equal Sum Subsets</a>
     * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
     * Example 1:
     *
     * Input: nums = [4,3,2,3,5,2,1], k = 4
     * Output: true
     * Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums.length < k) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        Arrays.sort(nums);
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int tmp = nums[r];
            nums[r] = nums[l];
            nums[l] = tmp;
            l++;
            r--;
        }

        return canPartitionKSubsets(nums, k, 0, new int[k], sum / k);
    }

    private boolean canPartitionKSubsets(int[] nums, int k, int start, int[] buckets, int target) {
        if (start == nums.length) {
            return true;
        }

        for (int i = 0; i < k; i++) {
            if (i > 0 && buckets[i] == buckets[i - 1]) {
                continue;
            }
            if (buckets[i] + nums[start] > target) {
                continue;
            }
            buckets[i] += nums[start];
            if (canPartitionKSubsets(nums, k, start + 1, buckets, target)) {
                return true;
            }
            buckets[i] -= nums[start];
        }

        return false;
    }

    /**
     * <a href="https://leetcode.cn/problems/additive-number/">306. Additive Number</a>
     * An additive number is a string whose digits can form an additive sequence.
     *
     * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
     *
     * Given a string containing only digits, return true if it is an additive number or false otherwise.
     *
     * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
     * @param num 1 <= num.length <= 35
     * num consists only of digits.
     * @return
     */
    public static boolean isAdditiveNumber(String num) {
        if (num.length() < 3) {
            return false;
        }

        int totalLen = num.length();
        char c = num.charAt(0);
        if (c == '0') {
            return isAdditiveNumber(num, 1, 0);
        } else {
            for (int len1 = 1; len1 <= totalLen / 2; len1++) {
                long num1 = Long.parseLong(num.substring(0, len1));
                if (isAdditiveNumber(num, len1, num1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isAdditiveNumber(String num, int start, long num1) {
        if (start >= num.length()) {
            return false;
        }
        int totalLen = num.length() - start;
        char c = num.charAt(start);
        if (c == '0') {
            return isAdditiveNumber(num, start + 1, num1, 0);
        } else {
            for (int len2 = 1; len2 <= totalLen / 2; len2++) {
                if (start + len2 >= num.length()) {
                    return false;
                }
                long num2 = Long.parseLong(num.substring(start, start + len2));
                if (isAdditiveNumber(num, start + len2, num1, num2)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isAdditiveNumber(String num, int start, long num1, long num2) {
        if (start >= num.length()) {
            return false;
        }
        long sum = num1 + num2;
        String sumStr = String.valueOf(sum);
        if (start + sumStr.length() > num.length()) {
            return false;
        }
        if (sumStr.equals(num.substring(start, start + sumStr.length()))) {
            if (start + sumStr.length() >= num.length()) {
                return true;
            }
            return isAdditiveNumber(num, start + sumStr.length(), num2, sum);
        }

        return false;
    }

    /**
     * <a href="https://leetcode.cn/problems/n-queens/">51. N-Queens</a>
     * Input: n = 4
     * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
     * @param n 1 <= n <= 9
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        Set<Integer> cols = new HashSet<>();
        Set<Integer> leftCols = new HashSet<>();
        Set<Integer> rightCols = new HashSet<>();

        List<List<String>> result = new ArrayList<>();

        int[][] board = new int[n][n];
        solveNQueens(board, n, 0, cols, leftCols, rightCols, result);
        return result;
    }

    private void solveNQueens(int[][] board, int n, int row, Set<Integer> cols, Set<Integer> leftCols, Set<Integer> rightCols, List<List<String>> result) {
        if (row == n) {
            solveNQueensGenerateAns(board, result);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (cols.contains(col)) {
                continue;
            }
            if (leftCols.contains(col + row)) {
                continue;
            }
            if (rightCols.contains(col - row)) {
                continue;
            }
            board[row][col] = 1;

            cols.add(col);
            leftCols.add(col + row);
            rightCols.add(col - row);

            solveNQueens(board, n, row + 1, cols, leftCols, rightCols, result);

            board[row][col] = 0;
            cols.remove(col);
            leftCols.remove(col + row);
            rightCols.remove(col - row);
        }
    }

    private void solveNQueensGenerateAns(int[][] board, List<List<String>> result) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 1) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            list.add(sb.toString());
        }
        result.add(list);
    }


    /**
     * <a href="https://leetcode.cn/problems/24-game/">679. 24 Game</a>
     * You are given an integer array cards of length 4. You have four cards, each containing a number in the range [1, 9]. You should arrange the numbers on these cards in a mathematical expression using the operators ['+', '-', '*', '/'] and the parentheses '(' and ')' to get the value 24.
     *
     * You are restricted with the following rules:
     *
     * The division operator '/' represents real division, not integer division.
     * For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12.
     * Every operation done is between two numbers. In particular, we cannot use '-' as a unary operator.
     * For example, if cards = [1, 1, 1, 1], the expression "-1 - 1 - 1 - 1" is not allowed.
     * You cannot concatenate numbers together
     * For example, if cards = [1, 2, 1, 2], the expression "12 + 12" is not valid.
     * Return true if you can get such expression that evaluates to 24, and false otherwise.
     * @param cards cards.length == 4
     * 1 <= cards[i] <= 9
     * @return
     */
    public boolean judgePoint24(int[] cards) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < cards.length; i++) {
            list.add((double) cards[i]);
        }

        return judgePoint24(list);
    }

    public static final double epsilon = 0.00001;
    public boolean judgePoint24(List<Double> cards) {
        if (cards.size() == 1) {
            return Math.abs(cards.get(0) - 24) < epsilon;
        }

        for (int i = 0; i < cards.size() - 1; i++) {
            double a = cards.get(i);
            for (int j = i + 1; j < cards.size(); j++) {
                double b = cards.get(j);
                List<Double> copy = new ArrayList<>(cards);
                copy.remove(a);
                copy.remove(b);
                boolean flag;

                copy.add(a + b);
                flag = judgePoint24(copy);

                copy.set(copy.size() - 1, a - b);
                flag = flag || judgePoint24(copy);

                copy.set(copy.size() - 1, b - a);
                flag = flag || judgePoint24(copy);

                copy.set(copy.size() - 1, a * b);
                flag = flag || judgePoint24(copy);

                if (a != 0) {
                    copy.set(copy.size() - 1, b / a);
                    flag = flag || judgePoint24(copy);
                }

                if (b != 0) {
                    copy.set(copy.size() - 1, a / b);
                    flag = flag || judgePoint24(copy);
                }

                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

}
