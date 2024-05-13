package org.example.leetcode;

import java.util.*;

/**
 * @author jialu.yxl
 * @date 09/02/2023 10:29
 */
public class Maths {

    public static void main(String[] args) {
        System.out.println(calculate("-1+(2-11-(2+3))-1+3"));
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
    public static double myPow(double x, int n) {
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
