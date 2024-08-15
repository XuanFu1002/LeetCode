/**
 * 380.
 * Implement the RandomizedSet class:
 *
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present.
 * Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns
 * true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements
 * (it's guaranteed that at least one element exists when this method is called).
 * Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works
 * in average O(1) time complexity.
 *
 * Example 1:
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 *
 *  Output
 * [null, true, false, true, 2, true, false, 2]
 *
 * Explanation
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 * randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 * randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
 * randomizedSet.insert(2); // 2 was already in the set, so return false.
 * randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 */

//try to use array to implement random length

package leetcode.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class L {
    private int[] ary;
    private ArrayList<Integer> arrayList;
    private HashMap<Integer, Integer> hashMap;
    private int topIndex = -1;      //latest assign value index => 不用0做initial，因為++做起來就會失去意義，因為會變成指向下一個index，但是指向當前最後assign value的index會比較make sense

    public L(){
        ary = new int[10];      //after initialize, each element may be 0
        hashMap = new HashMap<>();
        arrayList = new ArrayList<>();
    }

    public boolean insert(int val){
//        if(ary.length-1 == topIndex)        //if last index or not?
//            expendAryLength();

        //用for loop O(topIndex)還是慢
//        for(int i=0; i<=topIndex/*ary.length*/; i++){        //感覺查到topIndex即可 => correct
//            if(ary[i] == val)
//                return false;
//        }
        if(hashMap.containsKey(val))
            return false;

//        ary[++topIndex] = val;
        arrayList.add(val);
        hashMap.put(val,arrayList.size()-1/*topIndex*/);

        return true;
    }

    /**
     * 使用swap，就不用一個個shift => 反正這裡也就只有問有沒有，fuck突然想到contain故事XDDD => 不過確實需要remove
     * @param val
     * @return
     */
    public boolean remove(int val){
//        if(topIndex == -1) return false;

//        int i = 0;
//        int tmp = ary[topIndex];
//        boolean tag = false;
//
//        for(;i<=topIndex;i++){
//            if(ary[i] == val){
//                tag = true;
//                break;
//            }
//        }
//
//        if(tag){
//            ary[topIndex] = ary[i];
//            ary[i] = tmp;
//            topIndex--;
//            return true;
//        }

        if(hashMap.containsKey(val)){
//            int tmp = ary[topIndex];      //因為這裡有用topIndex去做control，所以可以不用將value清空=0
//            ary[topIndex] = ary[hashMap.get(val)];
//            ary[hashMap.get(val)] = tmp;
//            topIndex--;
//            hashMap.replace(tmp, hashMap.get(val));      //刷新所屬index => 若是last index被remove，也就是同index自己remove自己時，replace就算沒有key，也不會有exception，頂多return null
//            hashMap.remove(val);
//            return true;

            int index = hashMap.get(val);     //將被移動者，刷新其arraylist index，以
            hashMap.replace(arrayList.get(arrayList.size()-1), index);
            arrayList.set(index, arrayList.get(arrayList.size()-1));
            arrayList.remove(arrayList.size()-1);
            hashMap.remove(val);
        }
        return false;
    }

    public int getRandom(){
        Random random = new Random();
//        int tmp = random.nextInt(topIndex+1);
//        return ary[tmp];
        return arrayList.get(random.nextInt(arrayList.size()));
    }

    private void expendAryLength(){
        int capacity = ary.length+10;
        int[] tmpAry = new int[capacity];

        for(int i=0; i<ary.length; i++){
            tmpAry[i] = ary[i];
        }
        ary = tmpAry;       //這邊可以嗎?? assign address?
    }

    public void getAry(){
        for(int i =0; i<=topIndex; i++){
            System.out.print(ary[i]+" ");
        }
        System.out.println();
    }
}
