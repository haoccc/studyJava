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
        int[] leftMax = new int[height.length];
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
        return ans;

    }


    /**
     * 柱状图中最大的矩形
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];

        Deque<Integer> stock = new LinkedList<>();
        stock.push(0);

        left[0] = -1;
        right[heights.length-1] = heights.length;

        // 从左到右遍历
        for (int i = 1; i < heights.length; i++) {
            while (!stock.isEmpty() && heights[stock.peek()] >= heights[i]){
                stock.pop();
            }
            left[i] = (stock.isEmpty() ? -1 : stock.peek());
            stock.push(i);
        }

        // 从右到左遍历
        stock.clear();
        stock.push(heights.length-1);
        for (int i = heights.length-2; i >= 0; i--) {
            while (!stock.isEmpty() && heights[stock.peek()] > heights[i]){
                right[i] = stock.pop();
            }
        right[i] = (stock.isEmpty() ? heights.length : stock.peek());
            stock.push(i);
        }
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, (right[i]-left[i] - 1) * heights[i]);
        }
        return max;
    }


    public static void main(String[] args) {

        int[] heights = {3,6,5,7,4,8,1,0};
//        int[] heights = {2,1,5,6,2,3};
        largestRectangleArea(heights);
//        int[] temperatures = {1,2,1};
//        System.out.println(Arrays.toString(nextGreaterElements(temperatures)));;
    }
}
