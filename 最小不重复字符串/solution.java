/*题目：给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

*/



//解法1：暴力法
/*
暴力法解题思路非常的暴力，直接使用两次遍历的思想，
直接使用两个指针进行两次轮询，记录下每次不重复的子字符串的长度，并且每当子字符串的长度大于
最大长度时进行更新，最后返回最大长度


*/
class Solution {
     public int lengthOfLongestSubstring(String s) {
            int maxlength = 0;
           // int length=0;
            HashSet<Character> charset = new HashSet<Character>();
            for(int i=0;i<s.length();i++)
                for(int j=i;j<s.length();j++)
                {
                    if(!charset.contains(s.charAt(j)))
                    {
                        charset.add(s.charAt(j));
                        //length++;
                        if(charset.size()>maxlength)
                            maxlength=charset.size();
                    }
                    else
                    {
                        charset.clear();
                        break;
                    }
                }

        return maxlength;
    }
}




//解法2：滑动窗口
/*
滑动窗口法采用了一次遍历就可以找到结果大致思想为：
首先将头指针和尾指针都放在字符串的开头，然后向右滑动end指针，并且检测当前指向的字符是否再map中存在
如果不存在，将该字符放进map中，key值为该字符，value为该指针的索引+1（因为发现后面要更新指针的时候肯定时发现了重复字符
但是重复字符仅与start指向的 那个字符重复，此时窗口的其他字符均未重复，所以将指针移到该重复字符的下一位，即在map中的索引即可）
那么start的更新方式为，如果Math.max(ans,end-start+1)；
因为存在这样的情况当字符为，且end和start都指向第二个b时再进行end指针移动
a     b     b     a
            ↑↑ 
会发现此时又重复了，但此时的map.get(tmp)=1,因为我们时先进性的start的指针的移动然后再进行的map的更新
最后返回答案
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int start = 0,end = 0,ans = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        for(;end<s.length();end++){
            char tmp = s.charAt(end);
            if(map.containsKey(tmp)){
                start = Math.max(map.get(tmp),start);//当发生重复时，先修改start指针在进行map更新，所以若如果前面重复过一次，
													//start已经移动到了下一个冲突字符索引的后面，就不要在移动start了
            }
            map.put(tmp,end+1);
            ans = Math.max(ans,end-start+1);
        }
        return ans;
    }
}