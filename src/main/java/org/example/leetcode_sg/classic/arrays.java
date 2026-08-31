package org.example.leetcode_sg.classic;

import org.example.leetcode_sg.common.Interval;

import java.util.*;

public class arrays {

    public static void main(String[] args) {
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
