package leetcode.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AO {
    /**
     * origin idea => 不要超過interval，在interval之內指到相同value return true，若全部做完，就代表都沒找到return false
     * cons => 我這裡的毛病就在於，我對於「interval」區間內的觀察不夠到位，因為我是寫說，如果差過一次上限，那就全部clear，然後重新計算interval，
     * 會這樣想是因為，hashset api只有clear & remove，如果單純以當前value去remove，會有問題，所以我選用clear => 但殊不知，就衍生一位一個而忽視
     * 調先前所有努力，這就不make sense
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        int count = 0;

        for(int i=0; i<nums.length; i++){
            if(count <= k){         //首先， <= k就變成，count~k，所以就會變成 0~3，那一共檢查4格數字  => 然後這裡依然可以用「小於等於」的原因是因為，loop的起始index為0，所以撇除interval再加上本身那一筆之後，確實一共是1+3筆數字 => 若是不想用「等於」，那就要找一個地方，先將index 1的element塞到set當中
                if(!set.contains(nums[i])){
                    set.add(nums[i]);
                    count++;
                }else return true;
            }else {
                set.clear();        //不能用remove => e.g.[1,2,3,4,1,2,3]、k = 2，假設interval超過了，然後當前的i指向的value是4，然後你試圖去remove，但接下來重疊的是1，但你沒有清除，導致他return true
                set.add(nums[i]);       //也不要忘了加上當前的value
//                count = 0;
                count = 1;      //既然條件用的是「小於等於」，那這邊assign過後，就要用1出發 => 這樣接下來的 1「小於等於」3，就都會變成interval的unit check => 不然第一輪的時候，是check「總數」
            }
        }
        return false;
    }

    /**
     * 改選用hashmap，不然為了解決hashset remove的問題有點麻煩，假如只用一般型別variable儲存目標被remove的value，但這就只能做一次，因為下一筆就抓不到了
     * ，不然就是再宣告一個DS去專門存放目標remove value的清單，但這就太麻煩 => 所以改用hashmap，利用他的key mapping特性，如此就能很簡單的控制刪除element的動作
     * ，然後選用hashmap而不是list這個原因遇到很多遍了，因為「查找」方面，hash系列有hash function、hash table，所以average O(1)，但是list、ary這種，就只能
     * 從index 0開始去查找，所以average O(n) => list的contains(Object) or indexOf(Object) parameter所放的都是value，而不是index，要直接給index要用get(index)
     * 這樣list查找element才可以變成O(1)
     * @param ary
     * @param k
     * @return
     */
    public boolean improvement(int[] ary, int k){
        if(k==0)            //因為我loop body裡面控制set個數的寫法，at least one element，所以把0過濾掉，不然會有問題 => 況且interval為0，本身就是找不到兩個distinct value，因為永遠只有自己
            return false;

        HashMap<Integer,Integer> map = new HashMap<>();
        int removeIndex = 0;

        for(int i=0; i<ary.length; i++){
           if(!map.containsKey(ary[i]))
               map.put(ary[i],i);
           else return true;

           if(i>=k){        //這裡要做的就是維持一個「3 elements set」，然後因為會做remove element的動作，所以這邊加上「等於」，不能單純小於，e.g. 0~3 -1 => 1~3 (maintain 3 element)
               map.remove(ary[removeIndex++]);
               //map.put(ary[i],i);           //oh fuck => 這便有問題 => ㄚ對齁，我都忘記我這邊的設計是，前面先加，然後走到這邊再維持size
               // => 並且能加進去就表示沒有duplicate,所以不會有甚麼value漏加的問題，縱使遺漏好了，那也是因為發現duplicate，所以早就return function了
               // 這邊map.put沒有問題是因為，他override相同一筆key => 所以不該有這行statement
           }
        }
        return false;
    }

    /**
     * functionality沒問題，但是用了list的contains & remove，並沒有優化code time complexity 反而變worse
     * => therefore, avoid 1) using remove => primitive ary better    2) contains => hash better
     * @param ary
     * @param k
     * @return
     */
    public boolean changeDS(int[] ary, int k){
        if(k==0)
            return false;

        List<Integer> list = new ArrayList<>();
        int index = 0;

        for(int i=0; i<ary.length; i++){
            if(!list.contains(ary[i]))
                list.add(ary[i]);
            else return true;

            if(i>=k)
                list.remove(index/*index++*/);       //oh, idiot
            // => 這邊永遠remove index 0 就好 => 只有hashmap才需要透過key查找 => 所以要搞清楚使用的DS，有甚麼特性，是甚麼東西
            // list 做remove的時候，他是做兩件事「移除元素」&「把element往前挪」=> 那這樣的話list並沒有比hashmap快，對於這個remove operation來說
        }
        return false;
    }
}
