package org.example.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。
 * 如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * @author jialu.yxl
 * @date 2/19/24 5:34 PM
 */
public class LRUCache {

    int capacity;
    Map<Integer, Node> map;
    Node head;
    Node tail;

    public static class Node {
        public int key;
        public int val;
        public Node pre;
        public Node next;

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.pre = null;
            this.next = null;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            moveToTail(node);
            map.put(key, node);
            return node.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            moveToTail(node);
        } else {
            if (map.size() >= capacity) {
                deleteFirst();
            }

            Node node = new Node(key, value);
            addToTail(node);
        }
    }

    private void addToTail(Node node) {
        tail.pre.next = node;
        node.pre = tail.pre;
        node.next = tail;
        tail.pre = node;
        map.put(node.key, node);
    }

    private void deleteFirst() {
        Node node = head.next;
        head.next = node.next;
        node.next.pre = head;
        map.remove(node.key);
    }

    private void moveToTail(Node node) {
        if (node.next == tail) {
            return;
        }

        node.pre.next = node.next;
        node.next.pre = node.pre;

        node.pre = tail.pre;
        node.pre.next = node;
        node.next = tail;
        tail.pre = node;
    }
}
