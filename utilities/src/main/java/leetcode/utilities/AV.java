/**
 * 172. 2024/9/16
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.
 *
 * Example 1:
 * Input: n = 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 *
 * Example 2:
 * Input: n = 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 *
 * Example 3:
 * Input: n = 0
 * Output: 0
 */
package leetcode.utilities;

public class AV {
    /**
     * => uh..題目要的是階乘之後數值，包含幾格0
     * @param n
     * @return
     */
    public int trailingZeroes(int n){
        int factorial = 1;
        int zeros = 0;

        for(int i=n; i>0; i--)
            factorial *= i;

        System.out.println(factorial);

//        while(factorial%10 == 0){           //eg. 13! = 1932053504, 不能直接一看到不是0就確定後面會沒有 => 要trace完一圈
//            factorial/=10;
//            zeros += 1;
//        }
        while(factorial>0){
            if(factorial%10 == 0)
                zeros += 1;
            factorial/=10;
        }
        return zeros;
    }
}
