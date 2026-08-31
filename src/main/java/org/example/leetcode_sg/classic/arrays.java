package org.example.leetcode_sg.classic;

import org.example.leetcode_sg.common.Interval;

import java.util.*;

public class arrays {

    public static void main(String[] args) {
        System.out.println(mostBooked(3, new int[][]{{3, 7},{12, 19},{16, 17},{1, 17},{5, 6}}));
    }

    /**
     * <a href="https://neetcode.io/problems/meeting-schedule/question">252. Meeting Rooms</a>
     * time complexity: O(NlogN); space complexity: O(N)
     * @param intervals
     * @return
     */
    public static boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals.size() <= 1) {
            return true;
        }
        intervals.sort(Comparator.comparingInt(o -> o.start));
        int preRight = Integer.MIN_VALUE;
        for (Interval interval : intervals) {
            if (interval.start < preRight) {
                return false;
            }
            preRight = interval.end;
        }
        return true;
    }


    /**
     * <a href="https://neetcode.io/problems/meeting-schedule-ii/question">253. Meeting Rooms II</a>
     * Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), find the minimum number of rooms required to schedule all meetings without any conflicts.
     *
     * Note: (0,8),(8,10) is NOT considered a conflict at 8.
     *
     * time complexity: O(NlogN), space complexity: O(N)
     */
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals.size() <= 1) {
            return intervals.size();
        }
        int result = 0;

        intervals.sort((o1, o2) -> {
            if (o1.start != o2.start) {
                return o1.start - o2.start;
            }
            return o1.end - o2.end;
        });

        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        for (Interval interval : intervals) {
            if (queue.isEmpty() || queue.peek() > interval.start) {
                result += 1;
            } else {
                queue.poll();
            }
            queue.offer(interval.end);
        }

        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/meeting-rooms-iii/">2402. Meeting Rooms III</a>
     * @param n n rooms numbered from 0 to n - 1
     * @param meetings 2D integer array meetings where meetings[i] = [starti, endi]
     * @return the number of the room that held the most meetings. If there are multiple rooms, return the room with the lowest number.
     *
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


    /**
     * <a href="https://leetcode.com/problems/merge-intervals/">56. Merge Intervals</a>
     * time complexity: O(NlogN), space complexity: O(N)
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> resultList = new ArrayList<>();
        int[] lastInterval = intervals[0];
        for (int[] interval : intervals) {
            if (interval[0] > lastInterval[1]) {
                resultList.add(lastInterval);
                lastInterval = interval;
            } else {
                lastInterval[1] = Math.max(lastInterval[1], interval[1]);
            }
        }
        resultList.add(lastInterval);

        return resultList.toArray(new int[resultList.size()][2]);
    }




}
