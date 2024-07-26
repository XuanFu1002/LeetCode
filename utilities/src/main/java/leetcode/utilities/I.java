/**
 * 55.
 * You are given an integer array nums. You are initially positioned at the array's
 * first index, and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump
 * length is 0, which makes it impossible to reach the last index.
 */

package leetcode.utilities;

public class I {
    /**
     *  我下面用index的寫法，沒有去檢查消耗的步數，所以就沒辦反做出動態步數得功能
     *  => 而我只是檢查了，是否有人能到最後index，而就算有這個人，但是我說不定過不去呀
     *  => 所以 以下實作，無法滿足動態觀察公用
     * @param ary
     * @return
     */
    public static boolean canJump(int[] ary){
//        int reachableIndex = 0;
//        if(ary.length ==1) return true;     //若是長度只有1，不館該element value為何，他即是最後一個，所以直接true
//        if(ary[0] == 0) return false;
//
//        for(int i=0; i<ary.length -1; i++) {
//            int tmp = i + ary[i];
//            if (tmp >= ary.length - 1)             //超過就可以了呀...
//                return true;
//            else if (tmp > reachableIndex)
//                reachableIndex = tmp;
//        }
//        return false;

        //second times
//        int remainSteps = -1;
//
//        for(int i=0; i<ary.length; i++){
//            if(remainSteps == 0) return false;
//            if(ary[i] > remainSteps){
//                remainSteps = ary[i];
//            }else{
//                remainSteps--;
//            }
//        }
//        return true;

        //third times
        int remainSteps = -1;

        for(int i=0; i<ary.length; i++){
//            if(--remainSteps == 0) return false;        要用remainSteps--，不然這裡就不能用0要用-1 => value代表性問題
            if(remainSteps-- == 0) return false;       //ofc just only to judge remainSteps is enough, since step may be 0, but there still got remain step to use
            int steps = ary[i];
            if(steps > remainSteps)
                remainSteps = steps;
        }
        // improve space is that early jump out of for loop, since only need to return true false, there's no need for go through each for loop

        return true;
    }
}
