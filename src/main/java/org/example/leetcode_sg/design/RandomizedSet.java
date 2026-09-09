package org.example.leetcode_sg.design;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/insert-delete-getrandom-o1/description/?envType=study-plan-v2&envId=top-interview-150">380. Insert Delete GetRandom O(1)</a>
 */
class RandomizedSet {

    Map<Integer, Integer> map;
    List<Integer> list;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);

        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        int lastVal = list.get(list.size() - 1);
        list.set(index, lastVal);
        map.put(lastVal, index);
        list.remove(list.size() - 1);
        map.remove(val);

        return true;
    }

    public int getRandom() {
        Random r = new Random();
        int index = r.nextInt(list.size());
        return list.get(index);
    }
}
