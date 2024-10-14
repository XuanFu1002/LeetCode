/**
 * 2024/10/14 (Mon.)
 * 2530. Maximal Score After Applying K Operations, Medium
 *
 * You are given a 0-indexed integer array nums and an integer k. You have a starting score of 0.
 *
 * In one operation:
 *
 * choose an index i such that 0 <= i < nums.length,
 * increase your score by nums[i], and
 * replace nums[i] with ceil(nums[i] / 3).
 * Return the maximum possible score you can attain after applying exactly k operations.
 *
 * The ceiling function ceil(val) is the least integer greater than or equal to val.
 *
 * Example 1:
 * Input: nums = [10,10,10,10,10], k = 5
 * Output: 50
 * Explanation: Apply the operation to each array element exactly once.
 * The final score is 10 + 10 + 10 + 10 + 10 = 50.
 *
 * Example 2:
 * Input: nums = [1,10,3,3,3], k = 3
 * Output: 17
 * Explanation: You can do the following operations:
 * Operation 1: Select i = 1, so nums becomes [1,4,3,3,3]. Your score increases by 10.
 * Operation 2: Select i = 1, so nums becomes [1,2,3,3,3]. Your score increases by 4.
 * Operation 3: Select i = 2, so nums becomes [1,1,1,3,3]. Your score increases by 3.
 * The final score is 10 + 4 + 3 = 17.
 *
 * Constraints:
 * 1) 1 <= nums.length, k <= 105
 * 2) 1 <= nums[i] <= 109
 */
package leetcode.utilities;

import java.util.PriorityQueue;

public class BB {
    /**
     * 仔細觀察題目，會看到「in one operation」這個敘述，必且後面還給了"3個先後順序的步驟"
     * => 可想而知，這裡就是在敘述一次for loop iterate在做的三件事情 => 所以搞懂後implement即可
     * => 相較簡單，因為都有指示怎麼做
     *
     * 題目 => 1.從index中找最大value 2. 找到後把value加到score  3. 把被用過的value做ceiling replace
     * @param ary
     * @param k (times => 錯!! k這邊更貼切的意思是，amount => 要找k個element)
     * @return
     * => 因為每次要"找最大"element加到score，那為了加速後續for loop statement time complexity，
     * 就一樣老辦法，先對source data做加工處哩，讓他產生出我們想要的"資料特性"，後續就可以藉此快速操作
     * => sort
     */
    public long maxKElements(int[] ary, int k){
        long score = 0;
        int aryIndex = 0;
//        int max;
        int amount = 0;

        insertion(ary);
//        max = ary[aryIndex];

//        for(int i=0; i<k; i++){
////            if(ary[aryIndex]>=max){      實際寫出來就知道是要跟下一個index比，控且都已經sort過了，所以肯定前面做大
//            if(ary[aryIndex]>=ary[aryIndex+1]){
//                score += ary[aryIndex];
//                ary[aryIndex] = (int) Math.ceil(ary[aryIndex]/3);
//            }else{
//                i--;        // 這邊兩種approach，一種就是for loop，然後像這邊一樣去control index
//                            // 另外一種則是while，然後另外create一個variable去看是否到達k，然後停下
//                aryIndex++;
//            }
//        }

        // second times
//        while(amount!=k){
//            if(ary[aryIndex]>=ary[aryIndex+1]){
//                score += ary[aryIndex];
//                ary[aryIndex] = (int)Math.ceil(ary[aryIndex]/3);
//                amount++;
//            }else{
//                if(aryIndex == ary.length-1)    //這樣就可以避免，ary[aryIndex+1] indexOutOfBound
//                                                // => 最後一個index的時候，迴圈也讓它結束
//                                            // 但是!!會漏算最後一次 => 所以要再改架構
//                    break;
//                aryIndex++;
//            }
//        }

        // 這裡只對了一半，因為最後就是要湊滿k amount，所以要重新一個circle去尋找max
        // 好吧，還是想淺了，雖然第一層，使用sort之後，確實可以直接add，因為當前必最大，但是value做過ceiling"一次"之後，
        // 遞減就不存在了，所以在繼續使用「相鄰comparison」就不make sense了 => 如果要繼續用這DS思路的話，
        // 那就必須每次ceiling結束之後都做一次sort => 理解&結論正確，但是可想而知太慢了，leetCode的test case也time out
        // 另一種思路就是「max heap」=> 以前DS學過，Max Heap的最上層value，必是當前maximum，所以就可以直接用
        while(amount!=k){
            score += ary[aryIndex];
//            ary[aryIndex] = (int)Math.ceil(ary[aryIndex]/3);      //如果沒有把ary cast成double，那計算的時候，縱使ceil，但是你是拿int value去做，i.e.拿3，怪不得10/3得到3...
            ary[aryIndex] = (int)Math.ceil((double)ary[aryIndex]/3);
            insertion(ary);     // time complexity, O(n^3), ㄏㄏ
            amount++;

            if(aryIndex!=ary.length-1){
                if(ary[aryIndex]<ary[aryIndex+1]){      //不能<=，因為會浪費一個
                    aryIndex++;
                }
            }else{
//                break;
//                aryIndex %= ary.length;
                aryIndex = 0;
            }
        }

        return score;
    }

    /**
     * insertion sort =>「插入排序通過逐一取出元素，並將其插入到前面已排序的部分中的正確位置，來使得整個陣列變得有序。」
     * @param ary
     */
    public void insertion(int[] ary){
        for(int i=1; i<ary.length; i++){
            insertionSort(ary,i-1,ary[i]);
            //這裡用「i-1」是因為，第ith個element要加入到，前0~i-1「以排序好」的數列當中，自然是跟前i-1個element做比對
        }
    }

    public void insertionSort(int[] ary, int index, int value){
        int i = index;

        for(;i>=0;i--){
            if(ary[i]<value)
                ary[i+1] = ary[i];      //這裡不用兩兩swap，因為要安插的element value
                                        // 有被keep住，所以只要在最後的定點assign一次即可
            else
                break;      //因為是排序好的數列，所以只要一不成立，後面的iterate就不可能成立 => sort DS特性
        }
        ary[i+1] = value;       // +1 是因為，不論是否執行for loop condition的i--、
                                // 又或者最一開始argument就都被「-1」，所以assign的時候要加回來
    }

    /**
     * chat => 使用「Max Heap」
     * java的PriorityQueue default "Min-Heap"
     * @param ary
     * @param k
     * @return
     */
    public long otherApproach(int[] ary, int k){
        long score = 0;

        //透過自訂「Comparator(比較器)」變成Max-Heap => (a,b)表示兩個parameter，b-a
        //Comparator,只有在「元素重新排續」的時候，才會觸發，所以當只有一個element加入DS的時候，不會去執行comparator
        //再來就是，熟悉Max-Heap的規則，他背後是如何運作
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);

        for(int num: ary){
            maxHeap.add(num);
        }

        while(k>0){
            int maxVal = maxHeap.poll();      //Max Heap DS特性，最大值在最上
            score += maxVal;
            maxHeap.add((int)Math.ceil((double)maxVal/3));      //會執行comparator，保持Max-Heap
            k--;
        }
        return score;
    }
}
