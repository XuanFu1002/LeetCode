/**
 * 135.
 * There are n children standing in a line. Each child is assigned a rating value
 * given in the integer array ratings.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 *
 * Example 1:
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 *
 * Example 2:
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 */
package leetcode.utilities;

import java.util.Arrays;

public class O {
    private boolean isIncreased = false;
    private boolean isDecreased = false;

    public int candy(int[] ratings){
        int tmp = 0;
        int minimumCandy = 0;
        int minRelate = 1;
        int count = 1;
        int omg = 0;
        int[] ary = new int[ratings.length];

        tmp = ary[0] = 1;

        for(int i=1; i<ratings.length; i++){
            if(ratings[i] < ratings[i-1]){      //decrease
                isDecreased = true;
                // two cases => 1. decrease -> decrease     2. increase -> decrease
                if(isIncreased){
                    isIncreased = false;
                    tmp-=ary[i-1];
                    omg = ary[i-1];
                    minimumCandy += tmp;        //pay off
                    minRelate = tmp = ary[i-1] = 1;
                    count = 2;
                    ary[i] = 0;
                    if(ary[i] < minRelate)
                        minRelate = ary[i];
                }else{
                    ary[i] = ary[i-1] -1;
                    if(ary[i] < minRelate)
                        minRelate = ary[i];
                    count++;
                    tmp+=ary[i];
                }
            }else if(ratings[i] > ratings[i-1]){        //increase
                isIncreased = true;

                if(isDecreased){            //still need pay off, but these all because speculate, not really can't tell the point principle for why
                    isDecreased = false;
                    if(omg >= count && count!=1)
                        tmp += omg - count;
                    minimumCandy += tmp + (minRelate*(-1)+1)*count;
                    tmp = ary[i] = 2;
                    omg = 0;
                    count = minRelate = 1;
                }else{
                    ary[i] = ary[i-1] +1;
                    count++;
                    tmp+=ary[i];
                }
            }else{
                //need to pay off
                isIncreased = isDecreased = false;
                minimumCandy += tmp + (minRelate*(-1)+1)*count;
                tmp = count = minRelate = ary[i] = 1;
            }
        }

        if(omg >= count && count!=1)
            tmp += omg - count;
        minimumCandy += tmp + (minRelate*(-1)+1)*count;

        return minimumCandy;
    }

    public int brilliantIdea(int[] ratings){
        //要習慣去對parameter做valid judgment
        if(ratings == null || ratings.length ==0)
            return 0;

        int[] tmp = new int[ratings.length];
        int minimumCandies = 0;

//        Arrays.fill(tmp, 1);
        for(int i=0; i<tmp.length; i++)
            tmp[i] = 1;

        //這裡會做n次
        for(int i=1; i<ratings.length; i++){
            if(ratings[i] > ratings[i-1])
                tmp[i] = tmp[i-1] +1;
        }

        //做m次，然後n+m=ratings.length，因為除果有遞增，就不會有遞減，所以擇期一做一次 => 且只有>or< 的才會做更動，否則就跳過，然後就會維持原先的「1」，就會達到說，當相等的時候，數值塞最小1
        for(int i=ratings.length-2; i>=0; i--){
            if(ratings[i] > ratings[i+1])
                tmp[i] = Math.max(tmp[i], tmp[i+1]+1);
        }

        for(int a: tmp)
            minimumCandies += a;

        return minimumCandies;
    }
}
