/**
 * 125.
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase
 * letters and removing all non-alphanumeric characters, it reads the same forward and backward.
 * Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 *
 * Example 3:
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 */

package leetcode.utilities;

public class Y {
    public boolean isPalindrome(String s){
        s = s.replaceAll("[^a-zA-Z0-9]","");

        for(int i=0; i<s.length()/2; i++){
            int a = s.charAt(i);
            int b = s.charAt(s.length()-1-i);
            if(a+b>=130) {
                if (!(a == b || Math.abs(a - b) == 32)) {
                    return false;
                }
            }
            else if(a+b>=96) {
                if (a != b)
                    return false;
            }
            else{
                return false;
            }
        }
        return true;
    }
}
