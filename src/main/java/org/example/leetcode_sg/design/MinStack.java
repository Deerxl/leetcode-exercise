package org.example.leetcode_sg.design;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/min-stack/submissions/2166367727/?envType=study-plan-v2&envId=top-interview-150">155. Min Stack</a>
 */
class MinStack {

    List<int[]> list;

    public MinStack() {
        list = new ArrayList<>();
    }

    public void push(int value) {
        if (list.isEmpty()) {
            list.add(new int[]{value, value});
        } else {
            list.add(new int[]{value, Math.min(value, list.get(list.size() - 1)[1])});
        }
    }

    public void pop() {
        list.remove(list.size() - 1);
    }

    public int top() {
        if (list.isEmpty()) {
            return -1;
        }
        return list.get(list.size() - 1)[0];
    }

    public int getMin() {
        return list.get(list.size() - 1)[1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
