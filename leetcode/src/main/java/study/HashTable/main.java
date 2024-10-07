package study.HashTable;

import java.util.*;

public class main {
    public int[] twoSum(int[] nums, int target) {
        final HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int sumNum = target - num;
            if (map.containsKey(sumNum)){
                return new int[]{map.get(sumNum), i};
            } else {
                map.put(num, i);
            }
        }
        return null;
    }

    /**
     * 三数之和
     *
     * 四数之和 三数之和再嵌套一层
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return res;
            if (i > 0 && nums[i-1] == nums[i]) continue;
            int left = i+1;
            int right = nums.length - 1;

            while (left < right){
                if (left > i + 1 && nums[left] == nums[left-1]){
                    left ++;
                    continue;
                }

                if (right < nums.length-1 && nums[right] == nums[right + 1]){
                    right --;
                    continue;
                }
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0){
                    final ArrayList<Integer> temp = new ArrayList<>();
                    Collections.addAll(temp, nums[i], nums[left], nums[right]);
                    res.add(temp);
                    left ++;
                    right --;
                } else if (sum < 0) {
                    left ++;
                } else {
                    right --;
                }
            }
        }
        return res;
    }



}
