/**
 * 11.
 * You are given an integer array height of length n. There are n vertical
 * lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the
 * container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 *
 * Example 1:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 *
 * Example 2:
 * Input: height = [1,1]
 * Output: 1
 */
package leetcode.utilities;

public class AB {
    /**
     * 這題TM降維打擊，就找「兩條」線而已，所以就基本的長*寬即可，不用考慮上麼高低問題
     * origin idea => 一樣適用two index，一頭一尾，用假的方式去算area，然後記得用最小的就好
     * @param heights
     * @return
     */
    public int maxArea(int[] heights){
        int i = 0;
        int j = heights.length-1;
        int maxArea = 0;
        int width = heights.length -1;      //寬是間距

        for(;i<j;){
            int tmpHeight = Math.min(heights[i],heights[j]);
            int tmpArea = tmpHeight * width--;

            if(tmpArea > maxArea)
                maxArea = tmpArea;

            if(heights[i] == heights[j]){
                if(heights[i+1] > heights[j-1]){
                    i++;
                }else if(heights[i+1] < heights[j-1]){
                    j--;
                }else{
                    i++;        //這邊隨便沒關係，因為也不會直接使用這個高度，要的話要等到第二次
                }
            }
            else if(tmpHeight == heights[i]){    //這邊要這樣，去比對是i or j index值的原因是因為，這個source data沒有遞增特性
                i++;
            }else
                j--;
        }
        return maxArea;
    }
}
