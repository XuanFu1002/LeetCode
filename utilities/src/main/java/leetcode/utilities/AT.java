/**
 * 9. 2024/9/14
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 *
 * Example 1:
 *
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * Example 2:
 *
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 *
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 */
package leetcode.utilities;

public class AT {
    /**
     * 清楚回文(palindrome)特性之後，很容易設計 => 就只需要兩個variable去控制頭尾index，然後必須相等，能安全走完就是回文
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        if(chars.length == 1 || chars.length == 0)
            return chars.length == 1;       //看leetCode上面別人用過的方法，還蠻有趣不錯的 => 我這邊就稍加改變 => 目標1的時候要true，0的時候要回傳false

        int i = 0;
        int j = chars.length -1;

        for(;i<j;){
            if(!(chars[i++] == chars[j--]))
                return false;
        }
        return true;
    }
}
