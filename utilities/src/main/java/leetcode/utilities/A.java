/**
 * 88.
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order,
 * and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 *
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 *
 * The final sorted array should not be returned by the function, but instead be stored inside
 * the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements
 * denote the elements that should be merged, and the last n elements are set to 0 and should be ignored.
 * nums2 has a length of n.
 */

package leetcode.utilities;

public class A {
    public static void merge(int[] nums1, int m, int[] nums2, int n){
        for(int i = m; i < nums1.length; i++)
            nums1[i] = nums2[i-m];

        insert(nums1);
    }

    public static void insert(int[] ary){
        for(int i = 1; i < ary.length; i++){
            insertionSort(ary, i-1, ary[i]);
        }
    }

    public static void insertionSort(int[] ary, int n, int val){
        int i = n;
        for(; i >=0; i--) {
            if (val < ary[i])
                ary[i + 1] = ary[i];
            else
                break;
        }
        ary[++i] = val;
    }
}
