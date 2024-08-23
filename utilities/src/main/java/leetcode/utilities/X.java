/**
 * 6.
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number
 * of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 * Example 1:
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 * Example 2:
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * Example 3:
 * Input: s = "A", numRows = 1
 * Output: "A"
 */
package leetcode.utilities;

public class X {
    /**
     * origin, idea => 先對資料進行處理，比且給他們label(i.e.所處行數)，然後再用依照line順序拼出string
     * after => 用取餘的話，要全trace；但若是用規律index直接去抓就不用
     * @param s
     * @param numRows
     * @return
     * 有在考慮，zigzag的string與line數不相符的情況，但根本不純在，因為line就是按照zigzag給的 => 所以line必是最高高度
     * => 結果還真有類似的問題，但他的問題是說，假設line數量給n，但是字串長度最多只有m (m<n)，這個時候形成不了zigzag(鋸齒狀)
     * => 所以也是直接回傳string即可，若是有形成zigzag的情況下，他給的line就必定是他的最高高度，不然題目不make sense
     */
    public String convert(String s, int numRows){
        if(numRows == 1 || s.length()<=numRows)     //這裡要多一個「numRows == 1」的judgment是因為有一種情況，那就是line給1，然後他的string超過1，不會形成zigzag但是就是一整串line
            return s;

        StringBuilder stringBuilder = new StringBuilder();
        int interval = 2 * (numRows - 1);
        int tmp;

        for(int i=0; i<numRows; i++){
            int index = i;
            if(index!=0 && index!=numRows-1){
                tmp = 2 * (numRows - index-1);
            }else{
                tmp = 0;
            }
            for(int j=0; j<s.length()-1;j++){
                if(index > s.length()-1)
                    break;
                stringBuilder.append(s.charAt(index));
                if(tmp!=0 && (index+tmp)<=s.length()-1)
                    stringBuilder.append(s.charAt(index+tmp));
                index += interval;
            }
        }
        return stringBuilder.toString();
    }
}
