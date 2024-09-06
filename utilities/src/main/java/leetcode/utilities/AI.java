/**
 * 383.
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed
 * by using the letters from magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 *
 * Example 1:
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 *
 * Example 2:
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 *
 * Example 3:
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 */
package leetcode.utilities;

import java.util.Map;
import java.util.HashMap;

public class AI {
    /**
     * origin, 原先的想法就是對source data做查找，
     * 每查找一次後，對magazine的value做調整，畢竟題目規定，一個char只能用一次，
     * 然後string更動的方式用stringBuilder較好 => 所以原先的想法就是不停地做for loop & 不停調整string value
     * => 但用膝蓋想就知道設計得很差time complexity一定爆高 => 太直觀了，可以多觀察，多思考
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean origin(String ransomNote, String magazine){
        if(magazine.length() < ransomNote.length())
            return false;

        StringBuilder stringBuilder = new StringBuilder(magazine);

        for(int i=0; i<ransomNote.length(); i++){
            //.....
        }
        return false;
    }

    /**
     * chat idea =>
     * 1.不對source data做處理，就省去邊做迴圈查找ransomNote的char有沒有在magazine，需要調整magazine這個低效率的動作
     * ，取而代之，這邊多新建一個DS(Data Structure) HashMap object, 用來儲存「magazine中的每個char所出現的『次數』」
     * 2.次數的好處在於，每用一次，次數-1即可，就可以替代掉，用stringBuilder reassign所造成的時間cost
     * 3.另外一個好處就是，不要忘了hash結構的object，在做「查找」的時候是採用「hash function」，而hash function average
     * time complexity is O(1) => 而若是採用string那種的contains的話，最差的情況是要trace所有element一次，反正就是一個一個找
     * Summary:
     * 1.Frequency
     * 2.Hash Function enhance lookup latency
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine){
        Map<Character,Integer> map = new HashMap<>();

        for(int i=0; i<magazine.length(); i++)
            map.put(magazine.charAt(i),map.getOrDefault(magazine.charAt(i),0)+1);
        //map.getOrDefault(a,b) => 意思是說，查看hashmap當中有沒有包含a這個key，如果沒有的話就return b這個value，
        // 如果有的話，那就回傳a這個key所對應的value，然後這裡設0做為沒有的原因非常直觀，就是因為沒出現呀，
        // 然後後面加上1的原因就是因為，待會put完之後，這就是第一次了呀

        for(int i=0; i<ransomNote.length(); i++){
            if(!map.containsKey(ransomNote.charAt(i)) || map.get(ransomNote.charAt(i))==0)
                return false;
            map.put(ransomNote.charAt(i),map.get(ransomNote.charAt(i))-1);      //用過之後，當然要減1
        }
        return true;
    }

    /**
     * 還不能很確切的說為何time complexity，int[]表現會更好
     * chat是說，hashMap DS會建立而外德hash table，而且可能會有cache miss的情況，就會導致cache與memory之間交換data的penalty
     * ，再來就是new int[26]很小，不過說是因為這裡的範圍限定在a~z，萬一今天還包含國字、大小寫等等 ，用hashmap會更好、更方便，
     * 因為使用hash function始終在查找key value更快，因為indexOf(c)，這是從index 0開始trace，所以worst case O(n)
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean otherApproach(String ransomNote, String magazine){
        int[] alphabet = new int[26];       //only 26 characters

        for(char c:ransomNote.toCharArray()){
            int tmp = magazine.indexOf(c,alphabet[c%26]);       // 「c%26」沒特別意義，只是因為剛好26個字母，所以讓每個character各占一個index，這裡就會變成ASCII % 26，然後相同character都會使用共同的index value就是了
            // 再來就是，indexOf(c, int i) => 這裡的用法是說從i這個index開始往下找，c這個value => 然後因為一開始initialize為0，所以保證可以完整trace一次，如果沒找到則return -1
            if(tmp == -1) return false;
            alphabet[c%26] = tmp+1;     //tmp是找到c這個character的index，然後!!因為一個character只能用一次，所以下次就要從下一個index開始找起 => make sense
        }
        return true;
    }
}
