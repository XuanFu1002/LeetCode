/**
 * 80.
 * RemoveDuplicates Part II => more tolerant
 *
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place
 * such that each unique element appears at most twice. The relative order of the elements
 * should be kept the same.
 *
 * Since it is impossible to change the length of the array in some languages, you must instead
 * have the result be placed in the first part of the array nums. More formally, if there are
 * k elements after removing the duplicates, then the first k elements of nums should hold the
 * final result. It does not matter what you leave beyond the first k elements.
 *
 * Return k after placing the final result in the first k slots of nums.
 *
 * Do not allocate extra space for another array. You must do this by modifying the input
 * array in-place with O(1) extra memory.
 */
package leetcode.utilities;

public class D {
    public static int removeDuplicates(int[] ary){
        int k = 0;
        int times = 1;
        for(int i = 1; i<ary.length; i++){
//            if((ary[k] != ary[i]) || (ary[k] == ary[i] && times != 2){
//                ary[++k] = ary[i];
//                times = 1;
//            }else
//                times++;
            if(ary[k] != ary[i]){
                ary[++k] = ary[i];
                times = 1;
            }else if(times != 2){       //淺再就是相等，所以依這是裡有兩個相等數
                ary[++k] = ary[i];
                times++;
            }
        }
        return ++k;
    }
}
