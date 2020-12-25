import java.util.HashMap;
import java.util.Map;

class Scratch {
    public static void main(String[] args) {
        
    }



    public int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> m = new HashMap();

        int maxLen = 0;
        int left = 0;

        for (int i=0; i<s.length();i++){
            if (m.containsKey(s.charAt(i)) && m.get(s.charAt(i)) >= left){
                left = m.get(s.charAt(i)) + 1;
            }
            int curLen = i - left + 1;
            maxLen = Math.max(maxLen, curLen);
            m.put(s.charAt(i), i);
        }

        return maxLen;

    }


}