package org.example.leetcode.design;

/**
 *
 * <a href="https://leetcode.cn/problems/design-circular-queue/description/">622. Design Circular Queue</a>
 *
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle, and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
 *
 * Implement the MyCircularQueue class:
 *
 * MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * boolean isEmpty() Checks whether the circular queue is empty or not.
 * boolean isFull() Checks whether the circular queue is full or not.
 * You must solve the problem without using the built-in queue data structure in your programming language.
 *
 * @author jialu.yxl
 * @date 7/5/24 4:59 PM
 */
public class MyCircularQueue {

    int capacity;
    int size;
    Node head;
    Node tail;

    public MyCircularQueue(int k) {
        this.size = 0;
        this.capacity = k;
        this.head = new Node(0);
        this.tail = new Node(0);
        head.next = tail;
        tail.pre = head;
    }

    public boolean enQueue(int value) {
        if (capacity == size) {
            return false;
        }

        Node newNode = new Node(value);
        tail.pre.next = newNode;
        newNode.pre = tail.pre;
        tail.pre = newNode;
        newNode.next = tail;

        size++;
        return true;
    }

    public boolean deQueue() {
        if (size == 0) {
            return false;
        }

        head.next = head.next.next;
        head.next.pre = head;

        size--;
        return true;
    }

    public int Front() {
        if (size == 0) {
            return -1;
        }
        return head.next.value;
    }

    public int Rear() {
        if (size == 0) {
            return -1;
        }
        return tail.pre.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }


    public static class Node {
        private int value;
        private Node next;
        private Node pre;

        public Node(int value) {
            this.value = value;
        }
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */