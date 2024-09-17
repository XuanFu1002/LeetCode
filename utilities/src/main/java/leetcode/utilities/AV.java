/**
 * 172. 2024/9/16
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.
 *
 * Example 1:
 * Input: n = 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 *
 * Example 2:
 * Input: n = 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 *
 * Example 3:
 * Input: n = 0
 * Output: 0
 */
package leetcode.utilities;

public class AV {
    /**
     * => uh..題目要的是階乘之後數值，包含幾格0 => 我又理解錯了，他的目標是「尾隨」，也就是n*10^m的尾數幾個0的意思
     * @param n
     * @return
     * => damn我知道了，這題出錯的原因在於，階乘的數值太大，會一直超過我設定的type scope，所以我不能去嘗試記錄他的value，因為必定會
     * 有casting的問題，然後這樣就計算不了「尾巴有幾個0」 => 所以必須換一種思路，那就是看因素(factor)，看有幾組2*5，
     * 然後反正一定有2，所以看有幾個5就知道幾個0 => 因為市街乘，是連續數，所以能走到5那就必定經過2
     * => 這題n的range，0 ~ 10^4
     */
    public int trailingZeroes(int n){
        long factorial = 1;     //factorial永遠會遇到value超過scope，遇上casting問題，所以用此value做judge會有問題
        int zeros = 0;

        for(int i=n; i>0; i--)
            factorial *= i;

        System.out.println(factorial);

        while(factorial%10 == 0){           //eg. 13! = 1932053504, 不能直接一看到不是0就確定後面會沒有 => 要trace完一圈
            factorial/=10;
            zeros += 1;
        }

//        while(factorial>0){
//            if(factorial%10 == 0)
//                zeros += 1;
//            factorial/=10;
//        }

        return zeros;
    }

    public int improvement(int n){
//        return(int)(Math.log(n)/Math.log(5));        //換抵公式....omg....
        int count = 0;
        while (n > 0) {
            n /= 5;  // ㄜ就簡單計算有幾個5的因素就可以了
            count += n;
        }
        return count;
    }
}
