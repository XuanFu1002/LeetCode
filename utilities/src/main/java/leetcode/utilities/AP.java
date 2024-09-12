/**
 * 128.     2024/9/12
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore, its length is 4.
 *
 * Example 2:
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 */
package leetcode.utilities;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class AP {
    /**
     * chat idea => all in notion c c
     * @param ary
     * @return
     */
    public int longestConsecutive(int[] ary){
        if(ary.length == 0)
            return 0;

        Set<Integer> set = new HashSet<>();

        for(int i=0; i<ary.length; i++)
            set.add(ary[i]);
        int longestConsecutive = 1;

        for(int i=0; i<ary.length; i++){
            if(!set.contains(ary[i]-1)) {         //ensure, counting start with ary[i], ensure that ary[i] is the smallest one
                int tmp = ary[i];
                int count = 1;
                while(set.contains(++tmp))
                    count++;
                longestConsecutive = Math.max(longestConsecutive,count);
            }
        }
        return longestConsecutive;
    }

    public int lifeHacks(int[] ary){
        if(ary.length==0 || ary.length==1)
            return ary.length;

        Arrays.sort(ary);       // damn O(nlogn)....

        int maxConsecutive = 1;
        int count = 1;

        for(int i=1; i<ary.length; i++){
            if(ary[i] == ary[i-1])
                continue;
            if(ary[i] == ary[i-1]+1){
                count++;
                maxConsecutive = Math.max(maxConsecutive,count);
            }else
                count =1;
        }
        return maxConsecutive;
    }
}
