package org.example.leetcode_sg.classic;

import java.util.*;

public class BackTracking {

    /**
     * <a href="https://leetcode.com/problems/permutations/?envType=study-plan-v2&envId=top-interview-150">46. Permutations</a>
     * @param nums 1 <= nums.length <= 6
     * -10 <= nums[i] <= 10
     * All the integers of nums are unique.
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteBackTrack(result, new ArrayList<>(), nums);
        return result;
    }


    public void permuteBackTrack(List<List<Integer>> list, List<Integer> tmpList, int[] nums) {
        if (tmpList.size() == nums.length) {
            list.add(new ArrayList<>(tmpList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (tmpList.contains(nums[i])) {
                continue;
            }
            tmpList.add(nums[i]);
            permuteBackTrack(list, tmpList, nums);
            tmpList.remove(tmpList.size() - 1);
        }
    }

    /**
     * <a href="https://leetcode.com/problems/combinations/?envType=study-plan-v2&envId=top-interview-150">77. Combinations</a>
     * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
     *
     * You may return the answer in any order.
     * @param n 1 <= n <= 20
     * @param k
     * @return 1 <= k <= n
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();


        for (int i = 1; i <= n - k + 1; i++) {
            List<Integer> initList = new ArrayList<>();
            initList.add(i);
            result.addAll(combine(i + 1, n, k - 1, initList));
        }
        return result;
    }


    List<List<Integer>> combine(int start, int end, int k, List<Integer> curCandiList) {
        List<List<Integer>> result = new ArrayList<>();
        if (k == 0) {
            result.add(curCandiList);
            return result;
        }
        if (start > end) {
            return new ArrayList<>();
        }

        for (int num = start; num <= end; num++) {
            List<Integer> newList = new ArrayList<>(curCandiList);
            newList.add(num);
            result.addAll(combine(num + 1, end, k - 1, newList));
        }

        return result;
    }


    /**
     * <a href="https://leetcode.com/problems/letter-combinations-of-a-phone-number/?envType=study-plan-v2&envId=top-interview-150">17. Letter Combinations of a Phone Number</a>
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        Queue<StringBuffer> queue = new ArrayDeque<>();
        for (char c : digits.toCharArray()) {
            int size = queue.size();
            String s = map.get(c);
            if (size == 0) {
                for (char candi : s.toCharArray()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(candi);
                    queue.offer(stringBuffer);
                }
            } else {
                for (int i = 0; i < size; i++) {
                    StringBuffer poll = queue.poll();
                    for (char candi : s.toCharArray()) {
                        StringBuffer stringBuffer = new StringBuffer(poll.toString());
                        stringBuffer.append(candi);
                        queue.offer(stringBuffer);
                    }
                }
            }
        }

        List<String> result = new ArrayList<>(queue.size());
        while (!queue.isEmpty()) {
            result.add(queue.poll().toString());
        }
        return result;
    }
}
