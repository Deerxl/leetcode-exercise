package org.example.leetcode;

import java.util.*;

/**
 * @author jialu.yxl
 * @date 5/16/24 11:41 AM
 */
public class Queues {


    /**
     * <a href="https://leetcode.cn/problems/simplify-path/">71. Simplify Path</a>
     * Given an absolute path for a Unix-style file system, which begins with a slash '/', transform this path into its simplified canonical path.
     *
     * In Unix-style file system context, a single period '.' signifies the current directory, a double period ".." denotes moving up one directory level, and multiple slashes such as "//" are interpreted as a single slash. In this problem, treat sequences of periods not covered by the previous rules (like "...") as valid names for files or directories.
     *
     * The simplified canonical path should adhere to the following rules:
     *
     * It must start with a single slash '/'.
     * Directories within the path should be separated by only one slash '/'.
     * It should not end with a slash '/', unless it's the root directory.
     * It should exclude any single or double periods used to denote current or parent directories.
     * Return the new path.
     * @param path 1 <= path.length <= 3000 path consists of English letters, digits, period '.', slash '/' or '_'. path is a valid absolute Unix path.
     * @return
     */
    public String simplifyPath(String path) {
        String[] split = path.split("/");
        Deque<String> queue = new ArrayDeque<>();
        for (int i = 0; i < split.length; i++) {
            if (split[i].isEmpty() || split[i].equals(".")) {
                continue;
            }
            if (split[i].equals("..")) {
                if (!queue.isEmpty()) {
                    queue.pollLast();
                }
            } else {
                queue.offerLast(split[i]);
            }
        }

        if (queue.isEmpty()) {
            return "/";
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append("/").append(queue.pollFirst());
        }
        return sb.toString();
    }

    /**
     * <a href="https://leetcode.cn/problems/top-k-frequent-elements/">347. Top K Frequent Elements</a>
     * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
     * Example 1:
     * Input: nums = [1,1,1,2,2,3], k = 2
     * Output: [1,2]
     * @param nums 1 <= nums.length <= 105 -104 <= nums[i] <= 104
     * @param k k is in the range [1, the number of unique elements in the array].
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length) {
            int startIndex = i;
            int num = nums[i++];
            while (i < nums.length && nums[i] == num) {
                i++;
            }
            queue.offer(new int[] {num, i - startIndex});
        }
        int[] result = new int[k];
        for (i = 0; i < k; i++) {
            result[i] = queue.poll()[0];
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/">LCR 159. 库存管理 III</a>
     * 仓库管理员以数组 stock 形式记录商品库存表，其中 stock[i] 表示对应商品库存余量。请返回库存余量最少的 cnt 个商品余量，返回 顺序不限。
     * 输入：stock = [2,5,7,4], cnt = 1
     * 输出：[2]
     * 示例 2：
     * 输入：stock = [0,2,3,6], cnt = 2
     * 输出：[0,2] 或 [2,0]
     * @param stock 0 <= stock[i] <= 10000
     * @param cnt 0 <= cnt <= stock.length <= 10000
     * @return
     */
    public int[] inventoryManagement(int[] stock, int cnt) {
        if (cnt <= 0) {
            return new int[] {};
        }
        if (cnt == stock.length) {
            return stock;
        }
        int[] result = new int[cnt];

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < stock.length; i++) {
            queue.offer(stock[i]);
        }

        while (cnt > 0) {
            result[cnt - 1] = queue.poll();
            cnt--;
        }

        return result;
    }
}
