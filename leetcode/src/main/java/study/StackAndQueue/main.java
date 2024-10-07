package study.StackAndQueue;

import java.util.*;

public class main {
    public boolean isValid(String s) {
        Deque<Character> stock = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stock.push(')');
            else if (s.charAt(i) == '{') stock.push('}');
            else if (s.charAt(i) == '[') stock.push(']');
            else if (stock.isEmpty() || s.charAt(i) != stock.peek()) return false;
            else stock.pop();
        }
        return stock.isEmpty();
    }

    /**
     * 150. 逆波兰表达式求值
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> stock = new LinkedList<>();
        for (int i = 0; i < tokens.length; i++) {
            if (Objects.equals(tokens[i], "+")){
                int temp1 = stock.pop();
                int temp2 = stock.pop();
                stock.push(temp1 + temp2);
            } else if (Objects.equals(tokens[i], "*")) {
                int temp1 = stock.pop();
                int temp2 = stock.pop();
                stock.push(temp1 * temp2);
            } else if (Objects.equals(tokens[i], "/")) {
                int temp1 = stock.pop();
                int temp2 = stock.pop();
                stock.push(temp2 / temp1);
            } else if (Objects.equals(tokens[i], "-")) {
                int temp1 = stock.pop();
                int temp2 = stock.pop();
                stock.push(temp2 - temp1);
            } else {
                stock.push(Integer.valueOf(tokens[i]));
            }
        }
        return stock.peek();
    }

    /**
     * 239. 滑动窗口最大值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

    }
}
