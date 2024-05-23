package org.example.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jialu.yxl
 * @date 02/02/2023 19:37
 */
public class StringAndArray {

    public static void main(String[] args) {
        System.out.println(findDuplicates(new int[] {4,3,2,7,8,2,3,1}));
    }

    /**
     * <a href="https://leetcode.cn/problems/find-the-duplicate-number/">287. Find the Duplicate Number</a>
     * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
     * There is only one repeated number in nums, return this repeated number.
     * You must solve the problem without modifying the array nums and uses only constant extra space.
     * Example 1:
     * Input: nums = [1,3,4,2,2]
     * Output: 2
     * @param nums nums.length == n + 1 1 <= nums[i] <= n
     * @return
     */
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int temp = Math.abs(nums[i]);
            if (nums[temp] < 0) {
                return temp;
            } else {
                nums[temp] = -nums[temp];
            }
        }
        return -1;
    }

    /**
     * <a href="https://leetcode.cn/problems/find-all-duplicates-in-an-array/description/">442. Find All Duplicates in an Array</a>
     * Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears once or twice, return an array of all the integers that appears twice.
     * You must write an algorithm that runs in O(n) time and uses only constant extra space.
     * Example 1:
     * Input: nums = [4,3,2,7,8,2,3,1]
     * Output: [2,3]
     * @param nums n == nums.length 1 <= n <= 10^5 1 <= nums[i] <= n
     * @return
     */
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums.length <= 1) {
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = -nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            int index = num - 1;
            if (nums[index] > 0) {
                if (!result.contains(index + 1)) {
                    result.add(index + 1);
                }
            } else {
                nums[index] = -nums[index];
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/palindrome-number/">9. Palindrome Number</a>
     * Input: x = -121
     * Output: false
     * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
     * @param x -231 <= x <= 231 - 1
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        if (x < 10) {
            return true;
        }

        int k = 0;
        while (x > k) {
            k = k * 10 + x % 10;
            x /= 10;
        }
        return x == k || x == k / 10;
    }

    /**
     * <a href="https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/">LCR 139. 训练计划 I</a>
     * 教练使用整数数组 actions 记录一系列核心肌群训练项目编号。为增强训练趣味性，需要将所有奇数编号训练项目调整至偶数编号训练项目之前。请将调整后的训练项目编号以 数组 形式返回。
     * 示例 1：
     * 输入：actions = [1,2,3,4,5]
     * 输出：[1,3,5,2,4]
     * 解释：为正确答案之一
     * @param actions
     * @return
     */
    public int[] trainingPlan(int[] actions) {
        int i = 0, j = actions.length - 1;
        int tmp;
        while (i < j) {
            if (actions[i] % 2 == 1) {
                i++;
            } else if (actions[j] % 2 == 0) {
                j--;
            } else {
                tmp = actions[i];
                actions[i] = actions[j];
                actions[j] = tmp;
                i++;
                j--;
            }
        }

        return actions;
    }



    /**
     * <a href="https://leetcode.cn/problems/valid-palindrome/">125. 验证回文串</a>
     * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
     * 字母和数字都属于字母数字字符。
     * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            char c1 = s.charAt(i);
            if (!isPalindromeValidChar(c1)) {
                i++;
                continue;
            }
            char c2 = s.charAt(j);
            if (!isPalindromeValidChar(c2)) {
                j--;
                continue;
            }
            if (c1 != c2) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public boolean isPalindromeValidChar(char c) {
        return (c - 'a' >= 0 && c - 'a' <= 25) || (c - '0' >= 0 && c - '0' <= 9);
    }

    /**
     * <a href="https://leetcode.cn/problems/sort-colors/">75. Sort Colors</a>
     * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
     * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
     * You must solve this problem without using the library's sort function.
     * Example 1:
     * Input: nums = [2,0,2,1,1,0]
     * Output: [0,0,1,1,2,2]
     * @param nums
     */
    public static void sortColors(int[] nums) {
        if (nums.length <= 1) {
            return;
        }

        int i = -1, j = nums.length;
        int index = 0;
        while (index < j) {
            if (nums[index] == 0) {
                nums[i + 1] = 0;
                index++;
                i++;
            } else if (nums[index] == 1) {
                index++;
            } else if (nums[index] == 2) {
                if (nums[j - 1] == 0) {
                    swap(nums, index, j - 1);
                    j--;
                } else if (nums[j - 1] == 2) {
                    j--;
                } else if (nums[j - 1] == 1) {
                    nums[j - 1] = 2;
                    j--;
                    index++;
                }
            }
        }
        while (j - i > 1) {
            nums[i + 1] = 1;
            i++;
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/3sum-closest/">16. 最接近的三数之和</a>
     * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
     * 返回这三个数的和。
     * 假定每组输入只存在恰好一个解。
     * 示例 1：
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     * @param nums 3 <= nums.length <= 1000
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        int tmpResult = result;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                tmpResult = nums[i] + nums[j] + nums[k];
                if (tmpResult == target) {
                    return tmpResult;
                }
                if (Math.abs(tmpResult - target) < Math.abs(result - target)) {
                    result = tmpResult;
                }
                if (tmpResult < target) {
                    j++;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                } else {
                    k--;
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/jump-game-ii/">45. 跳跃游戏 II</a>
     * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
     * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
     * 0 <= j <= nums[i]
     * i + j < n
     * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int result = 0;
        int maxIndex = nums[0];
        int curIndex = 0, tmpMaxIndex = maxIndex;
        while (curIndex < nums.length) {
            while (curIndex < nums.length && curIndex <= maxIndex) {
                if (curIndex + nums[curIndex] > tmpMaxIndex) {
                    tmpMaxIndex = curIndex + nums[curIndex];
                }
                curIndex++;
            }
            result++;
            curIndex = maxIndex + 1;
            maxIndex = tmpMaxIndex;
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array/">26. Remove Duplicates from Sorted Array</a>
     * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
     *
     * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
     *
     * Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
     * Return k.
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (index != i) {
                    nums[index] = nums[i];
                }
                index++;
            }
        }
        return index;
    }

    /**
     * <a href="https://leetcode.cn/problems/combination-sum-ii/"> 40. Combination Sum II</a>
     * Given a collection of candidate numbers (candidates) and a target number (target),
     * find all unique combinations in candidates where the candidate numbers sum to target.
     * Each number in candidates may only be used once in the combination.
     * Note: The solution set must not contain duplicate combinations.
     * Input: candidates = [10,1,2,7,6,1,5], target = 8
     * Output:
     * [
     * [1,1,6],
     * [1,2,5],
     * [1,7],
     * [2,6]
     * ]
     * @param candidates
     * @param target
     * @return
     */
    static List<List<Integer>> combinationSum2Result = new ArrayList<>();
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);

        combinationSum2(candidates, target, 0, new ArrayList<>());

        return combinationSum2Result;
    }

    private static void combinationSum2(int[] candidates, int target, int startIndex, List<Integer> list) {
        if (startIndex >= candidates.length) {
            return;
        }

        while (startIndex < candidates.length) {
            int num = candidates[startIndex];
            if (num > target) {
                return;
            }

            List<Integer> newList = new ArrayList<>(list);
            newList.add(num);

            if (startIndex > 0 && num == candidates[startIndex - 1]) {
                int listCount = 0;
                for (int i = newList.size() - 1; i >= 0; i--) {
                    if (newList.get(i) == num) {
                        listCount++;
                    } else {
                        break;
                    }
                }
                int arrCount = 2;
                for (int i = startIndex - 2; i >= 0; i--) {
                    if (candidates[i] == num) {
                        arrCount++;
                    } else {
                        break;
                    }
                }
                if (listCount != arrCount) {
                    startIndex++;
                    continue;
                }
            }


            if (num == target) {
                combinationSum2Result.add(newList);
            }
            combinationSum2(candidates, target - num, startIndex + 1, newList);
            startIndex++;
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/container-with-most-water/">11. Container With Most Water</a>
     * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
     * Find two lines that together with the x-axis form a container, such that the container contains the most water.
     * Return the maximum amount of water a container can store.
     * Notice that you may not slant the container.
     * Input: height = [1,8,6,2,5,4,8,3,7]
     * Output: 49
     * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int result = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            if (height[i] < height[j]) {
                result = Math.max(result, height[i] * (j - i));
                i++;
            } else {
                result = Math.max(result, height[j] * (j - i));
                j--;
            }
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/permutations-ii/">47. Permutations II</a>
     * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
     * Example 1:
     * Input: nums = [1,1,2]
     * Output:
     * [[1,1,2],
     *  [1,2,1],
     *  [2,1,1]]
     * @param nums
     * @return
     */
    static List<List<Integer>> permuteUniqueResult = new ArrayList<>();
    static List<Integer> permuteUniqueNums = new ArrayList<>();
    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);

        for (int num : nums) {
            permuteUniqueNums.add(num);
        }

        permuteUnique(0);

        return permuteUniqueResult;
    }

    private static void permuteUnique(int start) {
        if (start == permuteUniqueNums.size() - 1) {
            permuteUniqueResult.add(new ArrayList<>(permuteUniqueNums));
            return;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = start; i < permuteUniqueNums.size(); i++) {
            if (set.contains(permuteUniqueNums.get(i))) {
                continue;
            }
            set.add(permuteUniqueNums.get(i));
            swap(permuteUniqueNums, i, start);
            permuteUnique(start + 1);
            swap(permuteUniqueNums, i, start);
        }
    }

    private static void swap(List<Integer> list, int i, int j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    /**
     * <a href="https://leetcode.cn/problems/validate-ip-address/">468. 验证IP地址</a>
     * 给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither"
     * 有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.168.1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192.168@1.1” 为无效IPv4地址。
     * 一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
     * 1 <= xi.length <= 4
     * xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
     * 在 xi 中允许前导零。
     * 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:7334" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:8a2e:0370:7334" 是无效的 IPv6 地址。
     * @param queryIP
     * @return
     */
    public static String validIPAddress(String queryIP) {
        if (validIPAddressIPv4(queryIP)) {
            return "IPv4";
        }
        if (validIPAddressIPv6(queryIP)) {
            return "IPv6";
        }

        return "Neither";
    }

    private static boolean validIPAddressIPv6(String queryIP) {
        if (queryIP.length() < 1 * 8 + 7 || queryIP.length() > 8 * 4 + 7) {
            return false;
        }
        if (queryIP.charAt(0) == ':' || queryIP.charAt(queryIP.length() - 1) == ':') {
            return false;
        }
        String[] split = queryIP.split(":");
        if (split.length != 8) {
            return false;
        }

        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (s.length() == 0 || s.length() > 4) {
                return false;
            }

            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (!(c - '0' >= 0 && c - '0' <= 9)
                        && !(c - 'a' >= 0 && c - 'a' <= 5)
                        && !(c - 'A' >= 0 && c - 'A' <= 5)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean validIPAddressIPv4(String queryIP) {
        if (queryIP.length() < 1 * 4 + 3 || queryIP.length() > 3 * 4 + 3) {
            return false;
        }
        if (queryIP.charAt(0) == '.' || queryIP.charAt(queryIP.length() - 1) == '.') {
            return false;
        }
        String[] split = queryIP.split("\\.");
        if (split.length != 4) {
            return false;
        }

        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (s.length() == 0 || s.length() > 3) {
                return false;
            }

            if ('0' == s.charAt(0) && s.length() > 1) {
                return false;
            }

            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) - '0' < 0 || s.charAt(j) - '0' > 9) {
                    return false;
                }
            }

            if (Integer.parseInt(s) > 255) {
                return false;
            }
        }

        return true;
    }

    /**
     * <a href="https://leetcode.cn/problems/move-zeroes/">283. 移动零</a>
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * 示例 1:
     * 输入: nums = [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int zeroIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (zeroIndex != i - 1) {
                    nums[zeroIndex + 1] = nums[i];
                }
                zeroIndex++;
            }
        }
        if (zeroIndex >= 0) {
            for (int i = zeroIndex + 1; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/subarray-sum-equals-k/">560. 和为 K 的子数组</a>
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
     * 子数组是数组中元素的连续非空序列。
     * 示例 1：
     * 输入：nums = [1,1,1], k = 2
     * 输出：2
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        int preSum = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if (map.containsKey(preSum - k)) {
                result += map.get(preSum - k);
            }

            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/">122. 买卖股票的最佳时机 II</a>
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润 。
     * 示例 1：
     * 输入：prices = [7,1,5,3,6,4]
     * 输出：7
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
     *      总利润为 4 + 3 = 7 。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }

        int result = 0;
        int index = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[i - 1]) {
                if (index < i - 1) {
                    result += prices[i - 1] - prices[index];
                }
                index = i;
            }
        }
        if (index < prices.length - 1) {
            result += prices[prices.length - 1] - prices[index];
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/largest-number/">179. 最大数</a>
     * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
     * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     * 示例 1：
     * 输入：nums = [10,2]
     * 输出："210"
     * 示例 2：
     * 输入：nums = [3,30,34,5,9]
     * 输出："9534330"
     * @param nums
     * @return
     */
    public static String largestNumber(int[] nums) {
        if (nums.length == 0) {
            return "";
        }
        if (nums.length == 1) {
            return String.valueOf(nums[0]);
        }

        List<String> list = Arrays.stream(nums).boxed()
                .map(String::valueOf)
                .sorted((x, y) -> (y + x).compareTo(x + y))
                .collect(Collectors.toList());
        if ("0".equals(list.get(0))) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    /**
     * <a href="https://leetcode.cn/problems/majority-element/">169. 多数元素</a>
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * 示例 1：
     * 输入：nums = [3,2,3]
     * 输出：3
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    /**
     * <a href="https://leetcode.cn/problems/longest-consecutive-sequence/">128. 最长连续序列</a>
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        Arrays.sort(nums);
        int result = 1;
        int tmpLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (tmpLen == 1 && nums.length - i < result) {
                break;
            }
            if (nums[i] - nums[i - 1] == 1) {
                tmpLen++;
            } else if (nums[i] - nums[i - 1] > 1) {
                result = Math.max(result, tmpLen);
                tmpLen = 1;
            }
        }
        result = Math.max(result, tmpLen);

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/longest-common-prefix/">14. 最长公共前缀</a>
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                String str = strs[j];
                if (i >= str.length()) {
                    return sb.toString();
                }
                if (str.charAt(i) != c) {
                    return sb.toString();
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * <a href="https://leetcode.cn/problems/decode-string/">394. 字符串解码</a>
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        if (s.length() < 4) {
            return s;
        }

        StringBuilder result = new StringBuilder();
        Stack<String> stack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);

            if (c - '0' >= 0 && c - '0' <= 9) {
                StringBuilder tmp = new StringBuilder();
                while (i < s.length() && s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9) {
                    tmp.append(s.charAt(i));
                    i++;
                }
                stack.push(tmp.toString());
            } else if (c - 'a' >= 0 && c - 'a' < 26) {
                StringBuilder tmp = new StringBuilder();
                while (i < s.length() && s.charAt(i) - 'a' >= 0&& s.charAt(i) - 'a' < 26) {
                    tmp.append(s.charAt(i));
                    i++;
                }

                if (stack.isEmpty()) {
                    result.append(tmp);
                } else {
                    stack.push(tmp.toString());
                }
            } else if (c - ']' == 0) {
                StringBuilder tmpStr = new StringBuilder(stack.pop());
                while (stack.peek().charAt(0) - 'a' >= 0 && stack.peek().charAt(0) - 'a' < 26) {
                    tmpStr.insert(0, stack.pop());
                }

                String tmpNum = stack.pop();
                StringBuilder stringBuilder = new StringBuilder();

                for (int j = 0; j < Integer.parseInt(tmpNum); j++) {
                    stringBuilder.append(tmpStr);
                }
                if (stack.isEmpty()) {
                    result.append(stringBuilder);
                } else {
                    stack.push(stringBuilder.toString());
                }
                i++;
            } else {
                i++;
            }
        }

        return result.toString();
    }

    /**
     * <a href="https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/">34. 在排序数组中查找元素的第一个和最后一个位置</a>
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     *
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     *
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     *
     *输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * @param nums
     * @param target
     * @return
     */
    int[] searchRangeResult = new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE};
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1,-1};
        }

        searchRange(nums, 0, nums.length - 1, target);

        return searchRangeResult[0] == Integer.MAX_VALUE ? new int[]{-1, -1} : searchRangeResult;
    }

    private void searchRange(int[] nums, int start, int end, int target) {
        if (start > end) {
            return;
        }

        int mid = start + (end - start) / 2;
        if (nums[mid] < target) {
            searchRange(nums, mid + 1, end, target);
        } else if (nums[mid] > target) {
            searchRange(nums, start, mid - 1, target);
        } else {
            if (mid < searchRangeResult[0]) {
                searchRangeResult[0] = mid;
            }
            if (mid > searchRangeResult[1]) {
                searchRangeResult[1] = mid;
            }

            searchRange(nums, mid + 1, end, target);
            searchRange(nums, start, mid - 1, target);
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/combination-sum/">39. 组合总和</a>
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     * 示例 1：
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     * 解释：
     * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
     * 7 也是一个候选， 7 = 7 。
     * 仅有这两种组合。
     * @param candidates
     * @param target
     * @return
     */
    static List<List<Integer>> combinationSumResult = new ArrayList<>();
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);

        combinationSum(candidates, target, 0, new ArrayList<>(), 0);

        return combinationSumResult;
    }

    private static void combinationSum(int[] candidates, int target, int startIndex, List<Integer> list, int curSum) {
        if (startIndex >= candidates.length) {
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            int num = candidates[i];
            if (num > target || curSum + num > target) {
                break;
            }

            List<Integer> newList = new ArrayList<>(list);
            newList.add(num);
            if (curSum + num == target) {
                combinationSumResult.add(newList);
                break;
            } else {
                combinationSum(candidates, target, i, newList, curSum + num);
            }
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/reverse-words-in-a-string/">151. 反转字符串中的单词</a>
     * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
     * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
     *
     * 进阶：如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用 O(1) 额外空间复杂度的 原地 解法。
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] split = s.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            if (split[i].length() <= 0) {
                continue;
            }

            result.append(split[i]).append(" ");
        }

        if (result.length() > 1) {
            return result.substring(0, result.length() - 1);
        }

        return "";
    }

    /**
     * <a href="https://leetcode.cn/problems/longest-valid-parentheses/">32. 最长有效括号</a>
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        int result = 0;
        Stack<Integer> stack = new Stack<>();
        int tmpResult = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                    tmpResult += 2;
                } else {
                    result = Math.max(result, tmpResult);
                    tmpResult = 0;
                }
            }
        }

        if (stack.isEmpty()) {
            result = Math.max(result, tmpResult);
        } else {
            int leftIndex;
            int rightIndex = s.length() - 1;
            int realResult = 0;
            while (!stack.isEmpty()) {
                leftIndex = stack.pop();
                realResult = rightIndex - leftIndex;
                result = Math.max(result, realResult);
                rightIndex = leftIndex - 1;
                tmpResult -= realResult;
            }
            result = Math.max(result, tmpResult);
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/subsets/">78. 子集</a>
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * @param nums
     * @return
     */
    List<List<Integer>> subsetsResult = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        subsets(nums, 0, new ArrayList<>());

        subsetsResult.add(new ArrayList<>());
        return subsetsResult;
    }

    private void subsets(int[] nums, int startIndex, List<Integer> result) {
        for (int i = startIndex; i < nums.length; i++) {
            List<Integer> tmpResult = new ArrayList<>(result);
            tmpResult.add(nums[i]);
            subsetsResult.add(tmpResult);
            subsets(nums, i + 1, tmpResult);
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/minimum-window-substring/">76. 最小覆盖子串</a>
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * 注意：
     * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
     * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        String result = "";
        if (t.length() > s.length()) {
            return result;
        }

        if (s.contains(t)) {
            return t;
        }

        Map<Character, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        int startIndex = -1;

        Map<Character, Integer> curMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (targetMap.containsKey(c)) {
                curMap.put(c, curMap.getOrDefault(c, 0) + 1);

                if (startIndex == -1) {
                    startIndex = i;
                    continue;
                }

                boolean check = checkMinWindowComplete(targetMap, curMap);
                if (check) {
                    String str;
                    while (checkMinWindowComplete(targetMap, curMap)) {
                        str = s.substring(startIndex, i + 1);
                        if ("".equals(result) || str.length() < result.length()) {
                            result = str;
                        }
                        if (curMap.containsKey(s.charAt(startIndex))) {
                            curMap.put(s.charAt(startIndex), curMap.get(s.charAt(startIndex)) - 1);
                        }
                        startIndex++;
                    }
                    startIndex--;
                    curMap.put(s.charAt(startIndex), curMap.getOrDefault(s.charAt(startIndex), 0) + 1);
                }
            }
        }

        return result;
    }

    public static boolean checkMinWindowComplete(Map<Character, Integer> targetMap, Map<Character, Integer> curMap) {
        for (Map.Entry<Character, Integer> entry : targetMap.entrySet()) {
            if (curMap.containsKey(entry.getKey())) {
                if (curMap.get(entry.getKey()) < targetMap.get(entry.getKey())) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * <a href="https://leetcode.cn/problems/coin-change/description/">322. 零钱兑换</a>
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount <= 0) {
            return 0;
        }
        Arrays.sort(coins);

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] > i) {
                    break;
                }
                dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * <a href="https://leetcode.cn/problems/first-missing-positive/">41. 缺失的第一个正数</a>
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                nums[i] = 0;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (num > 0 && num <= nums.length) {
                if (nums[num - 1] == 0) {
                    nums[num - 1] = -Integer.MAX_VALUE;
                } else {
                    nums[num - 1] = -Math.abs(nums[num - 1]);
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    /**
     * <a href="https://leetcode.cn/problems/sliding-window-maximum/description/">239. 滑动窗口最大值</a>
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回 滑动窗口中的最大值 。
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowPriorityQueue(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t2[0] != t1[0] ? t2[0] - t1[0] : t2[1] - t1[1];
            }
        });

        for (int i = 0; i < k; i++) {
            priorityQueue.offer(new int[]{nums[i], i});
        }
        result[0] = priorityQueue.peek()[0];

        for (int i = 1; i <= nums.length - k; i++) {
            priorityQueue.offer(new int[]{nums[i + k - 1], i + k - 1});

            while (priorityQueue.peek()[1] < i) {
                priorityQueue.poll();
            }

            result[i] = priorityQueue.peek()[0];
        }


        return result;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int lastMaxIndex = 0;
        int lastMaxNum = nums[0];
        for (int i = 1; i < k; i++) {
            if (nums[i] >= lastMaxNum) {
                lastMaxIndex = i;
                lastMaxNum = nums[i];
            }
        }
        result[0] = lastMaxNum;

        int lastWindowIndex;
        for (int i = 1; i <= nums.length - k; i++) {
            lastWindowIndex = i + k - 1;
            if (nums[lastWindowIndex] >= lastMaxNum) {
                lastMaxIndex = lastWindowIndex;
                lastMaxNum = nums[lastWindowIndex];
                result[i] = lastMaxNum;
            } else {
                if (lastMaxIndex >= i) {
                    result[i] = lastMaxNum;
                } else {
                    lastMaxIndex = i;
                    lastMaxNum = nums[i];
                    for (int j = i + 1; j <= lastWindowIndex; j++) {
                        if (nums[j] >= lastMaxNum) {
                            lastMaxIndex = j;
                            lastMaxNum = nums[j];
                        }
                    }
                    result[i] = lastMaxNum;
                }
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/compare-version-numbers/">165. 比较版本号</a>
     * 给你两个版本号 version1 和 version2 ，请你比较它们。
     * 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。
     * 修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
     * 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。
     * 如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
     * 返回规则如下：
     * 如果 version1 > version2 返回 1，
     * 如果 version1 < version2 返回 -1，
     * 除此之外返回 0。
     *
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        String[] v1Nums = version1.split("\\.");
        String[] v2Nums = version2.split("\\.");

        int minLen = Math.min(v1Nums.length, v2Nums.length);
        int i;
        for (i = 0; i < minLen; i++) {
            int v1 = Integer.parseInt(v1Nums[i]);
            int v2 = Integer.parseInt(v2Nums[i]);
            if (v1 < v2) {
                return -1;
            } else if (v1 > v2) {
                return 1;
            }
        }

        for (int j = i; j < v1Nums.length; j++) {
            if (Integer.parseInt(v1Nums[j]) > 0) {
                return 1;
            }
        }

        for (int j = i; j < v2Nums.length; j++) {
            if (Integer.parseInt(v2Nums[j]) > 0) {
                return -1;
            }
        }

        return 0;
    }

    /**
     * <a href="https://leetcode.cn/problems/generate-parentheses/">22. 括号生成</a>
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * @param n
     * @return
     */
    static List<String> generateParenthesisResult = new ArrayList<>();
    public static List<String> generateParenthesis(int n) {
        generateParenthesis("", n, n);
        return generateParenthesisResult;
    }

    private static void generateParenthesis(String s, int lCount, int rCount) {
        if (lCount > rCount) {
            return;
        }
        if (lCount == rCount) {
            s += "(";
            generateParenthesis(s, lCount - 1, rCount);
        } else {
            if (lCount > 0) {
                generateParenthesis(s + "(", lCount - 1, rCount);
                generateParenthesis(s + ")", lCount, rCount - 1);
            }

            if (lCount == 0) {
                StringBuilder rStr = new StringBuilder();
                for (int i = 0; i < rCount; i++) {
                    rStr.append(")");
                }
                generateParenthesisResult.add(s + rStr);
            }
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/string-to-integer-atoi/">8. 字符串转换整数 (atoi)</a>
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     *
     * 函数 myAtoi(string s) 的算法如下：
     *
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * 注意：
     *
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        int result = 0;
        if (s == null || s.length() == 0) {
            return result;
        }

        StringBuilder num = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
            } else if (isNumber(c) || c == '-' || c == '+') {
                boolean negative = false;
                if (c == '-' || c == '+') {
                    negative = c == '-';
                    i++;
                }

                if (i >= s.length()) {
                    break;
                }
                c = s.charAt(i);
                if (isNumber(c)) {
                    while (i < s.length()) {
                        if (s.charAt(i) == '0') {
                            i++;
                        } else {
                            break;
                        }
                    }
                    while (i < s.length() && isNumber(s.charAt(i)) && num.length() < 11) {
                        num.append(s.charAt(i));
                        i++;
                    }
                    if (num.length() <= 0) {
                        break;
                    }
                    long l = Long.parseLong(num.toString());
                    if (negative) {
                        l = -l;
                    }
                    if (l < Integer.MIN_VALUE) {
                        return Integer.MIN_VALUE;
                    } else if (l > Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    } else {
                        return (int) l;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        return result;
    }

    boolean isNumber(char c) {
        return c - '0' >= 0 && c - '0' <= 9;
    }

    /**
     * <a href="https://leetcode.cn/problems/next-permutation/">31. 下一个排列</a>
     * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
     *
     * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
     * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
     * 那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，
     * 那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
     *
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     * 给你一个整数数组 nums ，找出 nums 的下一个排列。
     *
     * 必须 原地 修改，只允许使用额外常数空间。
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int i;
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }

        if (i >= 0) {
            int largeIndex = i + 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i] && nums[j] < nums[largeIndex]) {
                    largeIndex = j;
                }
            }
            swap(nums, largeIndex, i);
        }

        nextPermutationSort(nums, i + 1, nums.length - 1);
    }

    private static void nextPermutationSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        Random random = new Random();
        int partition = random.nextInt(end - start + 1) + start;
        int pivot = nums[partition];

        swap(nums, start, partition);
        int l = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] < pivot) {
                l++;
                swap(nums, i, l);
            }
        }
        swap(nums, start, l);

        nextPermutationSort(nums, start, l - 1);
        nextPermutationSort(nums, l + 1, end);
    }

    /**
     * <a href="https://leetcode.cn/problems/median-of-two-sorted-arrays/">4. 寻找两个正序数组的中位数</a>
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     *
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int first = (m + n + 1) / 2;
        int second = (m + n + 2) / 2;

        if (first == second) {
            return findMedianSortedArrays(nums1, nums2, 0, m - 1, 0, n - 1, first);
        } else {
            return (findMedianSortedArrays(nums1, nums2, 0, m - 1, 0, n - 1, first)
                    + findMedianSortedArrays(nums1, nums2, 0, m - 1, 0, n - 1, second)) / 2;
        }
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2,
                                         int start1, int end1,
                                         int start2, int end2,
                                         int kth) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        if (len1 <= 0) {
            return nums2[start2 + kth - 1];
        }
        if (len2 <= 0) {
            return nums1[start1 + kth - 1];
        }

        if (kth == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int mid1 = start1 + Math.min(len1, kth / 2) - 1;
        int mid2 = start2 + Math.min(len2, kth / 2) - 1;

        if (nums1[mid1] < nums2[mid2]) {
             return findMedianSortedArrays(nums1, nums2, mid1 + 1, end1, start2, end2, kth - (mid1 - start1 + 1));
        } else {
            return findMedianSortedArrays(nums1, nums2, start1, end1, mid2 + 1, end2, kth - (mid2 - start2 + 1));
        }

    }

    /**
     * <a href="https://leetcode.cn/problems/binary-search/">704. 二分查找</a>
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * @param nums
     * @param target
     * @return
     */
    public int searchBinary(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        return searchBinary(nums, 0, nums.length - 1, target);
    }

    public int searchBinary(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }

        int mid = start + (end - start) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        if (nums[mid] < target) {
            return searchBinary(nums, mid + 1, end, target);
        } else {
            return searchBinary(nums, start, mid - 1, target);
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/restore-ip-addresses/">93. 复原 IP 地址</a>
     * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
     * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。
     * 你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
     * @param s
     * @return
     */
    static List<String> restoreIpAddressesResult = new ArrayList<>();
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        result.add("");

        restoreIpAddresses(s, result, 0, 4);
        return restoreIpAddressesResult;
    }

    private static void restoreIpAddresses(String s, List<String> result, int start, int leftCount) {
        if (leftCount == 0) {
            if (start == s.length()) {
                restoreIpAddressesResult.addAll(result);
            }
            return;
        }

        int len = s.length() - start;
        if (len < leftCount || len > leftCount * 3) {
            return;
        }

        if (start >= s.length()) {
            return;
        }

        for (int i = 1; i <= 3; i++) {
            List<String> newResult = new ArrayList<>();

            int end = start + i;
            if (end > s.length()) {
                break;
            }
            String substring = s.substring(start, end);
            if (substring.charAt(0) == '0' && substring.length() > 1) {
                break;
            }
            if (Integer.parseInt(substring) > 255) {
                break;
            }
            for (String str : result) {
                if (leftCount > 1) {
                    newResult.add(str + substring + ".");
                } else {
                    newResult.add(str + substring);
                }
            }
            restoreIpAddresses(s, newResult, end, leftCount - 1);
        }
    }


    /**
     * <a href="https://leetcode.cn/problems/merge-intervals/description/">56. 合并区间</a>
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]。
     * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0] - t2[0];
            }
        });

        List<int[]> mergeList = new ArrayList<>();
        mergeList.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] lastArr = mergeList.get(mergeList.size() - 1);
            int[] curArr = intervals[i];
            if (curArr[0] <= lastArr[1]) {
                lastArr[1] = Math.max(lastArr[1], curArr[1]);
            } else {
                mergeList.add(curArr);
            }
        }

        int[][] result = new int[mergeList.size()][2];
        for (int i = 0; i < mergeList.size(); i++) {
            result[i] = mergeList.get(i);
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/trapping-rain-water/">42. 接雨水</a>
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int result = 0;
        if (height == null || height.length <= 2) {
            return result;
        }

        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        for (int i = 0; i < height.length; i++) {
            result += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/add-strings/">415. 字符串相加</a>
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
     *
     * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
     * 示例 2：
     *
     * 输入：num1 = "456", num2 = "77"
     * 输出："533"
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() -1;
        int j = num2.length() - 1;
        int add = 0;
        StringBuilder result = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int i1 = 0, j1 = 0;
            if (i >= 0) {
                i1 = Integer.parseInt(String.valueOf(num1.charAt(i)));
                i--;
            }
            if (j >= 0) {
                j1 = Integer.parseInt(String.valueOf(num2.charAt(j)));
                j--;
            }
            int tempResult = i1 + j1 + add;
            add = tempResult / 10;
            result.insert(0, tempResult % 10);
        }
        if (add > 0) {
            result.insert(0, 1);
        }
        return result.toString();
    }

    /**
     * <a href="https://leetcode.cn/problems/longest-increasing-subsequence/">300. 最长递增子序列</a>
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 1) {
            return nums.length;
        }

        int[] dp = new int[nums.length];
        int result = 1;
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            int tempMax = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    tempMax = Math.max(tempMax, dp[j]);
                }
            }
            dp[i] = Math.max(dp[i], tempMax + 1);
            result = Math.max(dp[i], result);
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/permutations/">46. 全排列</a>
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 1) {
            List<Integer> tempResult = new ArrayList<>();
            tempResult.add(nums[0]);
            result.add(tempResult);
        } else {
            for (int i = 0; i < nums.length; i++) {
                int[] tempNums = new int[nums.length - 1];
                if (i > 0) {
                    System.arraycopy(nums, 0, tempNums, 0, i);
                }
                if (i < nums.length - 1) {
                    System.arraycopy(nums, i + 1, tempNums, i, nums.length - i - 1);
                }
                List<List<Integer>> tempResult = permute(tempNums);
                for (List<Integer> list : tempResult) {
                    list.add(nums[i]);
                    result.add(list);
                }
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/merge-sorted-array/">88. 合并两个有序数组</a>
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     *
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     *
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = nums1.length - 1;
        while (i >= 0 || j >= 0) {
            if (i >= 0 && j >= 0) {
                if (nums2[j] >= nums1[i]) {
                    nums1[k] = nums2[j];
                    j--;
                } else {
                    nums1[k] = nums1[i];
                    i--;
                }
            } else if (i >= 0) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/valid-parentheses/">20. 有效的括号</a>
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
     * 输入：s = "()[]{}"
     * 输出：true
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            } else if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    /**
     * <a href="https://leetcode.cn/problems/number-of-islands/">200. 岛屿数量</a>
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * 输出：1
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int result = 0;
        if (grid == null) {
            return result;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    numIslandsMark(grid, i, j);
                    result += 1;
                }
            }
        }
        return result;
    }

    private void numIslandsMark(char[][] grid, int startRow, int startCol) {
        if (startRow < 0 || startRow >= grid.length) {
            return;
        }
        if (startCol < 0 || startCol >= grid[0].length) {
            return;
        }
        if (grid[startRow][startCol] != '1') {
            return;
        }

        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        grid[startRow][startCol] = '2';
        for (int[] dir : dirs) {
            numIslandsMark(grid, startRow + dir[0], startCol + dir[1]);
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/search-in-rotated-sorted-array/">33. 搜索旋转排序数组</a>
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     *
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     *
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     *
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     * @param nums
     * @param target
     * @return
     */
    public static int searchResult = -1;
    public static boolean findSearch = false;
    public static int search(int[] nums, int target) {
        search(nums, target, 0, nums.length - 1);
        return searchResult;
    }

    private static void search(int[] nums, int target, int start, int end) {
        if (findSearch) {
            return;
        }
        if (start >= 0 && start < nums.length && nums[start] == target) {
            searchResult = start;
            findSearch = true;
            return;
        }
        if (end >= 0 && end < nums.length && nums[end] == target) {
            searchResult = end;
            findSearch = true;
            return;
        }

        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            searchResult = mid;
            findSearch = true;
            return;
        }

        search(nums, target, start + 1, mid - 1);
        search(nums, target, mid + 1, end - 1);
    }

    /**
     * <a href="https://leetcode.cn/problems/longest-palindromic-substring/">5. 最长回文子串</a>
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
     *
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        String result = "";
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((s.length() - i) <= maxLen / 2) {
                break;
            }

            String singleStr = longestPalindromeSingle(s, i);
            String doubleStr = longestPalindromeDouble(s, i);
            String tempStr = singleStr.length() > doubleStr.length() ? singleStr : doubleStr;
            maxLen = Math.max(result.length(), tempStr.length());
            if (tempStr.length() > result.length()) {
                result = tempStr;
            }
        }

        return result;
    }

    private String longestPalindromeDouble(String s, int start) {
        int left = start;
        int right = start + 1;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        left += 1;
        right -= 1;
        if (left >= right) {
            return "";
        } else {
            return s.substring(left, right + 1);
        }
    }

    private String longestPalindromeSingle(String s, int start) {
        int left = start - 1;
        int right = start + 1;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }

        left += 1;
        right -= 1;

        return s.substring(left, right + 1);
    }

    /**
     *
     * <a href="https://leetcode.cn/problems/two-sum/description/">1. 两数之和</a>
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            if (map.containsKey(target - temp)) {
                result[0] = map.get(target - temp);
                result[1] = i;
            } else {
                map.put(temp, i);
            }
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/maximum-subarray/">53. 最大子数组和</a>
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length <= 1) {
            return nums[0];
        }
        int result = nums[0];
        int lastResult = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            lastResult = Math.max(lastResult + temp, temp);
            result = Math.max(result, lastResult);
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/3sum/">15. 三数之和</a>
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     *
     * 你返回所有和为 0 且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 解释：
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
     * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
     * 注意，输出的顺序和三元组的顺序并不重要。
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int firstNum = nums[i];
            if (i > 0 && firstNum == nums[i - 1]) {
                continue;
            }
            if (firstNum > 0) {
                break;
            }

            int targetTwoNumSum = -firstNum;
            result.addAll(twoSum(nums, firstNum, i + 1, targetTwoNumSum));
        }

        return result;
    }

    private static List<List<Integer>> twoSum(int[] nums, int firstNum, int start, int targetTwoNumSum) {
        List<List<Integer>> result = new ArrayList<>();
        int i = start;
        int j = nums.length - 1;
        while (i < j) {
            int tempSum = nums[i] + nums[j];
            if (tempSum < targetTwoNumSum) {
                i++;
                continue;
            } else if (tempSum > targetTwoNumSum) {
                j--;
                continue;
            }
            if (i > start && nums[i] == nums[i - 1]) {
                i++;
                continue;
            }
            if (j < nums.length - 1 && nums[j] == nums[j + 1]) {
                j--;
                continue;
            }

            List<Integer> tempResult = new ArrayList<>();
            tempResult.add(firstNum);
            tempResult.add(nums[i]);
            tempResult.add(nums[j]);
            result.add(tempResult);
            i++;
            j--;
        }
        return result;
    }


    /**
     * <a href="https://leetcode.cn/problems/kth-largest-element-in-an-array/description/">215. 数组中的第K个最大元素</a>
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     * 输入: [3,2,3,1,2,4,5,5,6], k = 4
     * 输出: 4
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        if (nums.length <= 1) {
            return nums[0];
        }
        int targetIndex = nums.length - k;
        quickSortArray(nums, 0, nums.length - 1, targetIndex);
        return nums[targetIndex];
    }

    public static int[] quickSort(int[] nums) {
        quickSortArray(nums, 0, nums.length - 1, 0);
        return nums;
    }

    private static void quickSortArray(int[] nums, int start, int end, int targetIndex) {
        if (start >= end) {
            return;
        }

        int partition = quickSortArrayPartition(nums, start, end);
        if (partition == targetIndex) {
            return;
        } else if (partition < targetIndex) {
            quickSortArray(nums, partition + 1, end, targetIndex);
        } else {
            quickSortArray(nums, start, partition - 1, targetIndex);
        }
    }

    private static int quickSortArrayPartition(int[] nums, int start, int end) {
        Random random = new Random();
        int index = random.nextInt(end - start + 1) + start;
        int pivot = nums[index];
        swap(nums, start, index);

        int lt = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] < pivot) {
                lt++;
                swap(nums, lt, i);
            }
        }
        swap(nums, start, lt);

        return lt;
    }


    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * <a href="https://leetcode.cn/problems/longest-substring-without-repeating-characters/">3. 无重复字符的最长子串</a>
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int result = 0;
        int startIndexInclude = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c) && map.get(c) >= startIndexInclude) {
                result = Math.max(result, i - startIndexInclude);
                startIndexInclude = map.get(c) + 1;
            }
            map.put(c, i);
        }

        return Math.max(result, s.length() - startIndexInclude);
    }

    /**
     * <a href="https://leetcode.cn/problems/jump-game-ii/">45. 跳跃游戏 II</a>
     * @param nums 输入: nums = [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * @return
     */
    public static int jump1(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        return jump1(nums, 0, 0, 0);
    }

    private static int jump1(int[] nums, int startIndex, int maxIndex, int step) {
        if (startIndex >= nums.length - 1 || maxIndex >= nums.length - 1) {
            return step;
        }

        int nextMaxIndex = maxIndex;
        for (int i = startIndex; i <= maxIndex; i++) {
            nextMaxIndex = Math.max(nextMaxIndex, nums[i] + i);
        }

        return jump1(nums, maxIndex + 1, nextMaxIndex, step + 1);
    }

    /**
     * <a href="https://leetcode.cn/problems/rotate-array/">189. 轮转数组</a>
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if (nums.length <= 1 || k % nums.length == 0) {
            return;
        }

        k = k % nums.length;
        int[] temp = new int[k];
        System.arraycopy(nums, nums.length - k, temp, 0, k);
        System.arraycopy(nums, 0, nums, k, nums.length - k);
        System.arraycopy(temp, 0, nums, 0, k);
    }

    /**
     * <a href="https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/description/">剑指 Offer 40. 最小的k个数</a>
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr == null || arr.length == 0) {
            return new int[]{};
        }
        Queue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);

        for (int value : arr) {
            queue.add(value);

            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] res = new int[k];
        for (int i = 0; queue.size() > 0; i++) {
            res[i] = queue.poll();
        }

        return res;
    }

    /**
     * <a href="https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/">剑指 Offer 51. 数组中的逆序对</a>
     * @param nums [7,5,6,4]
     * @return 5
     */
    public static int reversePairs(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        sortArray(nums, 0, nums.length - 1, new int[nums.length]);

        return reversePairs;
    }

    private static void sortArray(int[] nums, int start, int end, int[] tempArr) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;

        sortArray(nums, start, mid, tempArr);
        sortArray(nums, mid + 1, end, tempArr);

        if (nums[mid] > nums[mid + 1]) {
            mergeSort(nums, start, end, mid, tempArr);
        }
    }

    static int reversePairs = 0;
    private static void mergeSort(int[] nums, int start, int end, int mid, int[] tempArr) {
        int i = start, j = mid + 1;
        int index = 0;
        while (i <= mid || j <= end) {
            while (i <= mid && j <= end) {
                if (nums[i] <= nums[j]) {
                    tempArr[index++] = nums[i];
                    i++;
                    reversePairs = reversePairs + j - mid - 1;
                } else {
                    tempArr[index++] = nums[j];
                    j++;
                }
            }

            while (i <= mid) {
                reversePairs = reversePairs + end - mid;
                tempArr[index++] = nums[i];
                i++;
            }

            while (j <= end) {
                tempArr[index++] = nums[j];
                j++;
            }
        }

        System.arraycopy(tempArr, 0, nums, start, end - start + 1);
    }

    /**
     * <a href="https://leetcode.cn/problems/course-schedule/">207. 课程表</a>
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> list = map.getOrDefault(prerequisites[i][0], new ArrayList<>());
            list.add(prerequisites[i][1]);
            map.put(prerequisites[i][0], list);
        }

        int[] dp = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (dp[i] == 1) {
                continue;
            }
            if (!check(i, map, dp, new HashSet<>())) {
                return false;
            }
        }
        return true;
    }


    private static boolean check(int i, Map<Integer, List<Integer>> map, int[] dp, Set<Integer> chainList) {
        List<Integer> preCourses = map.getOrDefault(i, new ArrayList<>());
        chainList.add(i);
        if (preCourses.size() > 0) {
            for (int numCourse : preCourses) {
                if (chainList.contains(numCourse)) {
                    return false;
                }
                Set<Integer> set = new HashSet<>(chainList);
                set.add(numCourse);
                if (dp[numCourse] != 1 && !check(numCourse, map, dp, set)) {
                    return false;
                }
            }
        }
        dp[i] = 1;
        return true;
    }

    /**
     * <a href="https://leetcode.cn/problems/remove-k-digits/">402. 移掉 K 位数字</a>
     * 输入：num = "1432219", k = 3
     * 输出："1219"
     * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        if (k >= num.length()) {
            return "0";
        }

        int downIndex = 0;
        for (int i = 0; i < k; i++) {
            for (int j = downIndex; j < num.length(); j++) {
                if (j == num.length() - 1 || num.charAt(j) > num.charAt(j + 1)) {
                    num = num.substring(0, j) + num.substring(j + 1);
                    downIndex = Math.max((j - 1), 0);
                    break;
                }
            }
        }

        while (num.length() > 1 && num.charAt(0) == '0') {
            num = num.substring(1);
        }
        return num;
    }

    /**
     * <a href="https://leetcode.cn/problems/daily-temperatures/">739. 每日温度</a>
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            int temp = temperatures[i];
            if (stack.isEmpty()) {
                stack.push(i);
            } else if (temp > temperatures[stack.peek()]){
                while (!stack.isEmpty() && temp > temperatures[stack.peek()]) {
                    int pre = stack.pop();
                    result[pre] = i - pre;
                }
                stack.push(i);
            } else {
                stack.push(i);
            }
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/diagonal-traverse/">498. 对角线遍历</a>
     * @param mat
     * @return
     */
    public static int[] findDiagonalOrder(int[][] mat) {
        int row = mat.length, col = mat[0].length;
        int i = 0, j = 0;
        int[] result = new int[row * col];
        int curIndex = 0;
        while (i >= 0 && i < row && j >= 0 && j < col) {
            while (i >= 0 && j < col) {
                result[curIndex++] = mat[i][j];
                i--;
                j++;
            }

            i++;
            if (j >= col) {
                i++;
                j--;
            }
            while (i < row && j < col && j >= 0) {
                result[curIndex++] = mat[i][j];
                i++;
                j--;
            }

            j++;
            if (i >= row) {
                i--;
                j++;
            }
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/single-number/">136. 只出现一次的数字</a>
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        boolean repeat = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                repeat = true;
            } else {
                if (!repeat) {
                    return nums[i - 1];
                } else {
                    repeat = false;
                }
            }
        }
        return nums[nums.length - 1];
    }

    /**
     * <a href="https://leetcode.cn/problems/basic-calculator/description/">224. 基本计算器</a>
     * 输入：s = "(1+(4+5+2)-3)+(6+8)
     * @param s 由数字、'+'、'-'、'('、')'、和 ' ' 组成。
     *          '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效) 。
     *          '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)。
     * @return
     */
    public int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            switch (temp) {
                case '(':
                case '+':
                case '-':
                    opStack.push(temp);
                    break;
                case ' ':
                    break;
                case ')':

            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/minimum-size-subarray-sum/">209. 长度最小的子数组</a>
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int count = 0;
        int sum = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return 1;
            }
            sum += nums[i];
            if (sum >= target) {
                while (sum - nums[index] >= target) {
                    sum -= nums[index];
                    index++;
                }
                count = count == 0 ? i - index + 1 : Math.min(count, i - index + 1);
            }

        }

        return count;
    }

    /**
     * <a href="https://leetcode.cn/problems/subarray-sum-equals-k/">560. 和为 K 的子数组</a>
     * 解法2：前缀和+哈希表
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum2(int[] nums, int k) {
        int count = 0; //统计
        int sum = 0;   //前缀和
        HashMap<Integer, Integer> hashMap = new HashMap<>(); //哈希表

        for (int num : nums) { //从左往右
            sum += num; //累加
            if (sum == k) { //从下标 0 累加到 i 刚好等于 k
                count++;
            }
            if (hashMap.containsKey(sum - k)) {
                count += hashMap.get(sum - k);
            }
            hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    /**
     * <a href="https://leetcode.cn/problems/validate-ip-address/">468. 验证IP地址</a>
     * @param queryIP
     * @return
     */
    public static String validIPAddress1(String queryIP) {
        if (queryIP.length() < 7
                || queryIP.charAt(queryIP.length() - 1) == '.'
                ||  queryIP.charAt(queryIP.length() - 1) == ':') {
            return "Neither";
        }

        String[] ipv4Strs = queryIP.split("\\.");
        if (ipv4Strs.length == 4) {
            for (int m = 0; m < 4; m++) {
                String str = ipv4Strs[m];
                if (str.length() == 0 || str.length() > 3) {
                    return "Neither";
                }
                if (str.charAt(0) == '0' && str.length() > 1) {
                    return "Neither";
                }
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);
                    if (c < '0' || c > '9') {
                        return "Neither";
                    }
                }
                int val = Integer.parseInt(str);
                if (val < 0 || val > 255) {
                    return "Neither";
                }
            }

            return "IPv4";
        }

        String[] ipv6Strs = queryIP.split(":");
        if (ipv6Strs.length == 8) {
            for (int i = 0; i < 8; i++) {
                String str = ipv6Strs[i];
                if (str.length() == 0 || str.length() > 4) {
                    return "Neither";
                }
                for (int m = 0; m < str.length(); m++) {
                    char c = str.charAt(m);
                    if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
                        return "Neither";
                    }
                }
            }

            return "IPv6";
        }

        return "Neither";
    }


}
