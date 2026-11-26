package org.example.leetcode_sg.classic;

import java.util.*;

public class BackTracking {


    /**
     * <a href="https://leetcode.com/problems/word-search/?envType=study-plan-v2&envId=top-interview-150">79. Word Search</a>
     * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
     * <p>
     * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        Map<Character, Integer> needs = new HashMap<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                needs.put(board[i][j], needs.getOrDefault(board[i][j], 0) + 1);
            }
        }

        for (char c : word.toCharArray()) {
            int leftCount = needs.getOrDefault(c, 0) - 1;
            if (leftCount < 0) {
                return false;
            }
            needs.put(c, leftCount);
        }

        return existBackTrack(board, word, board.length, board[0].length, 0, 0, 0, new HashSet<>());
    }

    private boolean existBackTrack(char[][] board, String word, int m, int n, int start, int curI, int curJ, HashSet<String> seen) {
        if (start == word.length()) {
            return true;
        }
        if (start == 0) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        seen.add(i + "_" + j);
                        boolean exist = existBackTrack(board, word, m, n, 1, i, j, seen);
                        if (exist) {
                            return true;
                        }
                        seen.remove(i + "_" + j);
                    }
                }
            }
        } else {
            int[][] dirs = new int[][] {{0,1}, {0,-1}, {-1,0}, {1,0}};
            for (int[] dir : dirs) {
                int newI = dir[0] + curI;
                int newJ = dir[1] + curJ;
                if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && !seen.contains(newI + "_" + newJ) && board[newI][newJ] == word.charAt(start)) {
                    seen.add(newI + "_" + newJ);
                    boolean exist = existBackTrack(board, word, m, n, start + 1, newI, newJ, seen);
                    if (exist) {
                        return true;
                    }
                    seen.remove(newI + "_" + newJ);
                }
            }
        }


        return false;
    }


    /**
     * <a href="https://leetcode.com/problems/generate-parentheses/?envType=study-plan-v2&envId=top-interview-150">22. Generate Parentheses</a>
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * @param n 1 <= n <= 8
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesisBackTrack(result, "", 0, 0, n);
        return result;
    }

    void generateParenthesisBackTrack(List<String> result, String tmpStr, int leftCount, int rightCount, int n) {
        if (tmpStr.length() == n * 2) {
            result.add(tmpStr);
            return;
        }

        if (leftCount < n) {
            generateParenthesisBackTrack(result, tmpStr + "(", leftCount + 1, rightCount, n);
        }

        if (rightCount < n && rightCount < leftCount) {
            generateParenthesisBackTrack(result, tmpStr + ")", leftCount, rightCount + 1, n);
        }
    }


    /**
     * <a href="https://leetcode.com/problems/combination-sum/?envType=study-plan-v2&envId=top-interview-150">39. Combination Sum</a>
     *
     Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

     The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

     The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
     * @param candidates 1 <= candidates.length <= 30
     * 2 <= candidates[i] <= 40
     * All elements of candidates are distinct.
     * 1 <= target <= 40
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combinationSumBackTrack(result, new ArrayList<>(), candidates, 0, 0, target);

        return result;
    }

    void combinationSumBackTrack(List<List<Integer>> result, List<Integer> tmpList, int[] candidates, int curSum, int start, int target) {
        if (curSum == target) {
            result.add(new ArrayList<>(tmpList));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] + curSum > target) {
                break;
            }
            tmpList.add(candidates[i]);
            combinationSumBackTrack(result, tmpList, candidates, curSum + candidates[i], i, target);
            tmpList.remove(tmpList.size() - 1);
        }
    }


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
