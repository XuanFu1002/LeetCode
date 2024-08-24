/**
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number. Let these two numbers
 * be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 *
 * Return the indices of the two numbers, index1 and index2, added by one as an integer
 * array [index1, index2] of length 2.
 *
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 *
 * Your solution must use only constant extra space.
 *
 * Example 1:
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 *
 * Example 2:
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Explanation: The sum of 2 and 4 is 6. Therefore, index1 = 1, index2 = 3. We return [1, 3].
 *
 * Example 3:
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 * Explanation: The sum of -1 and 0 is -1. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 */
package leetcode.utilities;

public class AA {
    /**
     * origin idea => 舊式簡單的double for loop，掀桌一個數固定，然後再用second loop去找另一個數 => 可以用contain的方法，
     * 這樣就看起來像是single，因為是call API，但是實際上implement還是double loop
     * => 這題只說要求兩個index，沒有說沒找到要回傳甚麼，所以就先假定必有solution => 那我就不考慮A+B=C的A會大於C找不到的情況
     * => int[] has no contains()
     * @param numbers
     * @param target
     * @return
     */
    public int[] towSum(int[] numbers, int target){
        int[] result = new int[2];
        int tmp = 0;
        for(int i=0; i<numbers.length; i++){
            tmp = target - numbers[i];
            for(int j=i+1; j<numbers.length; j++){
                if(tmp==numbers[j]){
                    result[0] = i+1;
                    result[1] = j+1;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 這次改作single for loop => by using two index
     * @param numbers
     * @param target
     * @return
     */
    public int[] otherApproach(int[] numbers, int target){
        int[] result = new int[2];
        int i = 0;
        int j = numbers.length-1;

        for(;i<j;){
            int tmp = numbers[i] + numbers[j];
            if(target > tmp)        //fuck, here means summarize too small
                i++;
            else if(target < tmp)       // too big
                j--;
            else{
                result[0] = i+1;
                result[1] = j+1;
                return result;
            }
        }

        return result;
    }
}
