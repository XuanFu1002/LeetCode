/**
 * 155. 2024/9/18
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 *
 * Example 1:
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 */
package leetcode.utilities;

import java.util.Stack;

/**
 * 最開始考量的getMin怎麼實作 => 起初有想到用額外的DS來儲存觀察，
 * 但是我是打算把所有element都放進去這個新的DS當中，全存的情況想要實現查找minO(1)，就不可能
 * 因為當前data沒有「特性」 => chat提供了一個想法，那就是，存進去新的DS之前，會進行value比對，看是否是更小的value，
 * i.e. 這個minStack只會存當前最min的數值，所以pop掉一個之後，下一個value，必是當前最小，所以這就是和新idea
 */
public class AW {
    Stack<Integer> stack;
    //    int[] min;
    Stack<Integer> minStack;

    public AW(){
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val){
        stack.push(val);
        if(minStack.isEmpty() || val<=stack.peek())
            //這邊加上"="，原因是因為讓後面pop的時候方便，不需要多做額外判斷 => 因為這裡不加上"="的話，
            // 假設有連續2個min value出現，stack理所當然會加進去兩個value，但是如果minStack不加上去的話，
            // 納在pop的時候，還得額外寫statement去觀察main Stack value是不是只剩一組，如果只剩一個才去pop minStack想這就麻煩
            // => 加上等號之後，數量會等價，就可以很簡單操作，況且這也只會出現在連續出現相同min value情況才有
            minStack.push(val);
    }

    public void pop(){
        int x = stack.pop();
        if(x==minStack.peek())
            minStack.pop();
    }

    public int top(){
        return stack.peek();
    }

    public int getMin(){
        return minStack.peek();
    }
}
