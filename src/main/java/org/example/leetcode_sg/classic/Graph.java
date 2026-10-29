package org.example.leetcode_sg.classic;

import java.util.*;

public class Graph {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'X', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'O', 'O'}, {'X', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'X', 'X'}, {'X', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'O'}, {'O', 'X', 'X', 'X', 'O', 'X', 'O', 'X', 'O', 'X'}, {'X', 'X', 'O', 'X', 'X', 'O', 'O', 'X', 'X', 'X'}, {'O', 'X', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'O'}, {'O', 'X', 'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X', 'X', 'O', 'X', 'X', 'O', 'O'}, {'X', 'X', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'O'}};
        solve(board);
    }

    /**
     * <a href="https://leetcode.com/problems/evaluate-division/?envType=study-plan-v2&envId=top-interview-150">399. Evaluate Division</a>
     * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
     *
     * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
     *
     * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
     *
     * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
     *
     * Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.
     * @param equations 1 <= equations.length <= 20 equations = [["a","b"],["b","c"]],
     * @param values values = [2.0,3.0]
     * @param queries [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
     * @return [6.00000,0.50000,-1.00000,1.00000,-1.00000]
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            double value = values[i];

            Map<String, Double> firstMap = map.getOrDefault(equation.get(0), new HashMap<>());
            firstMap.put(equation.get(1), value);
            map.put(equation.get(0), firstMap);

            Map<String, Double> secondMap = map.getOrDefault(equation.get(1), new HashMap<>());
            secondMap.put(equation.get(0), value == 0 ? 0 : (1 / value));
            map.put(equation.get(1), secondMap);
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            result[i] = calcEquationDfs(query.get(0), query.get(1), map, new HashSet<>());
        }
        return result;
    }

    private double calcEquationDfs(String start, String end, Map<String, Map<String, Double>> map, HashSet<String> seen) {
        if (!map.containsKey(start)) {
            return -1;
        }

        if (Objects.equals(start, end)) {
            return 1;
        }

        Map<String, Double> mappings = map.get(start);
        if (mappings.containsKey(end)) {
            return mappings.get(end);
        }

        seen.add(start);

        for (Map.Entry<String, Double> entry : mappings.entrySet()) {
            if (seen.contains(entry.getKey())) {
                continue;
            }
            double remain = calcEquationDfs(entry.getKey(), end, map, seen);
            if (remain != -1) {
                return entry.getValue() * remain;
            }
        }
        return -1;
    }


    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * <a href="https://leetcode.com/problems/clone-graph/?envType=study-plan-v2&envId=top-interview-150">133. Clone Graph</a>
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Integer, Node> map = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            Node newNode;
            if (!map.containsKey(curNode.val)) {
                newNode = new Node(curNode.val);
                map.put(newNode.val, newNode);
            } else {
                newNode = map.get(curNode.val);
            }
            List<Node> newNeighbours = new ArrayList<>();
            for (Node neighbour : curNode.neighbors) {
                if (!map.containsKey(neighbour.val)) {
                    Node newNeighbour = new Node(neighbour.val);
                    map.put(neighbour.val, newNeighbour);
                    newNeighbours.add(newNeighbour);
                    queue.offer(neighbour);
                } else {
                    newNeighbours.add(map.get(neighbour.val));
                }
            }
            newNode.neighbors = newNeighbours;
        }

        return map.get(node.val);
    }

    /**
     * <a href="https://leetcode.com/problems/surrounded-regions/?envType=study-plan-v2&envId=top-interview-150">130. Surrounded Regions</a>
     * You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
     *
     * Connect: A cell is connected to adjacent cells horizontally or vertically.
     * Region: To form a region connect every 'O' cell.
     * Surround: A region is surrounded if none of the 'O' cells in that region are on the edge of the board. Such regions are completely enclosed by 'X' cells.
     * To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.
     * @param board
     */
    public static void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        if (m <= 2 || n <= 2) {
            return;
        }

        Set<String> seenSet = new HashSet<>();
        int[][] dirs = new int[][]{{0,1}, {0,-1}, {-1,0}, {1,0}};
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O' && !seenSet.contains(i + ",0")) {
                seenSet.add(i + ",0");
                queue.offer(new int[]{i, 0});
                solve(board, m, n, seenSet, dirs, queue, false);
            }
            if (board[i][n - 1] == 'O' && !seenSet.contains(i + "," + (n - 1))) {
                seenSet.add(i + "," + (n - 1));
                queue.offer(new int[]{i, n - 1});
                solve(board, m, n, seenSet, dirs, queue, false);
            }
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O' && !seenSet.contains("0," + j)) {
                seenSet.add("0," + j);
                queue.offer(new int[]{0, j});
                solve(board, m, n, seenSet, dirs, queue, false);
            }
            if (board[m - 1][j] == 'O' && !seenSet.contains((m - 1) + "," + j)) {
                seenSet.add((m - 1) + "," + j);
                queue.offer(new int[]{m - 1, j});
                solve(board, m, n, seenSet, dirs, queue, false);
            }
        }

        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O' && !seenSet.contains(i + "," + j)) {
                    queue.offer(new int[]{i, j});
                    seenSet.add(i + "," + j);
                    solve(board, m, n, seenSet, dirs, queue, true);
                }
            }
        }
    }

    private static void solve(char[][] board, int m, int n, Set<String> seenSet, int[][] dirs, Queue<int[]> queue, boolean modify) {
        int newI, newJ;
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            if (modify) {
                board[point[0]][point[1]] = 'X';
            }
            for (int[] dir : dirs) {
                newI = point[0] + dir[0];
                newJ = point[1] + dir[1];

                if (newI < 0 || newI >= m || newJ < 0 || newJ >= n) {
                    continue;
                }

                if (board[newI][newJ] == 'O' && !seenSet.contains(newI + "," + newJ)) {
                    queue.offer(new int[]{newI, newJ});
                    seenSet.add(newI + "," + newJ);
                }
            }
        }
    }


    /**
     * <a href="https://leetcode.com/problems/number-of-islands/description/?envType=study-plan-v2&envId=top-interview-150">200. Number of Islands</a>
     * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
     *
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
     * @param grid 1 <= m, n <= 300
     * @return
     */
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int result = 0;

        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i][j];
                if (c != '1') {
                    continue;
                }

                int newI, newJ;
                queue.offer(new int[] {i, j});
                grid[i][j] = '2';
                while (!queue.isEmpty()) {
                    int[] point = queue.poll();
                    for (int[] dir : dirs) {
                        newI = dir[0] + point[0];
                        newJ = dir[1] + point[1];
                        if (newI < 0 || newI >= m || newJ < 0 || newJ >= n) {
                            continue;
                        }
                        if (grid[newI][newJ] == '1') {
                            grid[newI][newJ] = '2';
                            queue.offer(new int[] {newI, newJ});
                        }
                    }
                }
                result++;
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/path-with-minimum-effort/?utm_source=chatgpt.com">1631. Path With Minimum Effort</a>
     * @param heights represents the height of cell (row, col)
     * @return
     *
     * time complexity: O(mn log(mn)), space complexity: O(mn)
     */
    public static int minimumEffortPath(int[][] heights) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        queue.offer(new int[]{0, 0, 0});
        int[][] dirs = new int[][]{{0,1}, {0,-1}, {-1,0}, {1,0}};
        int[][] seenBest = new int[heights.length][heights[0].length];
        for (int[] row : seenBest) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        while (!queue.isEmpty()) {
            int[] item = queue.poll();
            if (item[0] >= seenBest[item[1]][item[2]]) {
                continue;
            }
            seenBest[item[1]][item[2]] = item[0];

            if (item[1] == heights.length - 1 && item[2] == heights[0].length - 1) {
                return item[0];
            }
            for (int[] dir : dirs) {
                int newI = dir[0] + item[1];
                int newJ = dir[1] + item[2];
                if (newI < 0 || newI >= heights.length || newJ < 0 || newJ >= heights[newI].length) {
                    continue;
                }
                int curEffort = Math.max(Math.abs(heights[newI][newJ] - heights[item[1]][item[2]]), item[0]);
                if (curEffort >= seenBest[newI][newJ]) {
                    continue;
                }
                queue.offer(new int[]{curEffort, newI, newJ});
            }
        }

        return seenBest[heights.length - 1][heights[0].length - 1];
    }

}
