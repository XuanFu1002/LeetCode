/**
 * 20. 2024/9/13
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 *
 * Example 1:
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 *
 * Example 3:
 * Input: s = "(]"
 * Output: false
 *
 * Example 4:
 * Input: s = "([])"
 * Output: true
 */
package leetcode.utilities;

import java.util.Stack;

public class AR {
    public boolean isValid(String s) {
        if(s.isEmpty() || s.length() ==1)
            return false;

        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();

        for(int i=0; i<chars.length; i++){
            if(stack.empty()){
                stack.push(chars[i]);
                continue;
            }
//            int tmp = chars[i] - chars[i-1];          //"i-1"並不是stack中top index
            int tmp = chars[i] - stack.peek();       //這裡要多顧慮一個問題就是如果stack為空，就是每次剛好都一組一組, e.g. "()[]{}" => 這樣在做 [ 的時候，peek去看但stack為empty

            if(tmp== 1 || tmp==2){
                stack.pop();
            }else{
                stack.push(chars[i]);
            }
        }
        return stack.empty();
    }
}
