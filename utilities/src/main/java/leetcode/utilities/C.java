/**
 * 26.
 * Given an integer array nums sorted in non-decreasing(increasing) order, remove the duplicates in-place
 * such that each unique element appears only once. The relative order of the elements should
 * be kept the same. Then return the number of unique elements in nums.
 *
 * Consider the number of unique elements of nums to be k, to get accepted, you need to do the
 * following things:
 *
 * Change the array nums such that the first k elements of nums contain the unique elements in
 * the order they were present in nums initially. The remaining elements of nums are not important
 * as well as the size of nums.
 * Return k.
 */
package leetcode.utilities;

public class C {
    public static int removeDuplicates(int[] ary){
//        int count = 1;
        int k = 0;
        for(int i=1; i<ary.length; i++){
            if(ary[k]!=ary[i]){
                ary[++k] = ary[i];      //k本身就是unique index的涵義呀XD，要活用
//                count++;
            }
        }
        return ++k;
    }
}
