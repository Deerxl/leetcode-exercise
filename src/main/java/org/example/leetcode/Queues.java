package org.example.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author jialu.yxl
 * @date 5/16/24 11:41 AM
 */
public class Queues {

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
