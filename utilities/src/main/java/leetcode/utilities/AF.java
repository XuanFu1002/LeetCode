/**
 * 56.
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals, and return an array of the non-overlapping
 * intervals that cover all the intervals in the input.
 *
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
package leetcode.utilities;

import java.util.*;

public class AF {
    /**
     * 1. 首先就是，二維最多長度，也就是原先二維的長度
     * 2. 再來就是，一樣都是用two variable去管控頭尾
     * 3. 一維的長度，固定唯一，然後就用0、1去看就行，不用再寫inner for loop => 這裡長度固定，可以這樣寫
     * @param intervals
     * @return
     * 缺陷 =>
     * 1. 永遠會少一次，因為按照裡設計的方法，採取「比較」，index從一開始，而且當條件成立的時候，
     * assign到result的會是前一組數據，新的數據會等到下一次，條件成立的時候，才會被使用，那這樣設計就有一個缺陷，永遠慢一步，
     * 要馬function body結束的時候，另寫statement補上 => 要馬就換一種方式，那就是條件的部分，不要包含assign動作，
     * 只做參數調整，如此一來，不會有遺漏的情況，index也就不用從1開始，因為回傳的ary所看的index也有另外獨立，
     * 所以真實有幾組就看幾次，才更妥當
     * 2. 再來就是int[][]，一維的長度可以邊做邊給，但是二維就有點麻煩 => 所以改用List<int[]> => 選合適DS，方便後續操作
     */
    public int[][] merge(int[][] intervals){
//        int[][] result = new int[intervals.length][];  => n dimension就要new n times
        int[][] result = new int[intervals.length][2];      //此題固定都是2，所以就直接給，但若不固定的情況，那就在使用inner loop之前，做new，以每層二維的一維長度去做new
//        int start = 0;
//        int end = 0;
        int start = intervals[0][0];
        int end = intervals[0][1];
        int indexRes = 0;       // um need one index two control => 不能用i是因為他會一直跳動，但是我們需要固定，所以用i不make sense

        if(intervals.length == 1){
            result[0][0] = start;
            result[0][1] = end;
            return result;
        }

        for(int i=/*0*/1; i<intervals.length; i++){
//            if(i>0) => 與其以此區分index 0，讓他能順利assign，不如最初initialize就已[0][0]、[0][1]的value代入
//            result[indexRes] = new int[intervals[i].length];
            if(intervals[i][0] > end){      //beyond, therefore can't combine
                result[indexRes][0] = start;
                result[indexRes++][1] = end;
                start = intervals[i][0];
                end = intervals[i][1];
            }else{
                end = intervals[i][1];
                result[indexRes][0] = start;       //寫這兩行，就可以不用再for body之外，多寫一次assign statement
                result[indexRes][1] = end;         //只會會變成，多做好次assign的動作
            }
        }
        return result;
    }

    public int[][] twice(int[][] intervals){
//        List<List<Integer>> list = new ArrayList<>();
        List<int[]> list = new ArrayList<>();
        int[] tmp = new int[2];
        int start = intervals[0][0];
        int end = intervals[0][1];
        int index = 0;
        int tag= 0;

        for(int i=0; i<intervals.length; i++){
            if(i>0){
                if(intervals[i][0] > end)//{      //non-continuous, non-same interval
                    start = intervals[i][0];
//                    end = intervals[i][1];
//                }else

                if(intervals[i][0] < start) {
                    tag = 1;
                    start = intervals[i][0];
                }
                else
                    tag = 0;

                if(intervals[i][1] > end)
                    end = intervals[i][1];

                if(tag!=1 && tmp[0] != start && tmp[1] != end)
                    index++;
            }
            tmp[0] = start;
            tmp[1] = end;

            if(list.size() != index +1)
//                list.add(tmp)     => don't forget!! this is pass by address, so after tmp value changed, the previous value will disappear
                list.add(Arrays.copyOf(tmp,tmp.length));
            else
                list.set(index,Arrays.copyOf(tmp,tmp.length));
        }

        return list.toArray(new int[list.size()][]);
    }

    /**
     * [[start,end],[m,n]]
     * 1. [start,n]： end >= m & start > m (同區間，可銜接)
     * 2. [start,end] [m,n]： end < m (不同區間)
     * 3. [m,end]： m < start && n < end (重找下限)
     * 4. [m,n]： m < start && n > end
     * 4. [m,n] [start,end]：m < start & n < start (區間順序顛倒)
     *
     * 歸根這裡是直接對source data做處理 => 設計下來就侷限在，左右相鄰的compare而已
     * => 所以當遇上以下test case [[2,3],[4,5],[6,7],[8,9],[1,10]]時，就完全處理不了，
     * 按照我的code output:[[2,3],[4,5],[6,7],[1,10]]，
     * 但實際上是要回傳[[1,10]]
     * => 所以即使我把general case分析出來，並個別都做if-else卻還是有bug
     * @param intervals
     * @return
     */
    public int[][] third(int[][] intervals){
        List<int[]> list = new ArrayList<>();
        if(intervals.length == 0)
            return list.toArray(new int[0][]);
        int start = intervals[0][0];
        int end = intervals[0][1];
        int index = 0;
        list.add(new int[]{start,end});
        if(intervals.length == 1)
            return list.toArray(new int[list.size()][]);

        for(int i=1; i<intervals.length; i++){
            if(intervals[i][0] > end){
                index++;
                start = intervals[i][0];
                end = intervals[i][1];
                list.add(new int[]{start,end});         //count for additional memory space?
            }else if(intervals[i][0] == end){
                end = intervals[i][1];
                list.set(index, new int[]{start,end});
            }else{
                if(intervals[i][0] >= start){
//                    if(intervals[i][1] <= end){
//                        continue;
//                    }
                    if(intervals[i][1] > end){
                        end = intervals[i][1];
                        list.set(index, new int[]{start,end});
                    }
                }else{
                    if(intervals[i][1] < start){
                        list.set(index,new int[]{intervals[i][0],intervals[i][1]});
                        index++;
                        list.add(new int[]{start,end});
                    }else if(intervals[i][1] == start){
                        start = intervals[i][0];
                        list.set(index,new int[]{start,end});
                    }else{
                        if(intervals[i][1] > end){
                            start = intervals[i][0];
                            end = intervals[i][1];
                            list.set(index,new int[]{start,end});
                        }else{
                            start = intervals[i][0];
                            list.set(index,new int[]{start,end});
                        }
                    }
                }
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    /**
     * 看到別人說「sort」key word!!
     * => 對呀，如果我先對每組一維的start，先做大小排序，那後續在處理資料的時候，就完全不用去管加入list中的順序有誤
     * ，就可以順順的一直拿start，end往下處理，事情就變得單純，而且也都能符合generalize case
     * @param intervals
     * @return
     */
    public int[][] otherApproach(int[][] intervals){
        List<int[]> list = new ArrayList<>();
        if(intervals.length == 0)
            return intervals;

        for(int i=1; i<intervals.length; i++)
            sort(intervals,i-1,intervals[i][0],intervals[i][1]);

        int index = 0;
        int start = intervals[0][0];
        int end = intervals[0][1];

        for(int i=0; i<intervals.length; i++){
            if(i>0){
                if(intervals[i][0]>end){
                    index++;
                    start = intervals[i][0];
                    end = intervals[i][1];
                }else{          // only need to judge end, no start anymore, since we have already sorted data, therefore, newStart must greater or equal than oldStart, what we need to do is see whether update the end limit or not, or move to next interval
                    if(intervals[i][1] > end)
                        end = intervals[i][1];
                }
            }
            if(list.size() < index+1)       //actual length not match
                list.add(new int[]{start,end});
            else
                list.set(index, new int[]{start,end});
        }
        return list.toArray(new int[list.size()][]);
    }

    public void sort(int[][] intervals, int i, int left, int right){
        for(;i>=0;i--){
            if(intervals[i][0]>left){       //前往後塞，若前大於後
                intervals[i+1][0] = intervals[i][0];
                intervals[i+1][1] = intervals[i][1];
            }else
                break;
        }
        intervals[++i][0] = left;
        intervals[i][1] = right;
    }
}
