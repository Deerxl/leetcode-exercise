package org.example.leetcode.other;

import java.util.*;

/**
 * @author xiaolu
 * @date 2024/10/10 08:57
 */
public class InterviewQuestions {

    public static void main(String[] args) {
        int[][] points = new int[][] {
                {1,1},
                {2,2},
                {3,3}
        };

        System.out.println(maxPoints(points));
    }

    /**
     * 滴滴-2024-11-04
     * 二维地图上有一堆坐标点， 找到在共线最多的点数
     * 输入: points[] = {-1, 1},  {0, 0}, {1,3}, {1, 1}, {2,6} , {4,12} , {2, 2}, {3, 3}, {3, 4}，{6,6}
     * 输出 : 5
     * @param points
     * @return
     */
    public static int maxPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        if (points.length <= 2) {
            return points.length;
        }

        int result = 2;
        for (int i = 0; i < points.length - 1; i++) {
            int[] point1 = points[i];
            for (int j = i + 1; j < points.length; j++) {
                int[] point2 = points[j];

                int tmpResult = 2;
                for (int k = j + 1; k < points.length; k++) {
                    int[] point3 = points[k];

                    // 判断点3和点1、点3和点2形成的斜率是否相同
                    // k = (y3 - y1) / (x3 - x1) == (y3 - y2) / (x3 - x2)
                    // 即判断 (y3 - y1) * (x3 - x2) == (x3 - x1) * (y3 - y2)
                    int k1 = (point3[1] - point1[1]) * (point3[0] - point2[0]);
                    int k2 = (point3[0] - point1[0]) * (point3[1] - point2[1]);

                    if (k1 == k2) {
                        tmpResult++;
                    }
                }

                result = Math.max(result, tmpResult);
            }
        }

        return result;
    }


    /**
     * 希音-2024-11-05
     * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
     * 示例 1 :
     * 输入: 2736
     * 输出: 7236
     * 解释: 交换数字2和数字7。
     * 示例 2 :
     * 输入: 9973
     * 输出: 9973
     * 解释: 不需要交换。
     * @param num
     * @return
     */
    public static int getMaxNum(int num) {
        String str = String.valueOf(num);
        if (str.length() == 1) {
            return num;
        }

        for (int i = 0; i < str.length() - 1; i++) {
            int tmpMax = Integer.parseInt(String.valueOf(str.charAt(i)));
            int curIndex = i;
            for (int j = i + 1; j < str.length(); j++) {
                int curNum = Integer.parseInt(String.valueOf(str.charAt(j)));
                if (tmpMax <= curNum) {
                    tmpMax = curNum;
                    if (curIndex < j) {
                        curIndex = j;
                    }
                }
            }

            // 交换 i 和 curIndex 的数字
            // 0..curIndex...i...len
            if (curIndex > i) {
                StringBuilder sb = new StringBuilder();
                sb.append(str, 0, i);
                sb.append(str.charAt(curIndex));
                sb.append(str, i + 1, curIndex);
                sb.append(str.charAt(i));
                sb.append(str, curIndex + 1, str.length());
                return Integer.parseInt(sb.toString());
            }
        }

        return num;
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
