package org.example.leetcode.classification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author jialu.yxl
 * @date 07/03/2023 10:50
 */
public class DictionaryTree {

    // public static void main(String[] args) {
    //     System.out.println(findKthNumber(100, 50));
    // }

    /**
     * <a href="https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/">LCR 164. 破解闯关密码</a>
     * 闯关游戏需要破解一组密码，闯关组给出的有关密码的线索是：
     * 一个拥有密码所有元素的非负整数数组 password
     * 密码是 password 中所有元素拼接后得到的最小的一个数
     * 请编写一个程序返回这个密码。
     * 示例 1:
     * 输入: password = [15, 8, 7]
     * 输出: "1578"
     * 示例 2:
     * 输入: password = [0, 3, 30, 34, 5, 9]
     * 输出: "03033459"
     * @param password 0 < password.length <= 100
     * @return
     */
    public String crackPassword(int[] password) {
        String[] nums = Arrays.stream(password).mapToObj(String::valueOf).toArray(String[]::new);
        StringBuilder sb = new StringBuilder();
        Arrays.stream(nums).sorted(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1 + s2).compareTo(s2 + s1);
            }
        }).forEach(sb::append);

        return sb.toString();
    }

    /**
     * <a href="https://leetcode.cn/problems/k-th-smallest-in-lexicographical-order/description/">440. 字典序的第K小数字</a>
     * 输入: n = 13, k = 2
     * 输出: 10
     * 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
     * @param n 1 <= k <= n <= 10^9
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {
        long cur = 1;

        k--;
        while (k > 0) {
            int node = findKthNumberGetNodesCount(cur, n);
            if (node <= k) {
                cur++;
                k -= node;
            } else {
                cur *= 10;
                k--;
            }
        }

        return (int) cur;
    }

    /**
     * 计算[1,n]内以cur为根(开头)的节点个数
     * @param cur
     * @param n
     * @return
     */
    private int findKthNumberGetNodesCount(long cur, int n) {
        long next = cur + 1;
        int result = 0;

        while (cur <= n) {
            result += Math.min(next - cur, n - cur + 1);

            cur *= 10;
            next *= 10;
        }

        return result;
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
