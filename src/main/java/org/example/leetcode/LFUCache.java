package org.example.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * <a href="https://leetcode.cn/problems/lfu-cache/">460. LFU 缓存</a>
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 *
 * 实现 LFUCache 类：
 *
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 * int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
 * void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 *
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 *
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * @author jialu.yxl
 * @date 06/02/2023 16:31
 */
class LFUCache {

    int capacity;
    int time;
    Map<Integer, LFUNode> map;
    TreeSet<LFUNode> treeSet;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.time = 0;
        map = new HashMap<>();
        treeSet = new TreeSet<>();
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        if (map.containsKey(key)) {
            LFUNode node = map.get(key);
            treeSet.remove(node);
            LFUNode newNode = new LFUNode(++node.count, ++time, key, node.value);
            treeSet.add(newNode);
            map.put(node.key, newNode);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (map.size() < capacity) {
            if (map.containsKey(key)) {
                LFUNode node = map.get(key);
                treeSet.remove(node);
                LFUNode newNode = new LFUNode(++node.count, ++time, key, value);
                treeSet.add(newNode);
                map.put(node.key, newNode);
            } else {
                LFUNode node = new LFUNode(0, ++time, key, value);
                map.put(key, node);
                treeSet.add(node);
            }
        } else {
            if (map.containsKey(key)) {
                LFUNode node = map.get(key);
                treeSet.remove(node);
                LFUNode newNode = new LFUNode(++node.count, ++time, key, value);
                treeSet.add(newNode);
                map.put(node.key, newNode);
            } else {
                LFUNode first = treeSet.first();
                map.remove(first.key);
                treeSet.remove(first);
                LFUNode node = new LFUNode(0, ++time, key, value);
                map.put(key, node);
                treeSet.add(node);
            }
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.get(2);
        cache.put(2, 1);
        cache.put(3,2);

        cache.get(3);
        cache.get(2);

        cache.put(4,3);
        cache.get(2);
        cache.get(3);
        cache.get(4);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */