/**
 * 228.
 * You are given a sorted unique integer array nums.
 *
 * A range [a,b] is the set of all integers from a to b (inclusive).
 *
 * Return the smallest sorted list of ranges that cover all the numbers
 * in the array exactly. That is, each element of nums is covered by exactly one of the ranges,
 * and there is no integer x such that x is in one of the ranges but not in nums.
 *
 * Each range [a,b] in the list should be output as:
 *
 * "a->b" if a != b
 * "a" if a == b
 *
 * Example 1:
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 *
 * Example 2:
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 */
package leetcode.utilities;

import java.util.ArrayList;
import java.util.List;

public class AE {
    public List<String> summaryRanges(int[] nums){
        List<String> list = new ArrayList<>();
        int start = nums[0];
        int end = start+1;       // => 不需用end，只需要對start作加加的動作即可，然後發現若不是連續的話，再從新出發即可
        // 錯了，還是需要，因為就是為了保留起始值，不然後續無從找起

        for(int i=1; i<nums.length; i++){
            if(nums[i] != end){
                int tmp = end -1;
                if(tmp==start){
                    list.add(Integer.toString(start));
                }else{
                    list.add(start+"->"+tmp);
                }
                start = nums[i];
                end = start+1;
            }else{
                end = nums[i];
            }
        }
        return list;
    }

    /**
     * 功能做出來了，但是沒有把general case想清楚，好比array長度為0
     * => 以目前的方法就，只能用if-else去排除，不過這就表示沒設計得很好，因為有些類似客製化的手法，看看有沒有別的實作方式
     * @param nums
     * @return
     */
    public List<String> twice(int[] nums){
        List<String> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();      // use StringBuilder much better

        if(nums.length == 0)
            return list;

        if(nums.length == 1){
            list.add(Integer.toString(nums[0]));
            return list;
        }

        int start = nums[0];
        int end = start;

        for(int i=1; i<nums.length; i++){
//            if(nums[i] != ++end){       //不連續時，要做一次，list.add => record
            if(nums[i] != ++end){       //不連續時，要做一次，list.add => record
                if(end-1 == start)
//                    list.add(Integer.toString(start));
                    stringBuilder.append(start);
                else
//                    list.add(start+"->"+--end);
                    stringBuilder.append(start).append("->").append(--end);
                list.add(stringBuilder.toString());
                stringBuilder.setLength(0);
                end = start = nums[i];
            }
        }
        if(start!=end)
//            list.add(start+"->"+end);
            stringBuilder.append(start).append("->").append(end);
        else
//            list.add(Integer.toString(start));
            stringBuilder.append(start);
        list.add(stringBuilder.toString());
//        return list;
        return list;
    }
}
