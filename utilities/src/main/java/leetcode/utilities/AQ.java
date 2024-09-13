/**
 * 49. 2024/9/13
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Explanation:
 * There is no string in strs that can be rearranged to form "bat".
 * The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
 * The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
 *
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 */
package leetcode.utilities;

import java.util.*;

public class AQ {
    /**
     * learning from chat
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs){
        Map<String,List<String>> map = new HashMap<>();

        for(String str: strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = Arrays.toString(chars);
            map.putIfAbsent(sorted,new ArrayList<>());
            map.get(sorted).add(str);       //首先這邊我顧慮，如果有重複的怎麼辦? 但其實想多了，不用怕，既然題目沒說情處，那就先當作沒有要過濾這個問題，直接加上去就是了，如果不行後續再調整就好了，不要因為這麼一點小事情就猶豫不決
        }
        return new ArrayList<>(map.values());       //這裡我們拋棄，key不使用，而是單純使用「key所對應的value」，這樣就可以達到用Hash store，然後return List<List<>>
    }
}
