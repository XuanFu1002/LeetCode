/**
 * 15.
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such
 * that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 *
 * Example 2:
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 *
 * Example 3:
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 */

package leetcode.utilities;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AC {
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> innerList = new ArrayList<Integer>();

        if(nums.length < 3)
            return list;

        for(int i=0; i<nums.length-2; i++){
            for(int j=i+1; j<nums.length-1; j++){
                for(int k=j+1; k<nums.length; k++){
                    if(nums[i] + nums[j] + nums[k] == 0){
                        innerList.add(nums[i]);
                        innerList.add(nums[j]);
                        innerList.add(nums[k]);
                        if(!list.contains(innerList))
                            list.add(new ArrayList<>(innerList));
                        innerList.clear();      //要馬clear，要馬create local list variable
                    }
                }
            }
        }
        return list;
    }


    /**
     * damn，首先我以為要回傳index，再來就是我以為要按照原先順序排列，但TM的發現根本就不用
     * => 所以可以用sort，不然早就有想到要用sort來做了
     * @param nums
     * @return
     */
    public List<List<Integer>> otherApproach(int[] nums){
//        List<List<Integer>> list = new List<List<Integer>>; => 笑死，List是interface，不要忘了他是不能直接用來new的，要馬用implement class要馬用匿名(lambda)去implement class
        List<List<Integer>> list = new ArrayList<List<Integer>>();      //不要忘了，Generic裡面，要一致
        List<Integer> innerList = new ArrayList<Integer>();

        if(nums.length<3)
            return list;

        for(int i=1; i<nums.length; i++)
            insertionSort(nums, i-1, nums[i]);

        int a = nums[0] -1;
        int b = nums[1] -1;
        int c = nums[2] -1;

        for(int i=0; i<nums.length-2; i++){
//            if(a != nums[i])            //不能單獨判斷，要三個一組憶起判斷，才make sense
//                a = nums[i];
//            else
//                continue;
            for(int j=i+1; j<nums.length-1; j++){
//                if(b != nums[j])
//                    b = nums[j];
//                else
//                    continue;
                for(int k=j+1; k<nums.length; k++){
//                    if(c != nums[k])
//                        c = nums[k];
//                    else
//                        continue;

                    if(nums[i]+nums[j]+nums[k] == 0){
                        if(a!=nums[i] && b!=nums[j] && c!=nums[k]){
                            a = nums[i];
                            b = nums[j];
                            c = nums[k];
                        }else{
                            continue;
                        }
                        innerList.add(nums[i]);
                        innerList.add(nums[j]);
                        innerList.add(nums[k]);
                        list.add(new ArrayList<>(innerList));
                        innerList.clear();
                    }
                }
            }
        }
        return list;
    }

    /**
     * 做n次，就會形成一個排序好的n數列 => aim: increase
     * @param nums
     * @param index
     */
    public void insertionSort(int[] nums, int index, int value){
        int i = index;
        for(;i>=0;i--){
            if(nums[i] > value)
                nums[i+1] = nums[i];
            else
                break;
        }
        nums[++i] = value;
    }

    public List<List<Integer>> twice(int[] nums){
        List<List<Integer>> list = new ArrayList<List<Integer>>();

        if(nums.length < 3)
            return list;

        for(int i=1; i<nums.length; i++)
            insertionSort(nums, i-1, nums[i]);

        for(int i=0; i<nums.length; i++){
            if(i>0 && nums[i]==nums[i-1])
                continue;
            int j = i+1;
            int k = nums.length-1;
             while(j<k){
                int sum = nums[i] + nums[j] + nums[k];
                if(sum == 0){
                    list.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    while(j<k && nums[j]==nums[j+1]) j++;
                    while(j<k && nums[k]==nums[k-1]) k--;
                    j++;            //因為少了下面兩行，所以當找到加總為0的時候，並且左右element不相等的時候，index不會跳動，所以condition就會一直成立，就卡住了
                    k--;
                }else if(sum < 0)
                    j++;
                else
                    k--;
            }
        }
        return list;
    }
}
