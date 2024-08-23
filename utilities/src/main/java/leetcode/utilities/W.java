/**
 * 151.
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters.
 * The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words.
 * The returned string should only have a single space separating the words.
 * Do not include any extra spaces.
 *
 * Example 1:
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 *
 * Example 2:
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 *
 * Example 3:
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 */
package leetcode.utilities;

public class W {
    /**
     * idea => 先用split得到String ary，然後再從此ary的最後一組element開始出發處哩，
     * 處理完之後塞給return string並加上一個空白，最後再將總長度減去1，就可以刪除多餘的空白 => 以前chat教的
     *
     * damn, 沒看清楚題目，那邊說東我做西，雖說功能是對的 => 這裡很簡單，單字的整個排序相反而已
     * @param s
     * @return
     */
    public String reverseWords(String s){
        String[] words = s.trim().split("\\s+");       //1. split在數學意義上就是「間隔」 2. 然後為了避免不必要的麻煩，所以用trim()將前後多於空白消除
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=words.length-1; i>=0; i--)
            stringBuilder.append(words[i]).append(" ");

        //-1是最後一個element index，而此時是多餘的空白，所以在多減去1
        //喔幹，然後substring又會自動減去1，所以這裡只需要減去1即可
        return stringBuilder.substring(0,stringBuilder.length()-1);

//        for(int i=words.length-1; i>=0; i--){
//            char[] chars = words[i].toCharArray();
//            for(int j=0; j<words[i].length()/2; j++){
//                char tmp = chars[j];
//                chars[j] = chars[chars.length-1-j];
//                chars[chars.length-1-j] = tmp;
//            }
//            stringBuilder.append(chars).append(" ");
//        }
//
//        return stringBuilder.substring(0, stringBuilder.length()-2);
    }
}
