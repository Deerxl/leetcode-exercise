package org.example.leetcode.other;

import org.example.leetcode.classification.SortArray;
import org.example.leetcode.common.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jialu.yxl
 * @date 8/10/24 4:09 PM
 */
public class ReviewTopHot {


    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[] {1,2,2}));
    }


    /**
     * 后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            if (root.right == null || root.right == pre) {
                result.add(root.val);
                pre = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }

        }

        return result;
    }

    /**
     * 买卖股票
     * @param prices 0 <= prices[i] <= 10^5
     * @param k
     * @return
     */
    public int maxProfit(int[] prices, int k) {
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];

        Arrays.fill(buy, prices[0]);
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.min(buy[j], prices[i] - sell[j - 1]);
                sell[j] = Math.max(sell[j], prices[i] - buy[j]);
            }
        }
        return sell[k];
    }


    /**
     * 含重复元素的全排列
     * @param nums
     * @return
     */
    static List<List<Integer>> permuteUniqueResult = new ArrayList<>();
    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        permuteUnique(nums, 0);

        return permuteUniqueResult;
    }

    private static void permuteUnique(int[] nums, int start) {
        if (start == nums.length) {
            permuteUniqueResult.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);

            swap(nums, i, start);
            permuteUnique(nums, start + 1);
            swap(nums, start, i);
        }
    }

    static void swap(int[] nums, int i, int j) {
        SortArray.swap(nums, i, j);
    }


    /**
     * 鸡蛋掉落题
     * <a href="https://leetcode.cn/problems/super-egg-drop/">887. Super Egg Drop</a>
     * You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.
     * @param k  1 <= k <= 100
     * @param n 1 <= n <= 10^4
     * @return Return the minimum number of moves that you need to determine with certainty what the value of f is.
     */
    public int superEggDrop(int k, int n) {
        int t = 1;
        while (superEggDropCalFloor(k, t) < n + 1) {
            t++;
        }
        return t;
    }

    /**
     *
     * @param k 鸡蛋数量
     * @param t 机会数量
     * @return 计算出来的楼层
     */
    int superEggDropCalFloor(int k, int t) {
        if (k == 1 || t == 1) {
            return t + 1;
        }

        return superEggDropCalFloor(k - 1, t - 1) + superEggDropCalFloor(k, t - 1);
    }


    /**
     * 树的前序遍历
     * https://leetcode.cn/problems/binary-tree-preorder-traversal/description/
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return list;
    }


    /**
     * 树的中序遍历
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }

        return list;
    }

    /**
     * 快排
     * 时间复杂度：
     * 最好情况：O(n log n)
     * 平均情况：O(n log n)
     * 最坏情况：O(n²)（通常发生在选择的基准值极端不平衡时）
     * 空间复杂度：O(log n)（栈空间，递归的深度）
     * <a href="https://leetcode.cn/problems/sort-an-array/solutions/179489/fu-xi-ji-chu-pai-xu-suan-fa-java-by-liweiwei1419/">复习基础排序算法（Java）</a>
     * @param nums
     */
    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    static Random random = new Random();
    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int k = start + random.nextInt(end - start + 1);
        int pivot = nums[k];
        nums[k] = nums[start];

        int i = start, j = end;
        while (i < j) {
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            nums[i] = nums[j];
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            nums[j] = nums[i];
        }
        nums[i] = pivot;

        quickSort(nums, start, i - 1);
        quickSort(nums, i + 1, end);
    }

    /**
     * 归并排序
     * 时间复杂度：
     * 最好情况：O(n log n)
     * 平均情况：O(n log n)
     * 最坏情况：O(n log n)
     * 空间复杂度：O(n)（需要额外的数组存储临时结果）
     * @param nums
     */
    public static void mergeSort(int[] nums) {
        int[] mergeSortTmpArr = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, mergeSortTmpArr);
        System.out.println(Arrays.toString(nums));
    }

    private static void mergeSort(int[] nums, int start, int end, int[] mergeSortTmpArr) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid, mergeSortTmpArr);
        mergeSort(nums, mid + 1, end, mergeSortTmpArr);
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }

        mergeSort(nums, start, end, mid, mergeSortTmpArr);
    }

    private static void mergeSort(int[] nums, int start, int end, int mid, int[] mergeSortTmpArr) {
        int index = start;
        int i = start, j = mid + 1;
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) {
                mergeSortTmpArr[index++] = nums[i++];
            } else {
                mergeSortTmpArr[index++] = nums[j++];
            }
        }
        while (i <= mid) {
            mergeSortTmpArr[index++] = nums[i++];
        }
        while (j <= end) {
            mergeSortTmpArr[index++] = nums[j++];
        }
        System.arraycopy(mergeSortTmpArr, start, nums, start, end - start + 1);
    }
}
