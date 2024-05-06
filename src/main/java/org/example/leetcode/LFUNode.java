package org.example.leetcode;

import java.util.Objects;

/**
 * @author jialu.yxl
 * @date 07/02/2023 14:43
 */
public class LFUNode implements Comparable<LFUNode> {
    int count;
    int time;
    int key;
    int value;

    public LFUNode(int count, int time, int key, int value) {
        this.count = count;
        this.time = time;
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LFUNode lfuNode = (LFUNode) o;
        return count == lfuNode.count && time == lfuNode.time && key == lfuNode.key && value == lfuNode.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, time, key, value);
    }

    @Override
    public int compareTo(LFUNode lfuNode) {
        return count == lfuNode.count ? time - lfuNode.time : count - lfuNode.count;
    }
}