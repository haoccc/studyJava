package hot100.subString;

import java.util.HashMap;

public class main {
    /**
     * 560. 和为 K 的子数组
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        /**
         * 暴力破解
         */
//        int count = 0;
//        for (int i = 0; i < nums.length; i++) {
//            int sum = 0;
//            for (int j = i; j < nums.length; j++) {
//                sum += nums[j];
//                if (sum == k) count ++;
//            }
//        }
//        return count;

        /**
         * 前缀和
         */
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(nums[i], map.getOrDefault(sum, 0)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        subarraySum(new int[]{-1, -1, 1}, 0);
    }
}
