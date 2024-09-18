package leetcode.utilities;

import java.util.Stack;

public class AX {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                int second = stack.pop();
                int first = stack.pop();
                int result = applyOperation(first, second, token);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    // 判断是否为操作符
    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    // 根据操作符进行运算
    private int applyOperation(int first, int second, String operator) {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                return first / second; // Java 的整数除法会自动截断小数
            default:
                throw new IllegalArgumentException("无效的操作符: " + operator);
        }
    }
}
