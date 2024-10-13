/**
 * 2024/10/12 (Sat.)
 * 1863. Sum of All Subset XOR Totals
 * The XOR total of an array is defined as the bitwise XOR of all its elements,
 * or 0 if the array is empty.
 *
 * For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
 * Given an array nums, return the sum of all XOR totals for every subset of nums.
 *
 * Note: Subsets with the same elements should be counted multiple times.
 *
 * An array a is a subset of an array b if a can be obtained from b by deleting some
 * (possibly zero) elements of b.
 *
 * Example 1:
 * Input: nums = [1,3]
 * Output: 6
 * Explanation: The 4 subsets of [1,3] are:
 * - The empty subset has an XOR total of 0.
 * - [1] has an XOR total of 1.
 * - [3] has an XOR total of 3.
 * - [1,3] has an XOR total of 1 XOR 3 = 2.
 * 0 + 1 + 3 + 2 = 6
 *
 * Example 2:
 * Input: nums = [5,1,6]
 * Output: 28
 * Explanation: The 8 subsets of [5,1,6] are:
 * - The empty subset has an XOR total of 0.
 * - [5] has an XOR total of 5.
 * - [1] has an XOR total of 1.
 * - [6] has an XOR total of 6.
 * - [5,1] has an XOR total of 5 XOR 1 = 4.
 * - [5,6] has an XOR total of 5 XOR 6 = 3.
 * - [1,6] has an XOR total of 1 XOR 6 = 7.
 * - [5,1,6] has an XOR total of 5 XOR 1 XOR 6 = 2.
 * 0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
 *
 * Example 3:
 * Input: nums = [3,4,5,6,7,8]
 * Output: 480
 * Explanation: The sum of all XOR totals for every subset is 480.
 *
 * Constraints:
 * 1) 1 <= nums.length <= 12
 * 2) 1 <= nums[i] <= 20
 */
package leetcode.utilities;

public class AZ {
    public int subsetXORSum(int[] ary){
        return inOrExclude(ary,0,0);    // 最一開始先套，空集合(0,0) => 0 element, no value
    }

    /**
     * 核心思路 => implement 每個element「取or不取」，所有可能就出來了，i.e.所有的subset
     * 1. index parameter，在這有兩種meaning，放在parameter2的時候是amount，被拿去parameter3的時候是index
     * => 數學上，好像叫做二分法
     * @param ary
     * @param index
     * @param currentXOR
     * @return
     */
    public int inOrExclude(int[] ary, int index, int currentXOR){
        if(index == ary.length){        // 這邊就是控制，取與不取的「層數」
            return currentXOR;
        }
        // 共「index+1」個element，「取」第index個element => 都知道ary index start from 0
        int include = inOrExclude(ary, index+1, currentXOR^ary[index]);
        // 共「index+1」個element，但「不取」當前element
        int exclude = inOrExclude(ary, index+1, currentXOR);

        return include + exclude;
    }
}
