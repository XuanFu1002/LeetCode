/**
 * 66.  2024/9/16
 * You are given a large integer represented as an integer array digits, where each digits[i]
 * is the ith digit of the integer. The digits are ordered from most significant to least significant
 * in left-to-right order. The large integer does not contain any leading 0's.
 *
 * Increment the large integer by one and return the resulting array of digits.
 *
 * Example 1:
 * Input: digits = [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Incrementing by one gives 123 + 1 = 124.
 * Thus, the result should be [1,2,4].
 *
 * Example 2:
 * Input: digits = [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 * Incrementing by one gives 4321 + 1 = 4322.
 * Thus, the result should be [4,3,2,2].
 *
 * Example 3:
 * Input: digits = [9]
 * Output: [1,0]
 * Explanation: The array represents the integer 9.
 * Incrementing by one gives 9 + 1 = 10.
 * Thus, the result should be [1,0].
 */
package leetcode.utilities;

public class AU {
    public int[] plusOne(int[] digits) {
        int[] carry = new int[digits.length+1];
        carry[0] = 1;

        for(int i=digits.length-1; i>=0; i--){
            if(digits[i]!=9){
//                digits[i] = i+1;      => 白癡，是value+1，我在那邊index+1
                digits[i] += 1;
                return digits;
            }else{
                digits[i] = 0;
            }
        }
        return carry;
    }

    /**
     * 69. 2024/9/16
     * calculate square root
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        return (int)Math.sqrt(x);
    }

    /**
     * 50.  2024/9/17
     * just test whether u know Math.pow() API or not => which let u count n power of x, x^n
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        return Math.pow(x,n);
    }
}
