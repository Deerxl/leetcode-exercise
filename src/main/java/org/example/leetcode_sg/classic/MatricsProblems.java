package org.example.leetcode_sg.classic;

public class MatricsProblems {

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
