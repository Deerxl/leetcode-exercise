package org.example.leetcode_sg.classic;

import java.util.*;

public class query {

    /**
     * <a href="https://leetcode.com/problems/meeting-rooms-iii/">2402. Meeting Rooms III</a>
     *
     * @param n        n rooms numbered from 0 to n - 1
     * @param meetings 2D integer array meetings where meetings[i] = [starti, endi]
     * @return the number of the room that held the most meetings. If there are multiple rooms, return the room with the lowest number.
     * <p>
     * time complexity: O(m log m + m n log n);  space complexity: O(n + log m)
     * the better way is using two priority queue, one is for used rooms, another is for unused rooms.
     */
    public static int mostBooked(int n, int[][] meetings) {
        if (n == 1 || meetings.length == 1) {
            return 0;
        }

        Arrays.sort(meetings, ((o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        }));

        Queue<long[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] != o2[0]) {
                return Math.toIntExact(o1[0] - o2[0]);
            }
            return Math.toIntExact(o1[1] - o2[1]);
        });
        int[] resultRecord = new int[n];

        for (int[] meeting : meetings) {
            if (queue.isEmpty()) {
                queue.offer(new long[]{meeting[1], 0});
                resultRecord[0] = 1;
            } else if (queue.peek()[0] <= meeting[0]) {
                long minRoomNum = queue.peek()[1];
                List<long[]> pollList = new ArrayList<>();
                while (!queue.isEmpty() && queue.peek()[0] <= meeting[0]) {
                    if (queue.peek()[1] < minRoomNum) {
                        minRoomNum = queue.peek()[1];
                    }
                    pollList.add(queue.poll());
                }
                for (long[] item : pollList) {
                    if (item[1] != minRoomNum) {
                        queue.offer(item);
                    }
                }
                queue.offer(new long[]{meeting[1], minRoomNum});
                resultRecord[Math.toIntExact(minRoomNum)] = resultRecord[Math.toIntExact(minRoomNum)] + 1;
            } else if (queue.size() < n) {
                resultRecord[queue.size()] = 1;
                queue.offer(new long[]{meeting[1], queue.size()});
            } else {
                long[] item = queue.poll();
                item[0] += (meeting[1] - meeting[0]);
                queue.offer(item);
                resultRecord[Math.toIntExact(item[1])] = resultRecord[Math.toIntExact(item[1])] + 1;
            }
        }

        int result = -1;
        int maxRoomCount = Integer.MIN_VALUE;
        for (int i = 0; i < resultRecord.length; i++) {
            if (resultRecord[i] > maxRoomCount) {
                result = i;
                maxRoomCount = resultRecord[i];
            }
        }

        return result;
    }
}
