package org.example.leetcode;

import java.util.Objects;

/**
 * @author jialu.yxl
 * @date 07/02/2023 14:43
 */
public class LFUNode implements Comparable<LFUNode> {

    int key;
    int value;
    int count;
    int time;

    public LFUNode(int key, int value, int count, int time) {
        this.key = key;
        this.value = value;
        this.count = count;
        this.time = time;
    }

    @Override
    public int compareTo(LFUNode node) {
        return count == node.count ? time - node.time : count - node.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value, this.key, this.time, this.count);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LFUNode) {
            LFUNode node = (LFUNode) obj;
            return this.key == node.key && this.count == node.count && this.value == node.value && this.time == node.time;
        }
        return false;
    }
}