/**
 * 242.
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 */
package leetcode.utilities;

import java.util.Map;
import java.util.HashMap;

public class AL {
    /**
     * anagram => 相同字母，不同順序 => 集合是一樣的，只是順序可以不同而已
     * 既然說集合是同一個，那每個東西的次數必定相同，也就可以計算frequency
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t){
        if(s.length()!=t.length())
            return false;

        Map<Character,Integer> anagram = new HashMap<>();

        for(char c:s.toCharArray())     //這裡可以選用for each的原因是因為，hashmap這個DS用到的是key，而不是index，所以可以用index
            anagram.put(c,anagram.getOrDefault(c,0)+1);

        for(char c:t.toCharArray()){
            if(anagram.containsKey(c)){
                if(anagram.get(c)!=0)
                    anagram.put(c,anagram.get(c)-1);  //這裡不用getOrDefault的原因是因為，前面條件都塞選過了，這裡必定是有key&value的，所以如果再用getOrDefault就太不make sense
                else return false;
            }else return false;
        }
        return true;
    }

    /**
     * try using int[26], but they also use two loop...
     * @param s
     * @param t
     * @return
     */
    public boolean otherApproach(String s, String t){
        if(s.length()!=t.length())
            return false;

        int[] ary = new int[26];
//        int i = 0;

        for(char c:s.toCharArray())
//            ary[c%26] = ++i;      => idiot, 不能用i拉，次數增長，是要跟自己比較，所以是「+=1」=> 舉例，"aacc"，這邊a會是2次，c會變成4次...
            ary[c%26] += 1;

        for(char c:t.toCharArray()){
            if(ary[c%26]==0)
                return false;
            else
                ary[c%26] -= 1;
        }
        return true;
    }
}
