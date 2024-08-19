/**
 * 14. Largest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */
package leetcode.utilities;

import java.util.Objects;

public class U {
    /**
     * origin, intuition => 首先就是找最小長度的element，然後trace一次就知道
     * after more consider => 可以不用多此一舉，直接相鄰兩個element去找，然後拿著共通的東西往下找，找到最後為止或是題鹽沒東西了
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs){
        if(strs.length == 1)
            return strs[0];

        String commonPrefix = strs[0];

        for(int i=1; i<strs.length; i++){
            commonPrefix = findCommonPrefix(commonPrefix, strs[i]);
            //if(commonPrefix == "")
            if(Objects.equals(commonPrefix, ""))
                break;
        }

        return commonPrefix;
    }

    public String findCommonPrefix(String a, String b){
//        String commonWords = "";
        StringBuilder stringBuilder = new StringBuilder();
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        // int length = aChars.length > bChars.length? bChars.length:aChars.length;
        int length = Math.min(aChars.length, bChars.length);

        for(int i=0; i<length; i++){
            if(aChars[i] == bChars[i])
                stringBuilder.append(aChars[i]);
            else
                break;
        }
        return stringBuilder.toString();
    }
}
