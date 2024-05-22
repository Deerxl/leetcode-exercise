package org.example.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/find-median-from-data-stream/description/">295. Find Median from Data Stream</a>
 * The median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far.
 * Answers within 10-5 of the actual answer will be accepted.
 * @author jialu.yxl
 * @date 5/22/24 5:24 PM
 */
public class MedianFinder {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }

    List<Integer> list;

    public MedianFinder() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        if (list.size() == 0) {
            list.add(num);
            return;
        }
        int index = binarySearch(num, 0, list.size() - 1);
        list.add(index, num);
    }

    private int binarySearch(int num, int start, int end) {
        if (start >= list.size() || list.get(start) > num) {
            return start;
        } else if (start >= end) {
            return start + 1;
        }

        int mid = start + (end - start) / 2;
        if (list.get(mid) == num) {
            return mid + 1;
        } else if (list.get(mid) > num) {
            return binarySearch(num, start, mid - 1);
        } else {
            return binarySearch(num, mid + 1, end);
        }
    }

    public double findMedian() {
        if (list.size() % 2 == 0) {
            int mid = list.size() / 2;
            return (double) (list.get(mid) + list.get(mid - 1)) / 2;
        } else {
            return list.get(list.size() / 2);
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
