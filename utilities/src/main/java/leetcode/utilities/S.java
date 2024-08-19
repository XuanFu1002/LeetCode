/**
 * 12.
 * Seven different symbols represent Roman numerals with the following values:
 *
 * Symbol	Value
 * I	1
 * V	5
 * X	10
 * L	50
 * C	100
 * D	500
 * M	1000
 * Roman numerals are formed by appending the conversions of decimal place values from
 * highest to lowest. Converting a decimal place value into a Roman numeral has the following rules:
 *
 * If the value does not start with 4 or 9, select the symbol of the maximal value that
 * can be subtracted from the input, append that symbol to the result, subtract its value,
 * and convert the remainder to a Roman numeral.
 * If the value starts with 4 or 9 use the subtractive form representing one symbol subtracted
 * from the following symbol, for example, 4 is 1 (I) less than 5 (V): IV and 9 is 1 (I) less
 * than 10 (X): IX. Only the following subtractive forms are used: 4 (IV), 9 (IX), 40 (XL), 90 (XC),
 * 400 (CD) and 900 (CM).
 *
 * Only powers of 10 (I, X, C, M) can be appended consecutively at most 3 times to represent
 * multiples of 10. You cannot append 5 (V), 50 (L), or 500 (D) multiple times. If you need to
 * append a symbol 4 times use the subtractive form.
 *
 * Given an integer, convert it to a Roman numeral.
 *
 * Example 1:
 * Input: num = 3749
 * Output: "MMMDCCXLIX"
 * Explanation:
 * 3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M)
 *  700 = DCC as 500 (D) + 100 (C) + 100 (C)
 *   40 = XL as 10 (X) less of 50 (L)
 *    9 = IX as 1 (I) less of 10 (X)
 * Note: 49 is not 1 (I) less of 50 (L) because the conversion is based on decimal places
 *
 * Example 2:
 * Input: num = 58
 * Output: "LVIII"
 * Explanation:
 * 50 = L
 *  8 = VIII
 *
 * Example 3:
 * Input: num = 1994
 * Output: "MCMXCIV"
 * Explanation:
 * 1000 = M
 *  900 = CM
 *   90 = XC
 *    4 = IV
 */
package leetcode.utilities;

public class S {
    private int digit;
    /**
     * 1)直接操作source data
     * 2)對data進行處裡，產生特性
     * @param num
     * @return
     */
    public String intToRoman(int num){
        if(num == 0)
            return "";

        //String sNumber = "";        //用append 最後再轉乘string，效能會更好，因為不用重新assign space?再搞清楚一次
        StringBuilder sNumber = new StringBuilder();
        digit = 0;
        int quotient = 0;
        int remainder = 0;
        //do while
        do{
            digit++;
            quotient = num / 10;
            remainder = num % 10;
            sNumber.insert(0, judgement(quotient, remainder));
            num /= 10;
        }while(quotient > 0);

        return sNumber.toString();
    }

    public String judgement(int quotient, int remainder){
        // String letter = "";
        StringBuilder letter = new StringBuilder();
        int tmpQ = remainder / 5;
        int tmpR = remainder % 5;

        switch(digit){
            case 1:     // 1~9,
                if(remainder == 4)
                    return "IV";
                if(remainder == 9)
                    return "IX";
                if(tmpQ != 0)
                    letter = new StringBuilder("V");
                for(int i=0; i<tmpR; i++){
                    // letter += "I";
                    letter.append("I");
                }
                return letter.toString();
            case 2:
                if(remainder == 4)
                    return "XL";
                if(remainder == 9)
                    return "XC";
                if(tmpQ != 0)
                    letter = new StringBuilder("L");
                for(int i=0; i<tmpR; i++){
                    // letter += "I";
                    letter.append("X");
                }
                return letter.toString();
            case 3:
                if(remainder == 4)
                    return "CD";
                if(remainder == 9)
                    return "CM";
                if(tmpQ != 0)
                    letter = new StringBuilder("D");
                for(int i=0; i<tmpR; i++){
                    // letter += "I";
                    letter.append("C");
                }
                return letter.toString();
            case 4:
                for(int i=0; i<quotient+remainder; i++)
                    letter.append("M");
                return letter.toString();
        }

        return letter.toString();
    }
}
