/**
 * 290.
 * Given a pattern and a string s, find if s follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between
 * a letter in pattern and a non-empty word in s. Specifically:
 *
 * Each letter in pattern maps to exactly one unique word in s.
 * Each unique word in s maps to exactly one letter in pattern.
 * No two letters map to the same word, and no two words map to the same letter.
 *
 * Example 1:
 * Input: pattern = "abba", s = "dog cat cat dog"
 * Output: true
 * Explanation:
 * The bijection can be established as:
 * 'a' maps to "dog".
 * 'b' maps to "cat".
 *
 * Example 2:
 * Input: pattern = "abba", s = "dog cat cat fish"
 * Output: false
 *
 * Example 3:
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 * Output: false
 */
package leetcode.utilities;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class AK {
    public boolean wordPattern(String pattern, String s){
        String[] sAry = s.trim().split(" ");
        if(pattern.length() != sAry.length)
            return false;

        Map<Character,String> pToS = new HashMap<>();
        Map<String,Character> sToP = new HashMap<>();

        for(int i=0; i<pattern.length(); i++){
            char c = pattern.charAt(i);
            String str = sAry[i];

            if(pToS.containsKey(c)){
//                if(pToS.get(c) != str)    => String values are compared using '!=', not 'equals()'
                if(!Objects.equals(pToS.get(c),str))
                    return false;
            }else
                pToS.put(c,str);

            if(sToP.containsKey(str)){
                if(sToP.get(str) != c)
                    return false;
            }else
                sToP.put(str,c);
        }
        return true;
    }
}
