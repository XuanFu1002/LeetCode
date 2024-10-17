/**
 * 2024/10/17 (Thur.)
 * 670. Maximum Swap, Medium
 *
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 *
 * Return the maximum valued number you can get.
 *
 * Example 1:
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 *
 * Example 2:
 * Input: num = 9973
 * Output: 9973
 * Explanation: No swap.
 *
 * Constraint：
 * 1) 0 <= num <= 108
 */
package leetcode.utilities;

import javafx.scene.control.ListView;

import javax.swing.text.EditorKit;
import java.util.PriorityQueue;

public class BD {
    /**
     * 題目當中提到「at most once」 => 是一個常見的術語，特別是在計算機科學、網路協議、
     * 程式設計和資料處理等領域。它表示某個操作或事件最多發生一次，但也可能不發生 => 簡單來說就是「最多一次」
     * @param num
     * @return
     * => at most 英文意思"最多" => 所以at most once，就是"最多一次"
     */
    public int maximumSwap(int num) {
        String s = Integer.toString(num);
        char[] digits = s.toCharArray();

        if(digits.length==1){
            return num;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);

        for(char digit: digits){
            maxHeap.add(digit-48);      //or digit-'0' => 因為char是字元，所以這邊是"字元0~9"，
                                // 非數值的0~9，然後ASCII要想表達成數值的話，他的Value是按照ASCII表
                                // 定義的數值做使用，簡單來說就是一個mapping table，然後要想表達成我們
                                // 現實中的數值的話，減去他們的公差，就可以了，這邊0是48，扣除這個bias，
                                // 就可以得到0~9的int
        }

        for(int i=0; i<digits.length-1; i++){
            if(digits[i]-48 == maxHeap.peek()){
                maxHeap.poll();
                continue;
            }
//            for(int j=i+1; j<digits.length; j++){
            for(int j=digits.length-1; j>i; j--){
                if(digits[j]-48 == maxHeap.peek()){
//                    if(j!=digits.length-1){
//                        if(digits[j] == digits[j+1]){
//                            continue;
//                        }
//                    }
                    char tmp  = digits[i];
                    digits[i] = digits[j];
                    digits[j] = tmp;
                    return Integer.parseInt(String.valueOf(digits));
                }
            }
        }
        return Integer.parseInt(String.valueOf(digits));
    }
}
