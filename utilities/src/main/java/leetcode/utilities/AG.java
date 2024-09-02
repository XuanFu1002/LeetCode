/**
 * 57.
 * You are given an array of non-overlapping intervals where intervals[i] = [starti, endi]
 * represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti
 * and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
package leetcode.utilities;

import javax.naming.PartialResultException;
import java.util.List;
import java.util.ArrayList;

public class AG {
    /**
     * 1.從題目給的sample，假定interval是遞增
     * 2.題目有給建議，可以回傳新的array
     * => 分別找newInterval a&b的容身處，找完之後，剩下正常assign即可
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int []> list = new ArrayList<>();
        int i = 0;

        while(i<intervals.length){
            if(newInterval[0]>intervals[i][1]){
                list.add(intervals[i]);
            }else if(intervals[i][0]>newInterval[1]){
                list.add(newInterval);      //this is call by address, that's right, but why can write like this, since we only need one interval data, so is ok
                newInterval = intervals[i];     //oh~, since here also pass by address, therefore, can see that value won't depend, since different address
            }else{
                newInterval[0] = Math.min(newInterval[0],intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1],intervals[i][1]);
            }
            i++;
        }

        list.add(newInterval);

        return list.toArray(new int[list.size()][]);        //basic concept return array, in this case our ary sort one dimension, there the return data by toArray will suppose to be two dimension
    }

    public void test(){
        List<int[]> list = new ArrayList<>();
        int[] ary = new int[]{1,4};
        list.add(ary);
        for(int[] a:list)
            System.out.println(a[0]+"\t"+a[1]);

        ary[0] = 10;

        for(int[] a:list)
            System.out.println(a[0]+"\t"+a[1]);     //output => {10,4}  => since list.add(ary), is pass by address, not just value, so dynamic
    }
}
