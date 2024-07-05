package org.example.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jialu.yxl
 * @date 6/4/24 3:02 PM
 */
public class BackTracing {


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
