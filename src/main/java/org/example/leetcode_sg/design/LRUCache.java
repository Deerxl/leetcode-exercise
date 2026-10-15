package org.example.leetcode_sg.design;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/lru-cache/description/?envType=study-plan-v2&envId=top-interview-150">146. LRU Cache</a>
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 */
public class LRUCache {

    int capacity;
    Map<Integer, Node> map;
    Node preNode;
    Node tailNode;

    static class Node {
        int key;
        int val;
        Node pre;
        Node next;
    }


    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        preNode = new Node();
        tailNode = new Node();
        preNode.next = tailNode;
        tailNode.pre = preNode;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        if (capacity == 1) {
            return node.val;
        }
        node.pre.next = node.next;
        node.next.pre = node.pre;

        Node lastNode = tailNode.pre;
        lastNode.next = node;
        node.pre = lastNode;
        node.next = tailNode;
        tailNode.pre = node;
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            node.pre.next = node.next;
            node.next.pre = node.pre;

            node.pre = tailNode.pre;
            node.next = tailNode;
            node.pre.next = node;
            tailNode.pre = node;

        } else {
            if (map.size() >= capacity) {
                Node firstNode = preNode.next;
                map.remove(firstNode.key);
                preNode.next = firstNode.next;
                firstNode.next.pre = preNode;
            }
            Node lastNode = tailNode.pre;
            Node node = new Node();
            node.key = key;
            node.val = value;
            node.pre = lastNode;
            node.next = tailNode;

            lastNode.next = node;
            tailNode.pre = node;
            map.put(key, node);
        }
    }
}
