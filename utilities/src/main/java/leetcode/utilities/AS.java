package leetcode.utilities;

import java.util.Stack;
public class AS {
    /**
     * origin idea => 透過split、regex、stringBuilder去實作，然後對於刪除上一層，則是用最近一次存data的長度
     * @param path
     * @return
     * 有缺陷 => 因為我是拿element的長度去做刪除，可是每一筆的長度不一樣，假設第一筆長度為1，第二筆為2，那此時我刪除的長度紀錄為2，來看sample "/a/bc/../.."，
     * 在這個case下，就體現出為何不能用長度去刪除，因為bc為2但是a只有1，所以就會exception => 所以要改用stack
     */
    public String simplifyPath(String path) {
        StringBuilder stringbuilder = new StringBuilder();
        String[] str = path.split("[/\\\\]+");         //e.g."/home/" 透過split之後會形成["","home"]會產生""(空元素)，很正常，因為別忘了，split的參數作用的是「間隔」，所以會有n+1個元素，然後這個API對於尾吧的空元素會幫我們過濾
        stringbuilder.append("/");
        int tmp=0;

        for(int i=0; i<str.length; i++){
            if(!str[i].isEmpty() && !str[i].equals(".")){
//                int tmp = str[i].length();      => value assign 應該要寫在append Statement那便比較make sense => 因為這才是紀錄上一筆，被加進去的長度 => 而不是每輪都assign，這會assign到..的長度
                //if(str[i] == "..")    => Object 做 == compare的時候不是純看value，所以回傳不見得是對的，所以改用equals()去看value的方式
                if(!str[i].equals("..")){
                    stringbuilder.append(str[i]).append("/");
                    tmp = str[i].length()+1;
                }else{
                    if(stringbuilder.length() ==1)      //進入這裡一定是要砍的，然後如果只剩下/那就不砍，直到進入下一層之後，才有得砍
                        continue;
                    stringbuilder.delete(stringbuilder.length()-tmp,stringbuilder.length());
                }
            }
        }

        return (stringbuilder.length() ==1)? stringbuilder.toString(): stringbuilder.substring(0,stringbuilder.length()-1);     //因為題目說最少要有"/"
    }

    public String chooseStack(String path){
        StringBuilder stringBuilder = new StringBuilder();
        Stack<String> stack = new Stack<>();
        String[] patterns = path.split("[/\\\\]+");

        for(String pattern:patterns){
            if(pattern.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            } else if (!pattern.isEmpty() && !pattern.equals(".")) {        //考量「不為空」是因為split之後肯能為分出空元素
                stack.push(pattern);
            }
        }

        for(String s:stack)
            stringBuilder.append("/").append("s");

        if(stringBuilder.length()==0)
            return "/";
        else
            return stringBuilder.toString();
    }
}
