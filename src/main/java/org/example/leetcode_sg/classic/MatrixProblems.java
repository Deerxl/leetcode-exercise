package org.example.leetcode_sg.classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixProblems {


    /**
     * <a href="https://leetcode.com/problems/set-matrix-zeroes/?envType=study-plan-v2&envId=top-interview-150">73. Set Matrix Zeroes</a>
     * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
     *
     * You must do it in place.
     * @param matrix m == matrix.length
     * n == matrix[0].length
     * 1 <= m, n <= 200
     * -231 <= matrix[i][j] <= 231 - 1
     */
    public void setZeroes(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColZero = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRowZero = true;
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRowZero) {
            Arrays.fill(matrix[0], 0);
        }

        if (firstColZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * <a href="https://leetcode.com/problems/rotate-image/?envType=study-plan-v2&envId=top-interview-150">48. Rotate Image</a>
     * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
     *
     * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int start = 0; start * 2 < n; start++) {
            for (int startCol = start; startCol < n - start - 1; startCol++) {
                int curRow = start, curCol = startCol, curVal = matrix[curRow][curCol];
                int nextRow, nextCol, nextVal;
                for (int i = 0; i < 4; i++) {
                    nextRow = curCol;
                    nextCol = n - curRow - 1;
                    nextVal = matrix[nextRow][nextCol];

                    matrix[nextRow][nextCol] = curVal;

                    curRow = nextRow;
                    curCol = nextCol;
                    curVal = nextVal;
                }
            }
        }
    }

    /**
     * <a href="https://leetcode.com/problems/spiral-matrix/?envType=study-plan-v2&envId=top-interview-150">54. Spiral Matrix</a>
     * @param matrix m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 10
     * @return Given an m x n matrix, return all elements of the matrix in spiral order.
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        for (int start = 0; start * 2 < m && start * 2 < n; start++) {
            int row = start;
            int col = start;

            for (; col < n - start; col++) {
                result.add(matrix[row][col]);
            }

            row++;
            col--;
            if (row >= m - start) {
                break;
            }
            for(; row < m - start; row++) {
                result.add(matrix[row][col]);
            }

            row--;
            col--;
            if (col < start) {
                break;
            }
            for(; col >= start; col--) {
                result.add(matrix[row][col]);
            }

            row--;
            col++;
            if (row <= start) {
                break;
            }
            for(; row > start; row--) {
                result.add(matrix[row][col]);
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/valid-sudoku/?envType=study-plan-v2&envId=top-interview-150">36. Valid Sudoku</a>
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] subBox = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int num = board[i][j] - '1';
                int subBoxRow = (i / 3) * 3 + (j / 3);
                if (row[i][num] || col[num][j] || subBox[subBoxRow][num]) {
                    return false;
                }
                row[i][num] = true;
                col[num][j] = true;
                subBox[subBoxRow][num] = true;
            }
        }

        return true;
    }
}
