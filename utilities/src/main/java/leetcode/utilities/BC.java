/**
 * 2024/10/15 (Tues.)
 * 2938. Separate Black and White Balls, Medium
 *
 * There are n balls on a table, each ball has a color black or white.
 *
 * You are given a 0-indexed binary string s of length n, where 1 and 0 represent
 * black and white balls, respectively.
 *
 * In each step, you can choose two adjacent balls and swap them.
 *
 * Return the minimum number of steps to group all the black balls to the right and all
 * the white balls to the left.
 *
 * Example 1:
 * Input: s = "101"
 * Output: 1
 * Explanation: We can group all the black balls to the right in the following way:
 * - Swap s[0] and s[1], s = "011".
 * Initially, 1s are not grouped together, requiring at least 1 step to group them to the right.
 *
 * Example 2:
 * Input: s = "100"
 * Output: 2
 * Explanation: We can group all the black balls to the right in the following way:
 * - Swap s[0] and s[1], s = "010".
 * - Swap s[1] and s[2], s = "001".
 * It can be proven that the minimum number of steps needed is 2.
 *
 * Example 3:
 * Input: s = "0111"
 * Output: 0
 * Explanation: All the black balls are already grouped to the right.
 *
 * Constraints:
 * 1) 1 <= n == s.length <= 105
 * 2) s[i] is either '0' or '1'.
 */
package leetcode.utilities;

import java.util.List;
import java.util.ArrayList;

public class BC {
    /**
     * 功能沒問題，但是沒有真正理解題意 => 題目說的是「相鄰、兩兩一組」進行judge
     * ，但是我在那邊跳躍式control index => 固然能找到更少step，但不符合題意
     * @param s
     * @return
     */
    public long minimumSteps(String s){
        char[] chars = s.toCharArray();
        int steps = 0;
        int i = 0;
        int j = s.length()-1;

        for(;i<j;){     //不用探討「i==j」是因為，不論該index最後是0或是1，都沒差，他只會是臨界值而已，也沒東西去交換
//            if(chars[i]!=0){    damn.. => 不能用0，要用'0'，不然會被當作要去compare ASCII數值
            if(chars[i]!='0' && chars[j]!='1'){
                char tmp = chars[i];
                chars[i] = chars[j];
                chars[j] = tmp;
                steps++;
            }
            if(chars[i]=='0'){
                i++;
            }
            if(chars[j]=='1'){
                j--;
            }
//            if(s.charAt(i)!=1&&s.charAt(j)!=0){
//                char tmp = s.charAt(i);
//                s.charAt(i) = s.charAt(j);  //哎呀~，沒有這種直接assign value to method的syntax呀，idiot
//                s.charAt(j) = tmp;          //，如果要透過method去modify value，正確的作法都是透過「parameter」
//                                        //，況且別忘了一個最初的概念，method的誕生，其中一個用意就是為了避免
//                                        //user權限過高，而隨意修改value，導致不預期情控、或是惡意修改，
//                                    // 所以也就出現了getter、setter，透過事先設計好、核准的方式，去與value作互動
//                /*
//                    另一個重要觀念就是，別忘了String value是Static，要修改的話，背後都是宣告一個新的String variable
//                    ，所以想修改String value常見的作法都是，先做StringBuilder然後修改完之後，再轉乘String
//                    ，再來就是，把String用toCharAry()，然後去操作char[] => 所以這就是為何，不使用charAt()，常見使用
//                    toCharArray()去操作的原因
//                 */
//            }
        }

        return steps;
    }

    public long otherApproach(String s){
        int steps = 0;
        char[] chars = s.toCharArray();
        List<Integer> list = new ArrayList<>();
        String tmp = "";

        for(int i=chars.length-1; i>=1; i--){
            tmp = String.valueOf(chars[i-1])+String.valueOf(chars[i]);
            switch (tmp){
                case "01":
                    if(!list.isEmpty()){
                        char c = chars[i];
                        chars[i] = chars[i+list.size()-1];
                        chars[i+list.size()-1] = c;
                        steps += list.size();
                    }
                    break;
                case "10":
                    if(list.isEmpty()){
                        list.add(0);
                    }
                    char c = chars[i-1];
                    chars[i-1] = chars[i+list.size()-1];
                    chars[i+list.size()-1] = c;
                    steps += list.size();
                    break;
                case"00":
                    if(list.isEmpty()){
                        list.add(0);
                    }
                    list.add(0);
                    break;
            }
        }

        return steps;
    }
}
