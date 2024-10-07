package study.CharString;

import java.util.*;

public class main {

    /**
     *28. 找出字符串中第一个匹配项的下标
     * kmp
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        return 0;
    }


    /**
     * 49. 字母异位词分组
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = Arrays.toString(chars);
            List<String> orDefault = map.getOrDefault(key, new ArrayList<>());
            orDefault.add(str);
            map.put(key, orDefault);
        }
        return  new ArrayList<>(map.values());
    }

    /**
     * 128. 最长连续序列
     * 题解说的比较复杂，不太容易懂，简单来说就是每个数都判断一次这个数是不是连续序列的开头那个数。
     * 怎么判断呢，就是用哈希表查找这个数前面一个数是否存在，即num-1在序列中是否存在。存在那这个数肯定不是开头，直接跳过。
     * 因此只需要对每个开头的数进行循环，直到这个序列不再连续，因此复杂度是O(n)。 以题解中的序列举例:
     * [100，4，200，1，3，4，2]
     * 去重后的哈希序列为：
     * [100，4，200，1，3，2]
     * 按照上面逻辑进行判断：
     * 元素100是开头,因为没有99，且以100开头的序列长度为1
     * 元素4不是开头，因为有3存在，过，
     * 元素200是开头，因为没有199，且以200开头的序列长度为1
     * 元素1是开头，因为没有0，且以1开头的序列长度为4，因为依次累加，2，3，4都存在。
     * 元素3不是开头，因为2存在，过，
     * 元素2不是开头，因为1存在，过。
     * 完
     * @param nums
     * @return
     */
    static public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }
        int maxValue = 0;
        for (Integer i : set) {
            if (set.contains(i-1)){
                continue;
            } else {
                int n = i;
                int length = 1;
                while (set.contains(++n)){
                    length++;
                }
                maxValue = Math.max(maxValue, length);
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        int[] nums = {4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3};
        longestConsecutive(nums);
    }
}
