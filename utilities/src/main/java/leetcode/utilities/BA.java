/**
 * 2024/10/13 (Sun.)
 * 17. Letter Combinations of a Phone Number, Medium
 *
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent. Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 *
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Example 2:
 * Input: digits = ""
 * Output: []
 *
 * Example 3:
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 * Constraints:
 * 1) 0 <= digits.length <= 4
 * 2) digits[i] is a digit in the range ['2', '9'].
 */
package leetcode.utilities;

import java.util.ArrayList;
import java.util.List;

public class BA {
    int start = 0;
    int end = 0;
    List<String> list = new ArrayList<>();

    /**
     * chat提供了一個重要概念 =>「刪除」，當recursive要return的時候，原本加過一次的東西，把它清除一次，
     * 如此一來就會回到最初的空值，就不會有subset的問題
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits){
        if(digits == null || digits.isEmpty()){
            return new ArrayList<>();
        }

        //我原先的想法是用char[26]，然後透過judgement去得出for所需的start、end index
        String[] phoneMap = {
                "",     // 0 沒有對應的字母
                "",     // 1 沒有對應的字母
                "abc",  // 2
                "def",  // 3
                "ghi",  // 4
                "jkl",  // 5
                "mno",  // 6
                "pqrs", // 7
                "tuv",  // 8
                "wxyz"  // 9
        };

        List<String> result = new ArrayList<>();
        backtrack(result,digits,phoneMap,0,new StringBuilder());
        return result;
    }

    public void backtrack(List<String> result, String digits, String[] phoneMap, int index, StringBuilder currentCombination){
        // 當到達最大長度的時候return(中斷recursive) => 概念就跟做subset一模一樣
        if(index == digits.length()){
            result.add(currentCombination.toString());
            return;
        }

        String letters = phoneMap[digits.charAt(index)-'0'];    // 這裡因為沒有對digits這個String做轉型，
        // 就直接處理，所以用chartAt(index)的方式去看element，所得到的return是char，i.e.
        // ASCII，所以透過觀察發現，可以透過減去'0'的ASCII，從而反推得到想要的decimal數值

        for(char letter: letters.toCharArray()){
            // 「一層挑選一個element」，排列組合會做的事情
            currentCombination.append(letter);
            // index+1，就是要去看下一個位置
            backtrack(result,digits,phoneMap,index+1,currentCombination);
            // 核心，「移除被加過的東西」=> 而且這裡很重要的是statement放的位置，這裡並不是「+1-1+1-1」，
            // 而是「+1+1-1-1」，因為append是第一個statement，並且下一個statement是recursive，所以
            // 會先go through所有深度的元素做append，然後之後回朔的時候，「加過幾次，就刪除幾次」，
            // => 如此一來，回到最上層的時候，他是拿到空的東西，然後繼續進行下一圈迴圈 => 並且不會計算到
            // subset的原因是因為，最後list.add()的部分，只有最後一層才會assign value，
            // 然後中間層頂多append、deleteChart
            currentCombination.deleteCharAt(currentCombination.length()-1);
        }
    }

    /**
     * 這邊我嘗試複刻先前學過的「go through all subset」的 backtracking結構去設計解法
     * => 但發現一個問題就是，現在這題根本不需用subset，他要的長度就一種，也是以前數學有學過的，
     * eg. {3,3,2}，假設第一個位置的球有三種顏色、第二有三種、第三有二種，那最後有多少種排列組合
     * ，答案是3*3*2種 => 題目求的不是二分法、取與不取的問題，而是「排列組合」的問題
     * (那問題來了，沒想法了cc)
     * @param digit
     */
//    public List<String> backtracking(char[] chars, int times, String accumulate){
//        if(chars.length == times){
//            list.add(accumulate);
//            return list;
//        }
//
//        judgeStarEnd(chars[times]);
//
//        for(int i=start; i<=end; i++){
//            recursive();
//            這裡做下去只會是求subset，是錯的
//        }
//    }

    public void judgeStarEnd(int digit){
        switch(digit){
            case 2:
                start = 0;
                end = 2;
                break;
            case 3:
                start = 3;
                end = 5;
                break;
            case 4:
                start = 6;
                end = 8;
                break;
            case 5:
                start = 9;
                end = 11;
                break;
            case 6:
                start = 12;
                end = 14;
                break;
            case 7:
                start = 15;
                end = 18;
                break;
            case 8:
                start = 19;
                end = 21;
                break;
            case 9:
                start = 22;
                end = 25;
                break;
        }
    }
}
