package org.example.leetcode;

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
        System.out.println(canFinish(2, nums));
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
