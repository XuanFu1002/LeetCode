/**
 * 202.
 * Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 *  a) Starting with any positive integer, replace the number by the sum of the squares of its digits.
 *  b) Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a
 *      cycle which does not include 1.
 *  c) Those numbers for which this process ends in 1 are happy.
 *
 *  Return true if n is a happy number, and false if not.
 *
 * Example 1:
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 *
 * Example 2:
 * Input: n = 2
 * Output: false
 */
package leetcode.utilities;

import java.util.Set;
import java.util.HashSet;

public class AN {
    /**
     * 首要就是看懂題目 => 題目的意思是，將一個數字的每一位數字抓出來，個別作平方然後相加，沒有digit概念，只有數字概念
     * 問完chat之後，才更清楚題目的索求，並且停止條件就是循環...，都來不及思考、觀察
     * origin solving idea => double for loop, intuition
     * @param n
     * @return
     * => 結果又是沒有符合general case，思路沒問題，但就是判斷標準，這裡不一定是算出第一次這個值作為重複點，也有可能是別的地方，所以這裡寫的就不夠彈性，除非你很確定只有一種Case
     * => two improvement place, 1) tidy up code, can use function calling    2) 不一定是第一筆作循環點
     */
    public boolean isHappy(int n){
//        Map<Integer,Integer> happyNo = new HashMap<Integer,Integer>();
        int initSquare = 0;
        int quotient = n;
        int remainder = n;

        do{
            remainder = quotient %10;
            quotient /= 10;
//            remainder %= 10;      => 依賴的是下一個quotient去做運算，完全與remainder無關，所以不能用+=
            initSquare += remainder * remainder;
        }while(quotient!=0);

        if(initSquare == 1)
            return true;

//        int tmp = n; => 這裡如果assign n的話，那就又變成計算一次 最一開始的digit number square summation，所以就不make sense，因為只會最一次，且每次保證等於initSquare
        int tmp = initSquare;
        int count;

//        while(tmp!=initSquare && tmp!=1{      => 有鑑於要跳過第一次的計算，tmp assign了initSquare，所以用do while，改下次遇見時候才stop
        do{
            quotient = tmp;
            remainder = tmp;
            count = 0;
            do{
                remainder = quotient %10;
                quotient /= 10;
//                remainder %= 10;
                count += remainder * remainder;
            }while(quotient!=0);
            tmp = count;
        }while(tmp!=initSquare && tmp!=1);

//        if(tmp==initSquare)
//            return false;
//        else return true;
        return tmp != initSquare;
    }

    /**
     * 由於不確定是哪一筆data最為circle的起點，所以不能單單用primitive valuable，要改用可以儲存多筆紀錄，並提供查找的功能 => chat說用「hashset」
     * @param n
     * @return
     */
    public boolean otherApproach(int n){
        Set<Integer> seen = new HashSet<>();

        while(n!=1 && !seen.contains(n)){       //條件，若是這一組數沒出現過 && 不是1的話繼續做 => judge出現否，避免進入circle；若是value是，此為題目所求，所以不用繼續算
            seen.add(n);
            n = sumOfSquares(n);
        }
        return n==1;
    }

    public int sumOfSquares(int n){
        int sum = 0;

        while(n>0){
            int digit = n %10;
            sum += digit *digit;
            n /= 10;
        }
        return sum;
    }
}
