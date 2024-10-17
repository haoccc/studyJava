package hot100.TwoNode;

import java.util.*;

public class main {

    /**
     * 3. 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;
        char[] list = s.toCharArray();
        int max = 1, right = 0;
        HashSet<Character> chars = new HashSet<>();

        for (int i = 0; i < list.length; i++) {
            if (i != 0) chars.remove(list[i-1]);

            while (right < list.length && !chars.contains(list[right])){
                chars.add(list[right]);
                right++;
            }
            max = Math.max(max, right-i+1);
        }
        return max;
    }


    /**
     * 438. 找到字符串中所有字母异位词
     * @param s
     * @param p
     * @return
     */
     static public List<Integer> findAnagrams(String s, String p) {
         if (s.length() < p.length()) return new ArrayList<Integer>();
         char[] sArray = s.toCharArray();
         char[] pArray = p.toCharArray();

         int[] pMap = new int[26];
         for (char c : pArray) {
            pMap[c-'a'] ++;
         }

         List<Integer> ans = new ArrayList<>();

         int windowSize = p.length();
         int i = 0;
         while (i <= s.length()-windowSize) {
             int[] sMap = new int[26];
             for (int j = 0; j < windowSize; j++) {
                 if (pMap[sArray[i+j]-'a'] == 0){
                     i = i+j;
                     break;
                 }
                 sMap[sArray[i+j]-'a'] ++;
             }
             if (Arrays.equals(pMap, sMap)){
                 ans.add(i);
             }
             i++;
         }
         return ans;
     }

    public static void main(String[] args) {
        findAnagrams("baa", "aa");
    }
}
