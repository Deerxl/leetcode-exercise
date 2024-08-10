package org.example.leetcode.common;

/**
 * @author jialu.yxl
 * @date 02/02/2023 14:49
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
