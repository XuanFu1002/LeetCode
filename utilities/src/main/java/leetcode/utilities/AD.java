/**
 * 238.
 * Given an integer array nums, return an array answer such that answer[i] is
 * equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 */
package leetcode.utilities;

public class AD {
    /**
     * intuition idea => 最兩個unique for loop，第一個事先算全部的乘積，最後就是trace一輪，再除以當前element即可
     * @param self
     * @return
     */
    public int[] productExceptSelf(int[] self){
        int[] result = new int[self.length];
        int tag = 0;
        int totalProduct = 1;

        for(int a:self){
            if(a!=0){
                totalProduct *= a;
            }else{
                tag++;
                if(tag == 2){
//                    totalProduct = 0;     assign 0否不影響
                    break;
                }
            }
        }

        for(int i=0; i<self.length; i++){
            if(tag == 1){
                if(self[i] != 0)
                    result[i] = 0;
                else
                    result[i] = totalProduct;
            }else if(tag == 2)
                result[i] = 0;
            else
                result[i] = totalProduct/self[i];
        }

        return result;
    }
}
