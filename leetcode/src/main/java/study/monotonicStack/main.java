package study.monotonicStack;

import java.util.*;

public class main {

    /**
     * 739. 每日温度
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] ans = new int[len];
        Deque<Integer> stack = new LinkedList<>();
        stack.addLast(0);
        for (int i = 1; i < len; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                ans[stack.peek()] = i - stack.pop();
            }
            stack.addFirst(i);
            System.out.println(stack);
        }
        return ans;
    }


    /**
     * 496. 下一个更大元素 I
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        int len = nums2.length;
        Arrays.fill(ans, -1);
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < len; i++) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]){
                map.put(nums1[stack.peek()], stack.peek());
                ans[stack.peek()] = i - stack.pop();
            }
            stack.addFirst(i);
            System.out.println(stack);
        }
        return ans;
    }


    /**
     * 503. 下一个更大元素 II
     * @param nums
     * @return
     */
    public static int[] nextGreaterElements(int[] nums) {
        //边界判断
        if(nums == null || nums.length <= 1) {
            return new int[]{-1};
        }
        int size = nums.length;
        int[] result = new int[size];//存放结果
        Arrays.fill(result,-1);//默认全部初始化为-1
        Stack<Integer> st= new Stack<>();//栈中存放的是nums中的元素下标
        for(int i = 0; i < 2*size; i++) {
            while(!st.empty() && nums[i % size] > nums[st.peek()]) {
                result[st.peek()] = nums[i % size];//更新result
                st.pop();//弹出栈顶
            }
            st.push(i % size);
        }
        return result;
    }

    /**
     * 42. 接雨水
     * @param height
     * @return
     */
    public int trap(int[] height) {
        /**
         * 动态规划
         */
/*        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        int len = height.length;

        leftMax[0] = height[0];
        rightMax[0] = height[len-1];

        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i-1]);
        }

        for (int i = len-2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i+1]);
        }
        int ans = 0;

        for (int i = 0; i < len; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];

        }
        return ans;*/

        /**
         * 单调栈
         */

        Deque<Integer> stock = new LinkedList<>();
        stock.push(0);

        
    }

    /**
     * 84. 柱状图中最大的矩形
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] leftVal = new int[len];
        int[] rightVal = new int[len];

        for (int i = 0; i < len; i++) {
            
        }
        
    }

    public static void main(String[] args) {
        int[] temperatures = {1,2,1};
        System.out.println(Arrays.toString(nextGreaterElements(temperatures)));;
    }
}
