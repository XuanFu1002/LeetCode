/**
 * 134.
 * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station
 * to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
 *
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel
 * around the circuit once in the clockwise direction, otherwise return -1.
 * If there exists a solution, it is guaranteed to be unique
 *
 * Example 1:
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Output: 3
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 *
 * Example 2:
 * Input: gas = [2,3,4], cost = [3,4,3]
 * Output: -1
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 */
package leetcode.utilities;

public class N {
    private int start = 0;
    private int end = -1;
    private int times = 0;

    /**
     * thrid times
     * 只能說，確實沒有夠清楚「題目的敘述」以及「我自己實作的流程過後，數據剩下甚麼」
     * 1. 題目說的若存在 => if there get approach, there must be only one sulution
     * 2. 我都已經return -1；已經過濾玩了，結果再設計for的時候，我還在思考設計關於，是否
     * 要走遍所有element以確保說，不會漏看，所以就形成多此一舉，我for的condition就去看了redundant case
     * =>用了%，被「circular」一詞迷惑
     */
    public int canCompleteCircuit(int[] gas, int[] cost){
        int gasCount = 0;
        int costCount = 0;
        int value = 0;

        for(int i=0; i<gas.length; i++){
            gasCount += gas[i];
            costCount += cost[i];
        }

        if(gasCount < costCount)
            return -1;

        for(int i=0; i<gas.length; i++){
            value+= gas[i] - cost[i];
            if(value < 0){
                value = 0;
                start = i+1;
            }
        }

        return start;
    }

    // second times => change to use inner for loop, to watch the time complexity => implement approach is familiar
//    public int canCompleteCircuit(int[] gas, int[] cost) {
//
//        for(int i=0; i<gas.length; i++) {
//            int val = 0;
//            int times = 0;
//            for(int j=i; j!=start;){
//                val =  val + gas[j] - cost[j];
//                if(val<0){
//                    start = -1;
//                    break;
//                }else{
//                    if(times == 0){
//                        times++;
//                        start = j;
//                    }
//                }
//                ++j;
//                j%=gas.length;
//
//                if(j == start){
//                    val =  val + gas[j] - cost[j];
//                    if(val<0){
//                        start = -1;
//                    }else{
//                        start = j;
//                    }
//                }
//            }
//            if(start != -1)
//                break;
//        }
//
//        return start;
//    }

    // first time
//    public int canCompleteCircuit(int[] gas, int[] cost){
//        int availableInd = -1;
//
//        for(int i=0; i<gas.length; i++){
//            availableInd = remainRecursive(gas, cost, i, 0);        //each loop started, value is zero make sense
//            if(availableInd == i)
//                break;
//            times = 0;
//        }
//
//        return availableInd;
//    }

    /**
     * recursive閱讀性很強，並且statement數很少 => 但是瘋狂function calling，既耗時、也耗空間
     * => leetcode少筆的test casae跑沒問題，但是資料量大的時候，就跟我說time limit exceeded
     *
     * 其實我這裡的recursive無非就是替代了inner loop做的事情 => 所以本質上都不是single loop => 沒快去哪，反而還表較慢ㄌㄟ
     * @param gas
     * @param cost
     * @param ind
     * @param val
     * @return
     */
    // first times
//    public int remainRecursive(int[] gas, int[] cost, int ind, int val){
//        val = val + gas[ind] - cost[ind];
//
//        if(val <0)
//            return -1;
//        else if(start == ind)
//            return ind;
//        else{
//            if(times == 0){
//                times++;
//                start = ind;
//            }
//            return remainRecursive(gas, cost, ++ind % gas.length, val);
//        }
//    }
}
