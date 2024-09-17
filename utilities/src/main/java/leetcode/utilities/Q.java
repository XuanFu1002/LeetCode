/**
 * 42.
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it can trap after raining.
 *
 * Example 1:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by
 * array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 *
 * Example 2:
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 */

package leetcode.utilities;

import java.util.ArrayList;
import java.util.HashMap;

public class Q {
    /**
     * Key Point => 「夾」
     * 1. Separate as active and passive action
     * 2. Use two index, left and right to judge and control
     *
     * @param height
     * @return
     */
    public int trip(int[] height){
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        boolean increase = false;
        int tmp = 0;
        int hLeft = 0;
        int left = 0;      //index
        int right = 0;
        int rHeight = 0;
        int trip = 0;

        for(int i=1; i<height.length; i++){
            if(height[i] >= height[left]){
                right = i;
                //pay off
                for(int j=left+1; j<right; j++){
                    //int min = Math.min(height[left], height[right]);        //active不用，因為必是right>=left
                    trip += height[left] - height[j];
                }
                left = i;
                hLeft = i;
                rHeight = tmp = 0;
                hashMap.clear();
                increase = false;
            }else{      //夾不起來，就要看趨勢，de -> increase
                //夾不起來之後，就不能這樣寫了，因為這樣是橫掃 => 橫掃在夾得起來的時候可以使用，因為最後高度都一致
                //但夾不起來的時候，就得去看低轉高
                if(height[i] > height[i-1]){
                    increase = true;
                    if(height[i] > rHeight){
                        right = i;
                        rHeight = height[i];
                        hashMap.put(hLeft, right);
                    }

                    if(rHeight >= tmp){
                        tmp = rHeight;
                        hashMap.clear();
                        hashMap.put(left, right);
                    }
                }else{
                    if(increase){
                        increase = false;
                        hLeft = i-1;
                        rHeight = 0;
                    }
                }
            }
        }

        for(int a:hashMap.keySet()){
            for(int i=a; i<hashMap.get(a);i++){
                if(!(height[i]>height[hashMap.get(a)]))
                    trip += height[hashMap.get(a)] - height[i];
            }
        }

        return trip;
    }

    /**
     * 1. 一組低、高，記錄一次 => store in hashmap => 所以一開始for loop only record index
     * 2. 最後再對這一組hashmap做操作
     * @param height
     * @return
     */
    public int otherApproach(int[] height){
        HashMap<Integer,Integer> hashMap = new HashMap<>();         //put(key, value)
        int left = -1;
        boolean increase = false;
        int trip = 0;

        for(int i=1; i<height.length; i++){
            if(height[i] >= height[i-1]){
                increase = true;
                if(left != -1)      //若是用left = 0開頭的話，會抓到無效的東西，從-1開始，有效才assign => 所謂有效，就是指從increase變成decrease
                    hashMap.put(left,i);
            }else{
                if(left == -1)
                    left = i-1;
                if(increase){
                    increase = false;
                    left = i-1;
                }
            }
        }

        for(int a: hashMap.keySet()){
            System.out.println("Key:"+a+"\tValue:"+hashMap.get(a));
        }

        int[] stkLeft = new int[hashMap.size()];        //store index
        int[] stkRight = new int[hashMap.size()];
        ArrayList<Integer> arrayList = new ArrayList<>();
        int n = 0;

        for(int k: hashMap.keySet()){       //assign left right index
            stkLeft[n] = k;
            stkRight[n] = hashMap.get(k);
            n++;            //n also means the last assign index
        }

        n--;
        for(int i=hashMap.size()-1; i>=0;){     //modify hashmap
            for(;n>=0; n--){
                int tmp = height[stkRight[i]] - height[stkLeft[n]];
                if(tmp <= 0){
                    for (Integer integer : arrayList) {
                        hashMap.remove(integer);
                    }
                    arrayList.clear();
                    hashMap.put(stkLeft[n],stkRight[i]);
                    break;
                }
                arrayList.add(stkLeft[n]);
            }
//            for (Integer integer : arrayList) {
//                hashMap.remove(integer);
//            }
//            arrayList.clear();
            i = --n;
        }

        for(int a: hashMap.keySet()){
            System.out.println("After Key:"+a+"\tValue:"+hashMap.get(a));
        }

//        int right = 0;
        for(int i=0; i<height.length;){
            int min = -1;
            int right = 0;
            if(hashMap.containsKey(i)){
                min = Math.min(height[i],height[hashMap.get(i)]);
                right = hashMap.get(i);
            }
            if(min!=-1)
                for(int j=i+1; j<right; j++){
                    trip += min - height[j];
                }
//            i = ++right;
            if(right != 0)
                i = right;
            else
                i++;
        }

        return trip;
    }

    /**
     * 透過觀察，我確實知道，我的結論是「要能夾」，夾就要找left、right的min height
     * => 這邊chat，就將問題拆分得很簡單、明確
     * => 他這邊將left、right高度分開找，個別找去找他最他的容忍上限，i.e.用Max去找
     * ，個別找完並且存完資料後開始比對，left、right要找共同能達到的高度，i.e. min，然後再去扣除實際高度
     * => 就可以得出每個位置，可以裝多少水
     * @param height
     * @return
     */
    public int chatIdea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int waterTrapped = 0;

        // Fill leftMax array
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // Fill rightMax array
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // Calculate the water trapped at each bar
        for (int i = 0; i < n; i++) {
            int waterAtI = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (waterAtI > 0) {
                waterTrapped += waterAtI;
            }
        }

        return waterTrapped;
    }
}
