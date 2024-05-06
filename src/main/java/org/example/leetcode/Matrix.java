package org.example.leetcode;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jialu.yxl
 * @date 08/03/2023 17:11
 */
public class Matrix {

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        rotate(nums);
        // System.out.println();

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
                matrix[i][j] = matrix[j][n - 1 - j];
                matrix[j][n - 1 - j] = matrix[j][j];
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
