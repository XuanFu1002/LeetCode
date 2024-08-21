/**
 * 28.
 * Given two strings needle and haystack, return the index of the first occurrence
 * of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 *
 * Example 2:
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 */
package leetcode.utilities;

public class V {

    /**
     * 先用contain, 有的畫在從頭for，如獲first，就用substring equal否
     * => kind of deal with data first, here don't modify, but judge first
     * @param hayStack
     * @param needle
     * @return
     */
    public int strStr(String hayStack, String needle){
        if(!hayStack.contains(needle))
            return -1;

        int i=0;

        for(; i<hayStack.length();i++){
            if(hayStack.charAt(i) == needle.charAt(0)){
                String tmp = hayStack.substring(i,i+needle.length());
                if(tmp.equals(needle))
                    break;
            }
        }
        return i;
    }

    /**
     * Point: Without using contains, directly find equal or not, will be better?
     * @param hayStack
     * @param needle
     * @return
     */
    public int otherApproaches(String hayStack, String needle){
        for(int i=0; i<hayStack.length()-needle.length()+1;i++){
            if(hayStack.charAt(i) == needle.charAt(0)){
                String tmp = hayStack.substring(i,i+needle.length());
                if(tmp.equals(needle))
                    return i;
            }
        }
        return -1;
    }
}
