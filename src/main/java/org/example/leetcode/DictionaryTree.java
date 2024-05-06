package org.example.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jialu.yxl
 * @date 07/03/2023 10:50
 */
public class DictionaryTree {

    public static void main(String[] args) {
        System.out.println(findKthNumber(100, 50));
    }

    /**
     * <a href="https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order/description/">440. 字典序的第K小数字</a>
     * @param n
     * @param k
     * @return
     */
    public static int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0) {
            int steps = getSteps(curr, n);
            if (steps <= k) {
                k -= steps;
                curr++;
            } else {
                curr = curr * 10;
                k--;
            }
        }
        return curr;
    }

    public static int getSteps(int curr, long n) {
        int steps = 0;
        long first = curr;
        long last = curr;
        while (first <= n) {
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return steps;
    }

    /**
     * 给你一个产品数组 products 和一个字符串 searchWord ，products  数组中每个产品都是一个字符串。
     * 请你设计一个推荐系统，在依次输入单词 searchWord 的每一个字母后，推荐 products 数组中前缀与 searchWord 相同的最多三个产品。如果前缀相同的可推荐产品超过三个，请按字典序返回最小的三个。
     * 请你以二维列表的形式，返回在输入 searchWord 每个字母后相应的推荐产品的列表。
     * @param products
     * @param searchWord
     * @return
     */
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();



        return result;
    }
}
