package org.example.leetcode.classification;

import java.util.*;

/**
 * @author jialu.yxl
 * @date 5/13/24 4:12 PM
 */
public class Graphs {

    public static void main(String[] args) {
        int[][] nums = new int[][] {
                {1,0}
        };

        char[][] board = new char[][] {
                {'X','X','X','X'},
                {'X','O','X','X'},
                {'X','O','O','X'},
                {'X','O','X','X'}
        };

        solve(board);
        System.out.println(Arrays.deepToString(board));
    }

    /**
     * <a href="https://leetcode.cn/problems/surrounded-regions/">130. Surrounded Regions</a>
     * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
     *
     * Connect: A cell is connected to adjacent cells horizontally or vertically.
     * Region: To form a region connect every 'O' cell.
     * Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
     * A surrounded region is captured by replacing all 'O's with 'X's in the input matrix board.
     * @param board
     */
    public static void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        if (m == 1 || n <= 1) {
            return;
        }
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                checkMarginO(board, m, n, i, 0, visited);
            }
            if (board[i][n - 1] == 'O') {
                checkMarginO(board, m, n, i, n - 1, visited);
            }
        }
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                checkMarginO(board, m, n, 0, i, visited);
            }
            if (board[m - 1][i] == 'O') {
                checkMarginO(board, m, n, m - 1, i, visited);
            }
        }

        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    fillSurroundedX(board, m, n, i, j);
                }
            }
        }
    }

    private static void fillSurroundedX(char[][] board, int m, int n, int i, int j) {
        if (i <= 0 || i >= m - 1 || j <= 0 || j >= n - 1) {
            return;
        }

        board[i][j] = 'X';

        int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x <= 0 || x >= m - 1 || y <= 0 || y >= n - 1) {
                continue;
            }
            if (board[x][y] == 'O') {
                fillSurroundedX(board, m, n, x, y);
            }
        }
    }

    private static void checkMarginO(char[][] board, int m, int n, int i, int j, boolean[][] visited) {
        if (visited[i][j]) {
            return;
        }

        visited[i][j] = true;

        int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n) {
                continue;
            }
            if (board[x][y] == 'O' && !visited[x][y]) {
                checkMarginO(board, m, n, x, y, visited);
            }
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/course-schedule-ii/">210. Course Schedule II</a>
     * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
     *
     * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
     * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
     * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
     * Output: [0,2,1,3]
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];

        int[] inDegree = new int[numCourses];

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] pre: prerequisites) {
            List<Integer> inList = map.getOrDefault(pre[1], new ArrayList<>());
            inList.add(pre[0]);
            map.put(pre[1], inList);

            inDegree[pre[0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int index = 0;
        while (!queue.isEmpty()) {
            Integer course = queue.poll();
            result[index++] = course;

            List<Integer> inList = map.getOrDefault(course, new ArrayList<>());
            for (Integer in: inList) {
                inDegree[in]--;
                if (inDegree[in] == 0) {
                    queue.offer(in);
                }
            }
        }

        return index == numCourses ? result : new int[]{};
    }

    /**
     * <a href="https://leetcode.cn/problems/course-schedule/">207. Course Schedule</a>
     * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
     * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
     * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
     * Return true if you can finish all courses. Otherwise, return false.
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> preMap = new HashMap<>();
        int[] inDegrees = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> value = preMap.getOrDefault(prerequisites[i][0], new ArrayList<>());
            value.add(prerequisites[i][1]);
            preMap.put(prerequisites[i][0], value);

            inDegrees[prerequisites[i][1]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer course = queue.poll();
            numCourses--;
            List<Integer> dependCourses = preMap.getOrDefault(course, new ArrayList<>());
            for (Integer c : dependCourses) {
                inDegrees[c]--;
                if (inDegrees[c] == 0) {
                    queue.offer(c);
                }
            }
        }
        return numCourses == 0;
    }
}
