/**
 * 2024/10/11 (Fri.)
 * 1942. The Number of the Smallest Unoccupied Chair
 *
 * There is a party where n friends numbered from 0 to n - 1 are attending.
 * There is an infinite number of chairs in this party that are numbered from 0 to infinity.
 * When a friend arrives at the party, they sit on the unoccupied chair with the smallest number.
 *
 * For example, if chairs 0, 1, and 5 are occupied when a friend comes, they will sit on chair number 2.
 * When a friend leaves the party, their chair becomes unoccupied at the moment they leave.
 * If another friend arrives at that same moment, they can sit in that chair.
 *
 * You are given a 0-indexed 2D integer array times where times[i] = [arrivali, leavingi],
 * indicating the arrival and leaving times of the ith friend respectively, and an integer targetFriend.
 * All arrival times are distinct.
 *
 * Return the chair number that the friend numbered targetFriend will sit on.
 *
 * Example 1:
 * Input: times = [[1,4],[2,3],[4,6]], targetFriend = 1
 * Output: 1
 * Explanation:
 * - Friend 0 arrives at time 1 and sits on chair 0.
 * - Friend 1 arrives at time 2 and sits on chair 1.
 * - Friend 1 leaves at time 3 and chair 1 becomes empty.
 * - Friend 0 leaves at time 4 and chair 0 becomes empty.
 * - Friend 2 arrives at time 4 and sits on chair 0.
 * Since friend 1 sat on chair 1, we return 1.
 * 
 * Example 2:
 * Input: times = [[3,10],[1,5],[2,6]], targetFriend = 0
 * Output: 2
 * Explanation:
 * - Friend 1 arrives at time 1 and sits on chair 0.
 * - Friend 2 arrives at time 2 and sits on chair 1.
 * - Friend 0 arrives at time 3 and sits on chair 2.
 * - Friend 1 leaves at time 5 and chair 0 becomes empty.
 * - Friend 2 leaves at time 6 and chair 1 becomes empty.
 * - Friend 0 leaves at time 10 and chair 2 becomes empty.
 * Since friend 0 sat on chair 2, we return 2.
 *
 */

package leetcode.utilities;

import java.util.*;

public class AY {
    public int smallestChair(int[][] times, int targetFriend){
        int seatNo = 0;
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();

        int tarArrive = times[targetFriend][0];

        for(int[] arriveTime: times)
            if(arriveTime[0] <= tarArrive)
                map.put(arriveTime[0],arriveTime[1]);

        Map<Integer,Integer> occupiedSeat = new HashMap<>();        // <seat,leaveTime>
        Queue<Integer> leaveQue = new PriorityQueue<>();
        int chairNo = 0;

        Map<Integer,Integer> treeMap = new TreeMap<>(map);

//        for(int i=0; i<map.size(); i++){      => 因為是hashmap，沒有index，所以用foreach，keySet
        for(Integer arriveKey: treeMap.keySet()){
            if(!occupiedSeat.isEmpty()){        // 若不為空的話，接下來就要去judge此時間點是否有人離開，或是否有其他空位
//                if(occupiedSeat.containsValue(arriveKey)){      // damn!! 不是等於拉，是小於等於
                for(Map.Entry<Integer,Integer> entry: occupiedSeat.entrySet()){
//                    if(arriveKey.equals(entry.getValue())){
                    if(entry.getValue() != -1 && arriveKey >= entry.getValue()){
                        leaveQue.add(entry.getKey());       // 丟seatNo給queue
                        occupiedSeat.put(entry.getKey(),-1);
                    }
                }
                if(!leaveQue.isEmpty()){
                    seatNo = leaveQue.peek();
                    occupiedSeat.put(leaveQue.poll(),treeMap.get(arriveKey));
                }else{
                    seatNo = chairNo;
                    occupiedSeat.put(chairNo++,treeMap.get(arriveKey));
                }
            }else{
                seatNo = chairNo;
                occupiedSeat.put(chairNo++,treeMap.get(arriveKey));
            }
        }

        return seatNo;
    }
}
