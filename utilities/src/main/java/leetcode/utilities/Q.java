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
}
