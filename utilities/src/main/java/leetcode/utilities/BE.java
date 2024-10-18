/**
 * 2024/10/19 (Fri.)
 * 2044 Count Number of Maximum Bitwise-OR Subsets
 *
 * Given an integer array nums, find the maximum possible bitwise OR of a subset of nums and return
 * the number of different non-empty subsets with the maximum bitwise OR.
 *
 * An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero)
 * elements of b. Two subsets are considered different if the indices of the elements chosen are different.
 *
 * The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] (0-indexed).
 *
 * Example 1:
 * Input: nums = [3,1]
 * Output: 2
 * Explanation: The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
 * - [3]
 * - [3,1]
 *
 * Example 2:
 * Input: nums = [2,2,2]
 * Output: 7
 * Explanation: All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 23 - 1 = 7 total subsets.
 *
 * Example 3:
 * Input: nums = [3,2,1,5]
 * Output: 6
 * Explanation: The maximum possible bitwise OR of a subset is 7. There are 6 subsets with a bitwise OR of 7:
 * - [3,5]
 * - [3,1,5]
 * - [3,2,5]
 * - [3,2,1,5]
 * - [2,5]
 * - [2,1,5]
 *
 * Constraints:
 * 1) 1 <= nums.length <= 16
 * 2) 1 <= nums[i] <= 105
 */
package leetcode.utilities;

public class BE {
    public int countMaxOrSubsets(int[] nums) {
        int maxOR = 0;
        int[] count = new int[1];

        // 計算 nums 所有元素的最大 bitwise OR 值
        for (int num : nums) {
            maxOR |= num;
        }

        // 呼叫回溯函數
        backtrack(nums, 0, 0, maxOR, count);

        return count[0];
    }

    /**
     * backtrack, concept => 往下走、又回頭、又往下走.... => 本質上還是recursive
     * 一樣，實作subset，就是取與不取的二分問題
     * @param nums
     * @param index
     * @param currentOr
     * @param maxOR
     * @param count
     */
    private void backtrack(int[] nums, int index, int currentOr, int maxOR, int[] count) {
        // 當到達最後一個元素後，如果當前 OR 值等於 maxOR，就增加計數
        if (index == nums.length) {
            if (currentOr == maxOR) {
                count[0]++;
            }
            return;
        }

        // 選擇包含 nums[index] 的情況
        backtrack(nums, index + 1, currentOr | nums[index], maxOR, count);

        // 選擇不包含 nums[index] 的情況
        backtrack(nums, index + 1, currentOr, maxOR, count);
    }
}
