package org.example.leetcode.other;

import java.util.*;

/**
 * @author xiaolu
 * @date 2024/10/10 08:57
 */
public class InterviewQuestions {

    public static void main(String[] args) {
        char[][] pairs = new char[][] {
                {'A', 'B'},
                {'C', 'D'},
                {'B', 'C'},
                {'E', 'F'},
                {'G', 'H'},
                {'D', 'E'}
        };
        System.out.println(isDeadLock(pairs));
    }


    /**
     * tiktok-2024/10/16 会议室问题，求最小的会议室数量
     * @param intervals
     * @return
     */
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 1) {
            return 1;
        }

        int[][] pairs = Arrays.stream(intervals)
                .sorted(new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[0] - o2[0];
                    }
                })
                .toArray(int[][]::new);

        int result = 1;
        Queue<Integer> endTimeQueue = new PriorityQueue<>();
        endTimeQueue.offer(pairs[0][1]);

        for (int i = 1; i < pairs.length; i++) {
            int startTime = pairs[i][0];
            int endTime = pairs[i][1];

            if (endTimeQueue.peek() < startTime) {
                endTimeQueue.poll();
            } else {
                result++;
            }
            endTimeQueue.offer(endTime);
        }

        return result;
    }

    /**
     * tiktok-2024/10/09
     * 给定一个二维数组，每个数组里有两个元素，例如['A', 'B']表示A依赖于B，判定是否会造成死锁
     * @param pairs 元素只包含A...Z
     * @return 判断是否会造成死锁
     */
    public static boolean isDeadLock(char[][] pairs) {
        int totalSize = 26;
        if (pairs == null || pairs.length == 0) {
            return false;
        }

        // 输入没有限制，可能从中间某个字母开始，也可能不连续
        Map<Integer, List<Integer>> outDegreeMap = new HashMap<>();
        int[] inDegreeArr = new int[totalSize];

        // 记录每个点的出度数据，以及入度值
        for (char[] pair : pairs) {
            int request = pair[0] - 'A';
            int resource = pair[1] - 'A';

            List<Integer> outDegreeList = outDegreeMap.getOrDefault(resource, new ArrayList<>());
            outDegreeList.add(request);
            outDegreeMap.put(resource, outDegreeList);

            inDegreeArr[request]++;
        }

        // 用queue存储入度为0的点
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inDegreeArr.length; i++) {
            if (inDegreeArr[i] == 0) {
                queue.add(i);
            }
        }

        // 存储度为0的点
        List<Integer> noLockList = new ArrayList<>();

        // queue元素依次出队列，并清理该点a相关的出度b，并将b点的入度值减1
        while (!queue.isEmpty()) {
            int node = queue.poll();
            noLockList.add(node);
            List<Integer> outDegreeList = outDegreeMap.getOrDefault(node, new ArrayList<>());
            for (int i : outDegreeList) {
                inDegreeArr[i]--;
                if (inDegreeArr[i] == 0) {
                    queue.offer(i);
                }
            }
        }

        return noLockList.size() != totalSize;
    }
}
