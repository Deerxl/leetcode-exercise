package org.example.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author jialu.yxl
 * @date 08/03/2023 17:11
 */
@Slf4j
public class Matrix {

    public static void main(String[] args) {
        char[][] nums = new char[][]{
                {'0','1','1','0','0','1','0','1','0','1'},
                {'0','0','1','0','1','0','1','0','1','0'},
                {'1','0','0','0','0','1','0','1','1','0'},
                {'0','1','1','1','1','1','1','0','1','0'},
                {'0','0','1','1','1','1','1','1','1','0'},
                {'1','1','0','1','0','1','1','1','1','0'},
                {'0','0','0','1','1','0','0','0','1','0'},
                {'1','1','0','1','1','0','0','1','1','1'},
                {'0','1','0','1','1','0','1','0','1','1'}}
                ;

        char[][] board = new char[][] {
                {'A','B','C','E'},
                {'S','F','E','S'},
                {'A','D','E','E'}
        };

        int[][] nums1 = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        System.out.println(Arrays.deepToString(generateMatrix(3)));

    }

    /**
     * <a href="https://leetcode.cn/problems/spiral-matrix-ii/">59. Spiral Matrix II</a>
     * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
     * Input: n = 3
     * Output: [[1,2,3],[8,9,4],[7,6,5]]
     * @param n 1 <= n <= 20
     * @return
     */
    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int index = 1;

        for (int i = 0; i < (n + 1) / 2; i++) {
            int row = i;
            int col = i;
            while (col <= n - i - 1) {
                result[row][col++] = index++;
            }
            col--;
            row++;
            while (row <= n - i - 1) {
                result[row++][col] = index++;
            }
            row--;
            col--;
            while (col >= i) {
                result[row][col--] = index++;
            }
            col++;
            row--;
            while (row > i) {
                result[row--][col] = index++;
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/search-a-2d-matrix/">74. Search a 2D Matrix</a>
     * You are given an m x n integer matrix matrix with the following two properties:
     * Each row is sorted in non-decreasing order.
     * The first integer of each row is greater than the last integer of the previous row.
     * Given an integer target, return true if target is in matrix or false otherwise.
     * You must write a solution in O(log(m * n)) time complexity.
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;

        int row = searchMatrixRow(matrix, target, 0, m - 1);
        if (row == -1) {
            return false;
        }
        return searchMatrixCol(matrix, target, row, 0, n - 1);
    }

    private boolean searchMatrixCol(int[][] matrix, int target, int row, int startCol, int endCol) {
        if (startCol > endCol) {
            return false;
        }
        int mid = (startCol + endCol) / 2;
        if (matrix[row][mid] == target) {
            return true;
        }

        if (matrix[row][mid] > target) {
            return searchMatrixCol(matrix, target, row, startCol, mid - 1);
        } else {
            return searchMatrixCol(matrix, target, row, mid + 1, endCol);
        }
    }

    private int searchMatrixRow(int[][] matrix, int target, int startRow, int endRow) {
        if (startRow > endRow) {
            return -1;
        }

        int mid = (startRow + endRow) / 2;
        if (matrix[mid][0] > target) {
            return searchMatrixRow(matrix, target, startRow, mid - 1);
        } else if (matrix[mid][0] == target) {
            return mid;
        } else {
            if (mid == matrix.length - 1 || matrix[mid + 1][0] > target) {
                return mid;
            } else {
                return searchMatrixRow(matrix, target, mid + 1, endRow);
            }
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/word-search/">79. Word Search</a>
     * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
     * The word can be constructed from letters of sequentially adjacent cells,
     * where adjacent cells are horizontally or vertically neighboring.
     * The same letter cell may not be used more than once.
     * @param board
     * @param word
     * @return
     */
    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        if (word.length() > m * n) {
            return false;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] visit = new boolean[m][n];
                    if (existDfs(board, i, j, visit, word, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean existDfs(char[][] board, int i, int j, boolean[][] visit, String word, int index) {
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }

        if (visit[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }

        visit[i][j] = true;
        index++;

        int[][] dirs = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};
        for (int[] dir : dirs) {
            boolean[][] newVisit = Arrays.copyOf(visit, visit.length);
            if (existDfs(board, i + dir[0], j + dir[1], newVisit, word, index)) {
                return true;
            }
        }
        visit[i][j] = false;

        return false;
    }

    /**
     * <a href="https://leetcode.cn/problems/diagonal-traverse/">498. Diagonal Traverse</a>
     * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
     * @param mat
     * @return
     */
    public static int[] findDiagonalOrder(int[][] mat) {
        if (mat.length == 0 || mat[0].length == 0) {
            return new int[]{};
        }
        int m = mat.length, n = mat[0].length;
        int[] result = new int[m * n];
        int index = 0;

        int i = 0, j = 0;
        while (i >= 0 && i < m && j >= 0 && j < n) {
            while (i >= 0 && j < n) {
                result[index++] = mat[i][j];
                i--;
                j++;
            }

            i++;
            if (j >= n) {
                j--;
                i++;
            }

            while (i < m && j >= 0) {
                result[index++] = mat[i][j];
                i++;
                j--;
            }

            j++;
            if (i >= m) {
                i--;
                j++;
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/max-area-of-island/">695. 岛屿的最大面积</a>
     * 给你一个大小为 m x n 的二进制矩阵 grid 。
     * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
     * 岛屿的面积是岛上值为 1 的单元格的数目。
     * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
     * @param grid
     * @return
     */
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int result = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    maxAreaOfIsland(grid, i, j, m, n);
                    result = Math.max(result, maxAreaOfIslandResult);
                    maxAreaOfIslandResult = 0;
                }
            }
        }

        return result;
    }

    static int maxAreaOfIslandResult = 0;
    private static void maxAreaOfIsland(int[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        if (grid[i][j] == 1) {
            maxAreaOfIslandResult++;
            grid[i][j] = 2;

            maxAreaOfIsland(grid, i + 1, j, m , n);
            maxAreaOfIsland(grid, i - 1, j, m , n);
            maxAreaOfIsland(grid, i, j + 1, m , n);
            maxAreaOfIsland(grid, i, j - 1, m , n);
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/maximal-square/">221. 最大正方形</a>
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }

        int m = matrix.length, n = matrix[0].length;
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int maxLen = Math.min(m - i, n - j);
                if (maxLen * maxLen < result) {
                    break;
                }

                if (matrix[i][j] == '0') {
                    continue;
                }

                result = maximalSquare(matrix, i, j, maxLen, result);
                System.out.println("i: " + i + ", j: " + j + ", result: " + result);
            }
        }
        return result;
    }

    private static int maximalSquare(char[][] matrix, int i, int j, int maxLen, int result) {
        int tmpResult = 1;
        for (int k = 1; k < maxLen; k++) {
            for (int row = i; row <= i + k; row++) {
                if (matrix[row][j + k] != '1') {
                    return Math.max(result, tmpResult);
                }
            }
            for (int col = j; col <= j + k; col++) {
                if (matrix[i + k][col] != '1') {
                    return Math.max(result, tmpResult);
                }
            }
            tmpResult = (k + 1) * (k + 1);
        }

        return Math.max(result, tmpResult);
    }

    /**
     * <a href="https://leetcode.cn/problems/search-a-2d-matrix-ii/">240. 搜索二维矩阵 II</a>
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix240(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int j;
        for (j = 0; j < n; j++) {
            if (matrix[0][j] == target) {
                return true;
            } else if (matrix[0][j] > target) {
                break;
            }
        }

        j--;
        if (j < 0) {
            return false;
        }

        int i = 0;
        while (true) {
            while (i < m && matrix[i][j] < target) {
                i++;
            }
            if (i >= m) {
                break;
            }
            if (matrix[i][j] == target) {
                return true;
            }
            while (j >= 0 && matrix[i][j] > target) {
                j--;
            }
            if (j < 0) {
                break;
            }
            if (matrix[i][j] == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * <a href="https://leetcode.cn/problems/minimum-path-sum/">64. 最小路径和</a>
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * <a href="https://leetcode.cn/problems/rotate-image/">48. 旋转图像</a>
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/spiral-matrix/">54. 螺旋矩阵</a>
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int minRow = 0, maxRow = matrix.length - 1;
        int minCol = 0, maxCol = matrix[0].length - 1;

        for (int i = 0; i < matrix.length; i++) {
            minRow = i;
            minCol = i;

            if (minRow > maxRow || minCol > maxCol || minCol >= matrix[0].length) {
                break;
            }

            for (int col = minCol; col <= maxCol; col++) {
                result.add(matrix[minRow][col]);
            }

            minRow++;
            if (minRow > maxRow) {
                break;
            }

            for (int row = minRow; row <= maxRow; row++) {
                result.add(matrix[row][maxCol]);
            }
            maxCol--;
            if (minCol > maxCol) {
                break;
            }

            for (int col = maxCol; col >= minCol; col--) {
                result.add(matrix[maxRow][col]);
            }
            maxRow--;
            if (minRow > maxRow) {
                break;
            }

            for (int row = maxRow; row >= minRow; row--) {
                result.add(matrix[row][minCol]);
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/description/">剑指 Offer 04. 二维数组中的查找</a>
     * @param matrix 在一个 n * m 的二维数组中，每一行都按照从左到右 非递减 的顺序排序，每一列都按照从上到下 非递减 的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int col = matrix[0].length - 1;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == target) {
                return true;
            }
            if (matrix[0][i] > target) {
                col = i - 1;
                break;
            }
        }

        int row = 1;
        while (col >= 0 && row < matrix.length) {
            if (matrix[row][col] == target) {
                return true;
            }

            while (row + 1 < matrix.length && matrix[row + 1][col] <= target) {
                row++;
                if (matrix[row][col] == target) {
                    return true;
                }
            }

            col--;
        }


        return false;
    }

    /**
     * <a href="https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/">329. 矩阵中的最长递增路径</a>
     * @param matrix
     * @return
     */
    public static int longestIncreasingPath(int[][] matrix) {
        int[][] memo = new int[matrix.length][matrix[0].length];
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result = Math.max(result, longestIncreasingPath(matrix, i, j, memo));
            }
        }

        return result;
    }

    private static int longestIncreasingPath(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        memo[i][j] = 1;
        int[][] dirs  = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};

        for (int[] dir : dirs) {
            int newRow = dir[0] + i;
            int newCol = dir[1] + j;
            if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix[0].length) {
                if (matrix[i][j] < matrix[newRow][newCol]) {
                    memo[i][j] = Math.max(memo[i][j], longestIncreasingPath(matrix, newRow, newCol, memo) + 1);
                }
            }
        }
        return memo[i][j];
    }
}
