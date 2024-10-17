package hot100.NormalList;

import java.util.*;

public class main {
    /**
     * 53. 最大子数组和
     * 动态规划
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1]+nums[i], nums[i]);
            if (dp[i] > max) max = dp[i];
        }
        return max;
    }

    /**
     *
     56. 合并区间
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        Deque<int[]> stock = new LinkedList<>();
        stock.push(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            assert stock.peek() != null;
            if (stock.peek()[1] < intervals[i][0]){
                stock.push(intervals[i]);
            } else {
                assert stock.peek() != null;
                stock.peek()[1] = Math.max(stock.peek()[1], intervals[i][1]);
            }
        }
        return stock.toArray(new int[stock.size()][]);
    }


    /**
     * 189. 轮转数组
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if ((k = k % nums.length) == 0) return;

        // 倒序
        int left = 0; int right = nums.length-1;
        while (left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left ++;
            right --;
        }
        left = k;
        right = nums.length - 1;

        while (left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left ++;
            right --;
        }

        left = 0;
        right = k - 1;

        while (left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left ++;
            right --;
        }
        return;
    }

    /**
     * 238. 除自身以外数组的乘积
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        // 1,2,3,4
        int[] left = new int[nums.length];
        left[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i-1] * nums[i-1];
        }
        int r = 1;
        for (int i = nums.length-1; i >= 0; i--) {
            left[i] *= r;
            r *= nums[i];
        }
        return left;
    }


    /**
     * 73. 矩阵置零
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {
        boolean row = false;
        boolean col = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0){
                    if (i == 0) row = true;
                    if (j == 0) col = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        // 列
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0){
                for (int j = 1; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 行
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0){
                for (int j = 1; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        if (row){
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }

        if (col){
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

    }

    /**
     * 54. 螺旋矩阵
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int minLength = Math.min(matrix.length, matrix[0].length);
        int n = minLength / 2;
        for (int i = 0; i < n; i++) {
            // 上
            for (int j = i; j < matrix[0].length - i - 1; j++) {
                list.add(matrix[n][j]);
            }
            // 右
            for (int j = i; j < matrix.length - i - 1; j++) {
                list.add(matrix[j][matrix[0].length - i - 1]);
            }
            // 下
            for (int j = matrix[0].length - i - 1; j > i; j--) {
                list.add(matrix[matrix.length-1-i][j]);
            }
            // 左
            for (int j = matrix.length - 1 - i; j > i; j++) {
                list.add(matrix[j][i]);
            }
            if (minLength % 2 != 0){
            }

        }
        return list;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] setZero = new int[][]{{0, 1, 2, 0},{3, 4, 5, 2},{1, 3, 1, 5}};
        merge(intervals);

        setZeroes(setZero);
    }
}
