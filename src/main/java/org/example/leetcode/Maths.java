package org.example.leetcode;

import java.util.*;

/**
 * @author jialu.yxl
 * @date 09/02/2023 10:29
 */
public class Maths {

    public static void main(String[] args) {
        // System.out.println(findNthDigit(110));

        System.out.println(add36Strings("1b", "2x"));
    }

    /**
     * <a href="https://leetcode.cn/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/">LCR 158. 库存管理 II</a>
     * 仓库管理员以数组 stock 形式记录商品库存表。stock[i] 表示商品 id，可能存在重复。请返回库存表中数量大于 stock.length / 2 的商品 id。
     * 示例 1:
     * 输入: stock = [6, 1, 3, 1, 1, 1]
     * 输出: 1
     * @param stock 1 <= stock.length <= 50000
     * @return
     */
    public int inventoryManagement(int[] stock) {
        int candidate = stock[0];
        int count = 0;
        for (int i = 0; i < stock.length; i++) {
            if (count == 0) {
                candidate = stock[i];
            }
            count = candidate == stock[i] ? count + 1 : count - 1;
        }
        return candidate;
    }

    /**
     * <a href="https://mp.weixin.qq.com/s/XcKQwnwCh5nZsz-DLHJwzQ/">字节高频题补充——36进制加法</a>
     * 36进制由0-9，a-z，共36个字符表示。
     * 要求按照加法规则计算出任意两个36进制正整数的和，如1b + 2x = 48  （解释：47+105=152）
     * 要求：不允许使用先将36进制数字整体转为10进制，相加后再转回为36进制的做法
     * @param num1
     * @param num2
     * @return
     */
    public static String add36Strings(String num1, String num2) {
        Map<Character, Integer> map1 = new HashMap<>(36);
        Map<Integer, Character> map2 = new HashMap<>(36);
        String s = "0123456789abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < s.length(); i++) {
            map1.put(s.charAt(i), i);
            map2.put(i, s.charAt(i));
        }
        int i = num1.length() - 1, j = num2.length() - 1;
        int add = 0, sum = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (i >= 0 || j >= 0) {
            sum += add;
            if (i >= 0) {
                sum += map1.get(num1.charAt(i--));
            }
            if (j >= 0) {
                sum += map1.get(num2.charAt(j--));
            }
            if (sum >= 36) {
                sum %= 36;
                add = 1;
            } else {
                add = 0;
            }
            stringBuilder.insert(0, map2.get(sum));
            sum = 0;
        }
        if (add == 1) {
            stringBuilder.insert(0, "1");
        }
        return stringBuilder.toString();
    }


    /**
     * <a href="https://leetcode.cn/problems/valid-triangle-number/">611. Valid Triangle Number</a>
     * Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
     * Example 1:
     * Input: nums = [2,2,3,4]
     * Output: 3
     * Explanation: Valid combinations are:
     * 2,3,4 (using the first 2)
     * 2,3,4 (using the second 2)
     * 2,2,3
     * @param nums 1 <= nums.length <= 1000 0 <= nums[i] <= 1000
     * @return
     */
    public static int triangleNumber(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);

        int sum = 0;
        int k;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                sum = nums[i] + nums[j];
                k = j + 1;
                while (k < nums.length && nums[k] < sum) {
                    result++;
                    k++;
                }
            }
        }
        return result;
    }


    /**
     * <a href="https://leetcode.cn/problems/nth-digit/">400. 第 N 位数字</a>
     * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字。
     * @param n 1 <= n <= 2^31 - 1
     * @return
     */
    public static int findNthDigit(int n) {
        int numCount = 1;
        long startNum = 1;
        long endNum = startNum * 10 - 1;
        long startSerialNum = 1;
        long endSerialNum = startSerialNum + (endNum - startNum + 1) * numCount - 1;
        while (n > endSerialNum) {
            numCount++;
            startNum = endNum + 1;
            endNum =  startNum * 10 - 1;
            startSerialNum = endSerialNum + 1;
            endSerialNum = startSerialNum + (endNum - startNum + 1) * numCount - 1;
        }
        int divideNum = 1;
        int tmp = numCount;
        while (tmp-- > 1) {
            divideNum *= 10;
        }
        while (divideNum > 1) {
            endNum = startNum + divideNum - 1;
            endSerialNum = startSerialNum + (endNum - startNum + 1) * numCount - 1;
            if (n > endSerialNum) {
                startNum = endNum + 1;
                startSerialNum = endSerialNum + 1;
            } else {
                divideNum /= 10;
            }
        }

        while (startSerialNum + numCount <= n) {
            startSerialNum += numCount;
            startNum++;
        }

        String numStr = String.valueOf(startNum);
        return Integer.parseInt(String.valueOf(numStr.charAt(n - (int) startSerialNum)));
    }



    /**
     * <a href="https://leetcode.cn/problems/super-egg-drop/description/">887. Super Egg Drop</a>
     * You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.
     * You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break, and any egg dropped at or below floor f will not break.
     * Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can no longer use it. However, if the egg does not break, you may reuse it in future moves.
     * Return the minimum number of moves that you need to determine with certainty what the value of f is.
     * Input: k = 1, n = 2
     * Output: 2
     * Explanation:
     * Drop the egg from floor 1. If it breaks, we know that f = 0.
     * Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
     * If it does not break, then we know f = 2.
     * Hence, we need at minimum 2 moves to determine with certainty what the value of f is.
     * @param k 1 <= k <= 100
     * @param n 1 <= n <= 104
     * @return
     */
    public static int superEggDrop(int k, int n) {
        int t = 1;
        while (superEggDropFunc(k, t) < n + 1) {
            t++;
        }
        return t;
    }

    /**
     *
     * @param k 鸡蛋数量
     * @param t 机会数量
     * @return 能计算出来的楼层 F 0 <= F; 1 <= N
     */
    public static int superEggDropFunc(int k, int t) {
        // 只有一个鸡蛋，那么从1楼开始扔，能计算出t + 1层楼（含楼层0）；
        // 只有一次机会，从1开始扔，只能计算出两层楼；从1楼扔，碎了，则 F=0, N=1；没碎，则 F=1, N=1；
        if (k == 1 || t == 1) {
            return t + 1;
        }
        // superEggDropFunc(k - 1, t - 1) 蛋碎了，鸡蛋-1，机会-1
        // superEggDropFunc(k, t - 1) 蛋没碎，机会-1
        return superEggDropFunc(k - 1, t - 1) + superEggDropFunc(k, t - 1);
    }

        /**
         * <a href="https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/">LCR 187. 破冰游戏</a>
         * 社团共有 num 位成员参与破冰游戏，编号为 0 ~ num-1。成员们按照编号顺序围绕圆桌而坐。
         * 社长抽取一个数字 target，从 0 号成员起开始计数，排在第 target 位的成员离开圆桌，且成员离开后从下一个成员开始计数。
         * 请返回游戏结束时最后一位成员的编号。
         * 示例 1：
         * 输入：num = 7, target = 4
         * 输出：1
         * @param num
         * @param target
         * @return
         */
    public int iceBreakingGame(int num, int target) {
        int index = 0;
        // 约瑟夫环
        for (int i = 2; i <= num; i++) {
            index = (index + target) % i;
        }
        return index;
    }

    /**
     * <a href="https://leetcode.cn/problems/powx-n/">50. Pow(x, n)</a>
     * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
     * Input: x = 2.00000, n = -2
     * Output: 0.25000
     * Explanation: 2-2 = 1/22 = 1/4 = 0.25
     * @param x -100.0 < x < 100.0
     * @param n -231 <= n <= 231-1
     * @return
     */
    public static double myPow(double x, int n) {
        if (x == 1 || x == 0) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        if (x == -1) {
            return Math.abs(n) % 2 == 0 ? 1 : -1;
        }

        long n1 = n;
        if (n < 0) {
            x = 1 / x;
            n1 = -n1;
        }
        return myPowSimple(x, n1);
    }

    private static double myPowSimple(double x, long n) {
        if (n == 1) {
            return x;
        }
        double half = myPowSimple(x, n / 2);
        if (n % 2 == 1) {
            return half * half * x;
        } else {
            return half * half;
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/reverse-integer/">7. Reverse Integer</a>
     * Given a signed 32-bit integer x, return x with its digits reversed.
     * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
     * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
     * Example 1:
     * Input: x = 123
     * Output: 321
     * Example 2:
     * Input: x = -123
     * Output: -321
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int result = 0;

        boolean neg = x < 0;
        String s = String.valueOf(Math.abs(x));
        int tmpResult = Integer.parseInt(String.valueOf(s.charAt(s.length() - 1)));
        result = neg ? -tmpResult : tmpResult;
        for (int i = s.length() - 2; i >= 0; i--) {
            int tmp = Integer.parseInt(String.valueOf(s.charAt(i)));
            tmpResult = result * 10 + (neg ? -tmp : tmp);
            if (tmpResult / 10 != result) {
                return 0;
            }
            result = tmpResult;
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/single-number/">136. Single Number</a>
     * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            num = num ^ nums[i];
        }
        return num;
    }

    /**
     * <a href="https://leetcode.cn/problems/basic-calculator/description/">224. 基本计算器</a>
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
     * 1 <= s.length <= 3 * 105
     * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
     * s 表示一个有效的表达式
     * '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
     * '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
     * 输入中不存在两个连续的操作符
     * 每个数字和运行的计算将适合于一个有符号的 32位 整数
     * @param s
     * @return
     */
    public static int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c == '-') {
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("0-");
                } else if (stringBuilder.charAt(stringBuilder.length() - 1) == '(') {
                    stringBuilder.append("0-");
                } else {
                    stringBuilder.append("-");
                }
            } else {
                stringBuilder.append(c);
            }
        }
        s = stringBuilder.toString();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                    opStack.push(c);
                    break;
                case '+':
                case '-' :
                    if (!opStack.isEmpty() && (opStack.peek() == '+' || opStack.peek() == '-')) {
                        calculateDoOp(numStack, opStack);
                    }
                    opStack.push(c);
                    break;
                case ')':
                    while (opStack.peek() != '(') {
                        calculateDoOp(numStack, opStack);
                    }
                    opStack.pop();
                    break;
                case ' ':
                    break;
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append(c);
                    while (++i < s.length() && s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9) {
                        sb.append(s.charAt(i));
                    }
                    i--;
                    numStack.push(Integer.parseInt(sb.toString()));
                    break;
            }
        }

        while (!opStack.isEmpty()) {
            calculateDoOp(numStack, opStack);
        }
        return numStack.pop();
    }

    private static void calculateDoOp(Stack<Integer> numStack, Stack<Character> opStack) {
        int num1 = numStack.pop();
        int num2 = 0;
        if (!numStack.isEmpty()) {
            num2 = numStack.pop();
        }
        Character op = opStack.pop();
        if (op == '+') {
            numStack.push(num1 + num2);
        } else if (op == '-') {
            numStack.push(num2 - num1);
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/maximum-product-subarray/">152. 乘积最大子数组</a>
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组
     * （该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * 测试用例的答案是一个 32-位 整数。
     * 示例 2:
     * 输入: nums = [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums.length <= 1) {
            return nums[0];
        }

        int result = nums[0];
        int posMax = nums[0];
        int negMax = nums[0];
        int tmpMax, tmpMin;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            tmpMax = posMax;
            tmpMin = negMax;
            posMax = Math.max(num, Math.max(tmpMax * num, tmpMin * num));
            negMax = Math.min(num, Math.min(tmpMax * num, tmpMin * num));
            result = Math.max(result, Math.max(posMax, negMax));
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/basic-calculator-ii/">227. 基本计算器 II</a>
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * 整数除法仅保留整数部分。
     * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
     * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
     * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
     * @param s
     * @return
     */
    public static int calculate2(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            stringBuilder.append(s.charAt(i));
        }
        s = stringBuilder.toString();

        if (s.length() <= 2) {
            return Integer.parseInt(s);
        }

        if (s.charAt(0) == '-') {
            s = "0" + s;
        }

        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> opStack = new ArrayDeque<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c - '0' >= 0 && c - '0' <= 9) {
                StringBuilder sb = new StringBuilder();
                while (i < s.length() && s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9) {
                    sb.append(s.charAt(i));
                    i++;
                }
                numStack.offerLast(Integer.parseInt(sb.toString()));
            } else if (c == '+' || c == '-') {
                opStack.offerLast(c);
                i++;
            } else if (c == '*' || c == '/') {
                i++;
                StringBuilder sb = new StringBuilder();
                while (i < s.length() && s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9) {
                    sb.append(s.charAt(i));
                    i++;
                }
                int nextNum = Integer.parseInt(sb.toString());
                if (c == '*') {
                    numStack.offerLast(numStack.pollLast() * nextNum);
                } else {
                    numStack.offerLast(numStack.pollLast() / nextNum);
                }
            } else {
                i++;
            }
        }

        int result = numStack.pollFirst();
        while (!opStack.isEmpty()) {
            Character op = opStack.pollFirst();
            if (op == '+') {
                result += numStack.pollFirst();
            } else {
                result -= numStack.pollFirst();
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/implement-rand10-using-rand7/">470. 用 Rand7() 实现 Rand10()</a>
     * 给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。
     * 你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。
     * 每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数。
     * @return
     */
    public int rand10() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7();
            if (num <= 40) {
                return num % 10 + 1;
            } else {
                num = (num - 40 - 1) * 7 + rand7();
                if (num <= 60) {
                    return num % 10 + 1;
                }
            }
        }
    }

    public int rand7() {
        Random random = new Random();
        return random.nextInt(7) + 1;
    }


    /**
     * <a href="https://leetcode.cn/problems/multiply-strings/">43. 字符串相乘</a>
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        String result = "";
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = Integer.parseInt(String.valueOf(num1.charAt(i)));
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = Integer.parseInt(String.valueOf(num2.charAt(j)));
                int mul = n1 * n2;
                int zeroCount = num1.length() - i + num2.length() - j - 2;
                result = multiplySum(result, String.valueOf(mul), zeroCount);
            }
        }

        return result;
    }

    public String multiplySum(String num1, String num2, int num2Zero) {
        StringBuilder num2Builder = new StringBuilder(num2);
        while (num2Zero-- > 0) {
            num2Builder.append("0");
        }
        num2 = num2Builder.toString();

        if ("".equals(num1)) {
            return num2;
        }

        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int add = 0;
        String result = "";
        int sum = 0;
        while (i >= 0 && j >= 0) {
            sum = Integer.parseInt(String.valueOf(num1.charAt(i))) + Integer.parseInt(String.valueOf(num2.charAt(j))) + add;
            add = sum / 10;
            sum = sum % 10;
            result = sum + result;
            i--;
            j--;
        }

        while (i >= 0) {
            sum = Integer.parseInt(String.valueOf(num1.charAt(i))) + add;
            add = sum / 10;
            sum = sum % 10;
            result = sum + result;
            i--;
        }

        while (j >= 0) {
            sum = Integer.parseInt(String.valueOf(num2.charAt(j))) + add;
            add = sum / 10;
            sum = sum % 10;
            result = sum + result;
            j--;
        }

        if (add == 1) {
            result = add + result;
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/sqrtx/">69. x 的平方根 </a>
     * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
     * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int result = 0;
        int l = 1, r = x;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid <= x / mid) {
                result = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/angle-between-hands-of-a-clock/description/">1344. 时钟指针的夹角</a>
     * @param hour
     * @param minutes
     * @return
     */
    public double angleClock(int hour, int minutes) {
        double minAngle = (double) minutes / 60;
        double hourAngle = (double) hour / 12 + minAngle / 12;
        double angle = Math.abs(minAngle - hourAngle);
        angle = angle > 0.5 ? 1 - angle : angle;
        return  angle * 360;
    }

    /**
     * 十进制转换成二进制
     * @param num
     * @return
     */
    public static String convertBase10toBase2(int num) {
        StringBuilder stringBuilder = new StringBuilder();

        while (num != 0) {
            stringBuilder.append(num % 2);
            num = num / 2;
        }

        return stringBuilder.reverse().toString();
    }

    /**
     * <a href="https://leetcode.cn/problems/max-points-on-a-line/description/">149. 直线上最多的点数</a>
     */
    public int maxPoints(int[][] points) {
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            int[] m = points[i];
            int curResult = 0;
            Map<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                int[] n = points[j];
                int x = m[0] - n[0];
                int y = m[1] - n[1];
                int gcd = gcd(x, y);
                String key = x / gcd + "_" + y / gcd;
                map.put(key, map.getOrDefault(key, 0) + 1);
                curResult = Math.max(curResult, map.get(key));
            }
            result = Math.max(result, curResult + 1);
        }

        return result;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }


    public static int findOneCountInBinary(int n) {
        int result = 0;
        while (n != 0) {
            n = n & (n >> 1);
            result++;
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/">剑指 Offer 62. 圆圈中最后剩下的数字</a>
     * @param n
     * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
     *
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     * @param m
     * @return
     */
    public static int lastRemaining(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = lastRemaining(n - 1, m);
        return (m + x) % n;
    }

    /**
     * <a href="https://leetcode.cn/problems/powx-n/">50. Pow(x, n)</a>
     * @param x
     * @param n
     * @return
     */
    public static double myPow1(double x, int n) {
        if (n == 0 || x == 1) {
            return 1;
        }
        if (n < 0 && x != 0) {
            x = 1 / x;
            n = -n;
        }

        return singlePow(x, n);
    }

    private static double singlePow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double half = singlePow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
