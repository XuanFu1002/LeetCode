/**
 * 45.      2126
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane.
 * The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a
 * balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact
 * y-coordinates of the balloons.
 *
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
 * A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the
 * number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 *
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 *
 * Example 1:
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
 * - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
 *
 * Example 2:
 * Input: points = [[1,2],[3,4],[5,6],[7,8]]
 * Output: 4
 * Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
 *
 * Example 3:
 * Input: points = [[1,2],[2,3],[3,4],[4,5]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
 * - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
 */

package leetcode.utilities;

import java.util.Arrays;
import java.util.Comparator;
public class AH {
    /**
     * 首先要先對題目的敘述有想像，不然無法幫助思考&解決問題
     * 1.這題所描述的情況，可以想像成，有一對氣球往上堆疊，而他們對其的方法是看x軸的位置去排列
     * 2.例如[[1,6],[2,8],[3,5]]這邊就可以看成 => 第一顆氣球為直徑6，並且擺放位置為x=1~6
     *  至於第二科，直徑為6，擺放位置為x=2~8，注意這裡，x=2~6的部分重疊，所以就表說，第二顆氣球
     *  疊在第一顆上面，第三科以此類推
     * 3.所以既然有從疊的部分，那就代表說，假如今天我從他們x重疊的部分射過去，那一次就會射中3科
     *  => 這題不考慮氣球爆炸後的連鎖反應，題目問的就只是一次能射種幾顆
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points){
//        for(int i=1; i<points.length; i++)
//            insertion(points, i-1, points[i][0],points[i][1]);

        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));

        int start = points[0][0];
        int end = points[0][1];
        int count = 1;

        for(int i=1; i< points.length; i++){
            if(points[i][0] > end){
                count++;
                start = points[i][0];
                end = points[i][1];
            }else{      //points[i][0] <= end  => must have overlap
                start = Math.max(start,points[i][0]);
                end = Math.min(end,points[i][1]);
            }
        }
        return count;
    }

    public void insertion(int[][] points, int index, int value, int eValue){    //idiot, the one dimension data is array, not just take care single value => 之前明明就有consider到，這次居然忘了
        int i = index;
        for(;i>=0;i--){
            if(points[i][0]>value){
                points[i+1][0] = points[i][0];
                points[i+1][1] = points[i][1];
            }
            else
                break;
        }
        points[++i][0] = value;
        points[i][1] = eValue;
    }
}
