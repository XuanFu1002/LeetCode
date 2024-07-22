/**
 * 169.
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 */
package leetcode.utilities;

import javax.imageio.ImageReader;

public class E {
    public static int majorityElement(int[] nums){
        int majorElement;
        int count = 0;
        int maxCount = 0;

        insert(nums);

        majorElement = nums[0];

        for(int i = 0; i<nums.length-1;i++){
            if(nums[i] == nums[i+1]){
                count++;        //start form 0, means 多幾次，非共幾次
            }else count = 0;

            if(maxCount < count){
                maxCount = count;
                majorElement = nums[i];
            }
        }

        return majorElement;
    }

    public static void insert(int[] nums){
        for(int i = 1; i<nums.length; i++){
            insertionSort(nums, i-1, nums[i]);
        }
    }

    public static void insertionSort(int[] nums, int index, int val){
        int i = index;
        for(; i>=0; i--){
            if(val < nums[i])
                nums[i+1] = nums[i];
            else{
//                nums[i+1] = val;      因為每次val都要放一次他的位置，寫在這，只有沒被override才會執行
                break;
            }
        }
        nums[i+1] = val;
    }
}
