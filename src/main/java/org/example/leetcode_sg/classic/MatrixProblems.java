package org.example.leetcode_sg.classic;

import java.util.*;

public class MatrixProblems {

    /**
     * <a href="https://leetcode.com/problems/isomorphic-strings/?envType=study-plan-v2&envId=top-interview-150">205. Isomorphic Strings</a>
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) {
                    return false;
                }
            } else if (map.containsValue(c2)) {
                return false;
            } else {
                map.put(c1, c2);
            }
        }
        return true;
    }

    /**
     * <a href="https://leetcode.com/problems/game-of-life/?envType=study-plan-v2&envId=top-interview-150">289. Game of Life</a>
     * @param board
     */
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = board[i][j];
                int neighbourLives = getNeighbourLives(board, i, j, m, n);
                if (val == 1) {
                    if (neighbourLives < 2 || neighbourLives > 3) {
                        board[i][j] = 1;
                    } else if (neighbourLives == 2 || neighbourLives == 3) {
                        board[i][j] = 3;
                    }
                } else if (val == 0) {
                    if (neighbourLives == 3) {
                        board[i][j] = 2;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] >> 1;
            }
        }
    }

    int getNeighbourLives(int[][] board, int i, int j, int m, int n) {
        int lives = 0;
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] dir : dirs) {
            int newI = i + dir[0];
            int newJ = j + dir[1];

            if (newI < 0 || newI >= m || newJ < 0 || newJ >= n) {
                continue;
            }

            if ((newI <= i && newJ <= j) || newI < i) {
                if (board[newI][newJ] == 1 || board[newI][newJ] == 3) {
                    lives += 1;
                }
            } else {
                if (board[newI][newJ] == 1) {
                    lives += 1;
                }
            }
        }
        return lives;
    }

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
