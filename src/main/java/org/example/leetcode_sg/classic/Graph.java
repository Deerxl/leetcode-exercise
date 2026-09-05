package org.example.leetcode_sg.classic;

import java.util.*;

public class Graph {

    public static void main(String[] args) {
        int[][] heights = new int[][]{
                {1, 2, 2},{3, 8, 2},{5, 3, 5}
        };
        System.out.println(minimumEffortPath(heights));
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
