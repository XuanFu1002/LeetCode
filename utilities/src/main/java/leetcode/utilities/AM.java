/**
 * 1.
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 */
package leetcode.utilities;

import sun.management.snmp.jvmmib.EnumJvmMemManagerState;

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;

public class AM {
    /**
     * 這題以前做過，只是今天條件不一樣，他說「可不可以less then O(n^2)」
     * => 我以前的做法是先對Data做sort，然後產生頭大尾小的特性之後，直接two variable control，確實可以達到O(n)的效果
     * ，但如果整體的話還是O(n^2)，所以不清楚題目說的less then O(n^2)是指哪個部分，不過我就先做，然後看result表現
     * @param nums
     * @param target
     * @return
     * => 功能沒問題，不過，他是要回傳index，那自然我就不能改順序，不能sort
     */
    public int[] twoSum(int[] nums, int target){
        if(nums.length <= 1)
            return new int[2];

        for(int i=1; i<nums.length; i++)
            insertion(nums,i-1,nums[i]);

        int[] result = new int[2];
        int k = 0;
        int l = nums.length -1;

        for(;k<l;){     //不能k小於「等於」l，因為他們各自要抓一個value，so can't overlap
            int tmp = target - nums[k] - nums[l];
            if(tmp==0){
                result[0] = k;
                result[1] = l;
                break;
            }else if(tmp>0)
                k++;
            else l--;
        }
        return result;
    }

    public void insertion(int[] ary, int index, int value){
        int i = index;

        for(;i>=0;i--){
            if(ary[i] > value)
                ary[i+1] = ary[i];
            else break;
        }
        ary[++i] = value;
    }

    public int[] otherApproach(int[] ary, int target){
        int[] result = new int[2];
        int i = 0;
        int j = i+1;

        for(; i<ary.length-1;){
            int tmp = target - ary[i] - ary[j];
            if(tmp!=0){
                j++;
                if(j==ary.length){
                    i++;
                    j= i+1;
                }
            }else{
                result[0] = i;
                result[1] = j;
                break;
            }
        }
        return result;
    }

    public int[] improvement(int[] ary, int target){
        Map<Integer, Integer> map = new HashMap<>();    //還是得用hashmap，別忘了hash function查找key value average是O(1)，用ary contain會從頭找
        int[] result = new int[2];

        for(int i=0; i<ary.length; i++)
            map.put(ary[i],i);

        for(int i=0; i<ary.length; i++){
            int tmp = target - ary[i];
//            if(tmp==ary[i])      //若是相等，例如當前ary抓的是3，然後你又要去map裡面抓3，這樣只會有一筆資料，但是要兩筆unique
//                continue;
/**
 * = > 好，我終於懂題目的意思了，value一樣可以，但是要「兩筆」，兩筆甚麼概念，那就是「不同index」
 * => ok，這樣的敘述也才make sense => 題目確實沒說他所有element都是unique，不過這也才符合general case，
 * 並且他說了同element只能用一次，其實說的就是same index，並不是說element的value只能出現一次，我一開始以為是這個....
 */
            if(map.containsKey(tmp)){
                if(i!=map.get(tmp)){
                    result[0] = i;
                    result[1] = map.get(tmp);
                    break;
                }
            }
        }
        return result;
    }

    public int[] leetCode(int[] ary, int target){
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0; i<ary.length; i++){
            //explain: if ary[i] is the next value we found?
            if(map.containsKey(ary[i]))     //第一次肯定沒有key，所以不用擔心重複index使用的問題(同一筆)，避開第一次之後，下次就算遇到相同value也是不同stuff，所以放心
                return new int[]{map.get(ary[i]),i};
            else
                map.put(target-ary[i],i);       //put next found value & partner index
        }
        return null;        //oh~
    }
}
