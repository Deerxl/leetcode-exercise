package org.example.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/implement-stack-using-queues/description/">225. 用队列实现栈</a>
 * @author jialu.yxl
 * @date 08/03/2023 11:09
 */
public class MyStack {

    Queue<Integer> queue1;
    Queue<Integer> queue2;
    boolean inFirst = false;

    public MyStack() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
    }

    public void push(int x) {
        if (!inFirst) {
            queue1.offer(x);
            while (!queue2.isEmpty()) {
                queue1.offer(queue2.poll());
            }
            inFirst = true;
        } else {
            queue2.offer(x);
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
            inFirst = false;
        }

    }

    public int pop() {
        if (inFirst) {
            return queue1.poll();
        } else {
            return queue2.poll();
        }
    }

    public int top() {
        if (inFirst) {
            return queue1.peek();
        } else {
            return queue2.peek();
        }
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}


/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
