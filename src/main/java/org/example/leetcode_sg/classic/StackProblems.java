package org.example.leetcode_sg.classic;

import java.util.Stack;

public class StackProblems {

    /**
     * <a href="https://leetcode.com/problems/basic-calculator/?envType=study-plan-v2&envId=top-interview-150">224. Basic Calculator</a>
     * @param s
     * @return
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int sum = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + (s.charAt(i + 1) - '0');
                    i++;
                }
                result += sign * sum;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result = result * stack.pop() + stack.pop();
            }
        }
        return result;
    }


    /**
     * <a href="https://leetcode.com/problems/evaluate-reverse-polish-notation/?envType=study-plan-v2&envId=top-interview-150">150. Evaluate Reverse Polish Notation</a>
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num1 + num2);
            } else if (token.equals("-")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 - num1);
            } else if (token.equals("*")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 * num1);
            } else if (token.equals("/")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 / num1);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }


    /**
     * <a href="https://leetcode.com/problems/simplify-path/?envType=study-plan-v2&envId=top-interview-150">71. Simplify Path</a>
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] splitPaths = path.split("/");
        for (String splitPath : splitPaths) {
            if (splitPath == "") {
                continue;
            }
            if (splitPath.equals("/") || splitPath.equals(".")) {
                continue;
            }
            if (splitPath.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }
            stack.push(splitPath);
        }
        if (stack.isEmpty()) {
            return "/";
        }
        String result = "";
        while (!stack.isEmpty()) {
            result = "/" + stack.pop() + result;
        }
        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/valid-parentheses/?envType=study-plan-v2&envId=top-interview-150">20. Valid Parentheses</a>
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            if (c == ')' && stack.peek() != '(') {
                return false;
            }
            if (c == '}' && stack.peek() != '{') {
                return false;
            }
            if (c == ']' && stack.peek() != '[') {
                return false;
            }
            stack.pop();
        }

        return stack.isEmpty();
    }
}
