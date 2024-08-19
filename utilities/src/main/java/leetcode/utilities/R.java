/**
 * 13.
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * For example, 2 is written as II in Roman numeral, just two ones added together.
 * 12 is written as XII, which is simply X + II. The number 27 is written as XXVII,
 * which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 *
 * Example 1:
 * Input: s = "III"
 * Output: 3
 * Explanation: III = 3.
 *
 * Example 2:
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 *
 * Example 3:
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 */

// 不難看出，「5」為一個進位的循環 => 所以用 %5, 取餘 => 錯，角度想錯，應該是以10為基礎，因為source的數值是10為基底，藉此才可以找出4 & 9 case, 不然用%5都是同個case，無法直接區分
// 喔乾，是要string轉int，一直以為是int轉String XDD

package leetcode.utilities;

import jdk.nashorn.internal.ir.SplitReturn;

import java.util.ArrayList;

public class R {
    /**
     * Key：不要忘了可以先對數據做處理，讓他產生一定特性，或是方便處理的現象，就是事前加工呀 => 會卡那麼久，就是因為我試圖source data直接for loop去看，然後就想複雜了
     * @param sNumber
     * @return intNumber
     */
    public int romanToInt(String sNumber){
        int intNumber = 0;

        sNumber = sNumber.replaceAll("IV", "IIII").replaceAll("IX","VIIII");
        sNumber = sNumber.replaceAll("XL", "XXXX").replaceAll("XC","LXXXX");
        sNumber = sNumber.replaceAll("CD", "CCCC").replaceAll("CM","DCCCC");

        char[] chars = sNumber.toCharArray();

        for(int i=0; i<chars.length; i++){
            switch(chars[i]){
                case 'M':
                    intNumber += 1000;
                    break;
                case 'D':
                    intNumber += 500;
                    break;
                case 'C':
                    intNumber += 100;
                    break;
                case 'L':
                    intNumber += 50;
                    break;
                case 'X':
                    intNumber += 10;
                    break;
                case 'V':
                    intNumber += 5;
                    break;
                case 'I':
                    intNumber += 1;
                    break;
            }
        }

        return intNumber;
    }

    public int otherApproach(String s){
        int number = 0;
        int tmp = 0;
        int current = 0;

        // notice here, use second variable to keep the value, provide for comparison, instead of using 「i+1」, avoid 「indexOutOfBound」
        // 所以index從0到last沒問題
        // => 當前「大於」前者，前一輪要扣除兩次，第一次上上一輪多加，第二次是因為本來就該扣除，所以times 2
        for(int i=0; i<s.length(); i++){
            current = judgement(s.charAt(i));
            if(current > tmp){
                number += current - (2 * tmp);
            }else{
                number += current;
            }
            tmp = current;
        }
        return number;
    }

    public int judgement(char c){
        switch (c){
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
