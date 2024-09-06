/**
 * 205.
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving
 * the order of characters. No two characters may map to the same character, but a character may map to itself.
 *
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 * Explanation:
 * The strings s and t can be made identical by:
 * Mapping 'e' to 'a'.
 * Mapping 'g' to 'd'.
 *
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 * Explanation:
 * The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'.
 *
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 */
package leetcode.utilities;

import com.sun.scenario.animation.shared.ClipEnvelope;

import java.lang.reflect.Array;
import java.util.*;

public class AJ {
    /**
     * isomorphic(同構) => 意思就是架構一樣ㄅㄟ，就像是骨架一樣，架構相同，只是上色、穿的衣服不同而已
     * 結合hashMap的話 => 想到的DS就是，< Integer,int[]>，ary放的是該character所出現的index位置
     * @param s
     * @param t
     * @return
     * => not fit general case
     */
    public boolean isIsomorphic(String s, String t){
        if(s.length() != t.length())
            return false;

        Map<Character,Integer> sIsomorphic = new HashMap<>();
        Map<Character,Integer> tIsomorphic = new HashMap<>();

        //無法解決generalize case，因為我這邊紀錄的是「頻率、次數」，與構造相同否無關
        for(int i=0; i<s.length(); i++){
//            int tmpA = sIsomorphic.put(s.charAt(i), sIsomorphic.getOrDefault(s.charAt(i),0)+1);   => unBoxing problem, since getOrDefault() return 「Integer」，because the value of the key is declared by Integer
            Integer tmpA = sIsomorphic.put(s.charAt(i), sIsomorphic.getOrDefault(s.charAt(i),0)+1);
            Integer tmpB = tIsomorphic.put(t.charAt(i), tIsomorphic.getOrDefault(t.charAt(i),0)+1);
            if(!Objects.equals(tmpA, tmpB))
                return false;
        }
        return true;
        /**
         * Need familiar stuff
         * 1. getOrDefault()
         * 2. computeIfAbsent() => return stuff, and the reason that we can directly modify this return stuff, is that this is pass by address
         * 3. merge()
         * 4. put()
         * 5. unboxing
         */
    }

    /**
     * 這裡核心就問你到底是否真懂題目 => 所謂「同構」，具體是甚麼意思呢? 從寫下來觀察的例子，
     * 我會很直覺的說，同構就是那個骨架長的一樣呀 => 但其實換個角度，再更具體一點的意思就是，在某個情況下，如果你發現a對應b，那後面a就不能對應到b以外的東西
     * ，不然這樣的結構，就沒有規則，也就是不同構的東西 => 統整此現象之後，只需坐< Character, Character>字元的對照map即可
     * @param s
     * @param t
     * @return
     */
    public boolean improvement(String s, String t){
        if(s.length()!=t.length())
            return false;

        Map<Character,Character> sTot = new HashMap<>();
        Map<Character,Character> tTos = new HashMap<>();

        for(int i=0; i<s.length(); i++){
            char a = s.charAt(i);
            char b = t.charAt(i);

            if(sTot.containsKey(a)){
               if(sTot.get(a) != b)
                   return false;
            }else{
                sTot.put(a,b);
            }

            if(tTos.containsKey(b)){       //OK，知道為何要雙向都檢查，假如只檢查單向的情況下，有可以發生a & b 都對應c，但這就錯了，所以要雙向檢查，單向檢查不出來
                if(tTos.get(b) != a)
                    return false;
            }else{
                tTos.put(b,a);
            }
        }
        return true;
    }

    /**
     * idea => 創建兩個empty ary，因為要起到「同構」的效果，所以這裡就是要給兩個字元綁在一起，這裡的方法是「塞一個共同value」，
     * value是多少並不重要，重要的是，這個value起到了「識別」的作用。好比，string s & string t，第一輪的對應的字元分別為
     * a & b，那此時，為了將他們的關聯性綁再一起，這裡隨便塞一個value，這裡採用「i+1」，並且將這個value存放在自己ASCII的index當中
     * ，像是a就是97，b就是98，然後他們的value都是塞1，那關聯性就建立起來了，因為假設下一輪string中的字元對應是c&b，那這裡就會有問題，
     * 因為c的99 index value還是0，但是tAry的b 98 index已經是1，這就代表說，他是與別人有關聯的了，具體是誰呢，就是那個當初依樣跟我份配1
     * 的那個人，然後如果又出現一組a&b，驗證過他們value一樣那就ok，至於後面assign value塞多少根本不重要，重要的是他們綁再一起
     * @param s
     * @param t
     * @return
     */
    public boolean other(String s, String t){
        if(s.length()!=t.length())
            return false;

//        int[] sAry = new int[26];       //limit by a~z range, work => but this case use more than alphabet, 連標點符號都有
//        int[] tAry = new int[26];
        int[] sAry = new int[256];
        int[] tAry = new int[256];

        for(int i=0; i<s.length(); i++){
            char a = s.charAt(i);
            char b = t.charAt(i);

//            if(sAry[a%26]!=tAry[b%26])
            if(sAry[a]!=tAry[b])
                return false;

//            sAry[a%26] = i+1;       //必須+1，因為要跟initialize value的0做區分，不然就變成所已經用過的東西，卻還保持0，那其他0的也可以過來搭
//            tAry[b%26] = i+1;       //所以，雖然確實這裡assign的value是多少並不重要，但就是要跟initial做區隔
            sAry[a] = i+1;
            tAry[b] = i+1;
        }
        return true;
    }
}
