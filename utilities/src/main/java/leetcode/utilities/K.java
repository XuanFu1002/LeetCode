/**
 * 274.
 * Given an array of integers citations where citations[i] is the number of citations
 * a researcher received for their ith paper, return the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: The h-index is defined as the
 * maximum value of h such that the given researcher has published at least h papers that
 * have each been cited at least h times.
 *
 *Example 1:
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and
 * each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the
 * remaining two with no more than 3 citations each, their h-index is 3.
 *
 * Example 2:
 * Input: citations = [1,3,1]
 * Output: 1
 */

package leetcode.utilities;

public class K {
    public int hIndex(int[] citations){
        /**
         * 用升序，確實可以找出「有n篇論文，被引用n次」=> 但是我這裡是排序好後，用論文的value直接當子做引用次數
         * ，也就是說不一定是從0、1做開始，這還算小事 => 主要是因為，無法求出[1,3,1]這種為符合定義而求的值1
         * ，然後如果用h-index來解釋1，就會是「有1篇論文，至少被引用1次」，但明明實際上觀察卻是，
         * 有「3篇論文!!至少被引用1次」=> 所以在更合理的情況下，升序即可解決
          */
//        first time
//        int tmp = 0;
//        int count = 0;
//        insert(citations);
//
//        for(int i=0; i<citations.length; i++){
//
//            if(i!= 0 && citations[i] == tmp) continue;
//            if(citations[i] == citations.length- i)         //identical value, there case to fix
//                return citations[i];        //only one will meet
//            tmp = citations[i];
//            if(tmp != 0)
//                count++;
//        }
//
//        return count;       //not found

        //second time
        int count = 0;
        insert(citations);

        for(int i=0; i<citations.length; i++){
            if(citations[i] >= count+1)
                count++;
            else
                break;
        }
        return count;
    }

    public void insert(int[] citations){
        for(int i=1; i<citations.length; i++){
            insertionSort(citations, i-1, citations[i]);
        }
    }

    public void insertionSort(int[] citations, int index, int val){
        int i=index;

        for(;i>=0;i--){
            // increase
//            if(val < citations[i])
//                citations[i+1] = citations[i];
            if(val > citations[i])
                citations[i+1] = citations[i];
            else
                break;
        }
        citations[++i] = val;
    }
}
