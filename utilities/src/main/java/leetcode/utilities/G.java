/**
 * 121.
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a
 * different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve
 * any profit, return 0.
 */
package leetcode.utilities;

public class G {

    public static int maxProfit(int[] nums){
        int maxProfit = 0;
        int buyPoint = 0;

        for(int i = 1; i<nums.length; i++){
            int tmp = nums[i] - nums[buyPoint];
            if(tmp> 0 && tmp>maxProfit){
                maxProfit = tmp;
            }else if(tmp < 0){
                buyPoint = i;
            }
        }
        return maxProfit;
    }
}
