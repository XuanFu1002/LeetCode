/**
 * 27.
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
 * The order of the elements may be changed. Then return the number of elements in nums which are
 * not equal to val.
 *
 * Consider the number of elements in nums which are not equal to val be k, to get accepted, you
 * need to do the following things:
 *
 * Change the array nums such that the first k elements of nums contain the elements which are
 * not equal to val. The remaining elements of nums are not important as well as the size of nums.
 * Return k.
 */

// there still got place to imporve

package leetcode.utilities;

public class B {
    public static int removeElement(int[] nums, int val){
        int count = 0;
        int i = 0;
        int j = nums.length;

        for(; i<j; i++){
            if(nums[i] == val){
                for(--j; j>i; j--){
                    if(nums[j] != val){
                        int tmp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = tmp;
                        count++;
                        break;
                    }
                }
            }else count++;
        }

        return count;
    }
}
