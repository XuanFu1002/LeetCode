/**
 * 45.
 * You are given a 0-indexed array of integers nums of length n. You are initially
 * positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 * In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 *
 *  0 <= j <= nums[i] and
 *  i + j < n
 *
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated
 * such that you can reach nums[n - 1].
 *
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 * Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 */
package leetcode.utilities;

public class J {
    static int count;
    public static int jump(int[] ary){
//        int remainSteps = -1;           //間格
//        int count = 0;
//        int targetDistance = ary.length;        //全長
//        if(targetDistance == 1) return count;
//
//        for(int i=0; i< ary.length; i++){
//            targetDistance --;
//            if(remainSteps-- == 0) return count = 0;
//            int steps = ary[i];
//            if(steps != 0 &&steps > remainSteps){
//                remainSteps = steps;
//                count++;
//            }
////            count++;      //放在外面會導致，小步也加上去了
//            if(remainSteps >= targetDistance) return count;
//        }
//
//        return count;

        //second times
//        int count = 1;
//
//        if(ary.length == 1) return 0;
//
//        for(int i=0; i<ary.length; i++){
//            int tmp = findFarthestIndex(ary,i,ary[i]);
//            if(tmp == 0)
//                return 0;
//            else if(tmp >= ary.length -1)
//                return count;
//            else {
//                i = tmp;
//                count++;
//            }
//        }
//
//        return count;

        //third time
        count = (ary.length == 1)?0:1;

        for(int i=0; i<ary.length; i++){
            if(ary[i] >= ary.length-1-i) return count;      // -i => remainSteps, make more sense => ㄜ其實只有index 0會用到ㄟ，因為後續findFarthestIndex，就會return ary.length -1就會導致code Return
            int tmp = findFarthestIndex(ary,i,ary[i]);
            if(tmp == 0) return 0;
            else if(tmp == ary.length -1) return ++count;
            else count++;
            i = --tmp;
        }
        return count;
    }

//      second times
//    public static int findFarthestIndex(int[] ary, int sIndex, int range){
////        int farReach = ary[sIndex] + sIndex;
//        int farReach = 0;
//        int index = 0;
//        int tmp = 0;
//        int end = (range+sIndex > ary.length-1)? ary.length-1:range+ sIndex;
//
//        for(int i=sIndex; i<end; i++){
//            if (ary[i] != 0) {
//                tmp = ary[i] + i;
//            }
//            if(tmp>farReach){       //等於不用做，反整不管是前或後index反正都是到一樣的地方沒差
//                farReach = tmp;
//                index = i;
//            }
//        }
//        return index;
//    }

    public static int findFarthestIndex(int[] ary, int index, int range){
        int reachIndex = 0;
        int tmp = 0;
        int farIndex = 0;

        for(int i=index; i<=index+range;i++){
            if(ary[i] != 0){
                tmp = ary[i] + i;
            }
            if(tmp > reachIndex){
                reachIndex = tmp;
                farIndex = i;
            }
        }
        return (reachIndex>=ary.length-1)?ary.length-1:farIndex;
    }
}
