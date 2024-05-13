package org.example.leetcode;

/**
 * @author jialu.yxl
 * @date 02/02/2023 14:49
 */
class Node {
    public int val;
    public Node left;
    public Node right;

    Node next;
    Node random;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
