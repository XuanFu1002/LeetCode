/**
 * 189.
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 */
package leetcode.utilities;

public class F {
    /**
     * 透過觀察可以發現，將整個array做反轉，然後再做區分，在反轉即可
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k){
        k = k % nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }

    public static void reverse(int[] nums, int fIndex, int lIndex){
        //int middle = (int)Math.floor((fIndex + lIndex) / 2);    //ㄟ，也挺搞得如果我只是需要floor的話，利用integer計算只會回傳整數或是取魚都可以
        int middle = (int)Math.ceil((fIndex + lIndex) / 2.0) - fIndex;        //(上+下底)/2=高，減去基礎unit，裁示實際高度

        for(int i = 0; i<middle; i++){      //這裡i的用途是要當作times，控制迴圈次數 => 但原先middle的意義上是index，所以當起始index不是0時，就會多做幾次非必要計算
            int tmp = nums[fIndex + i];
            nums[fIndex+i] = nums[lIndex-i];
            nums[lIndex-i] = tmp;
        }
    }
}
