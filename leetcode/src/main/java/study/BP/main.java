package study.BP;

import java.util.Arrays;

public class main {
    public static void main(String[] args) {
//        int n = 4;
//        System.out.println(climbStairs(n));;

//        int[][] a = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
//        int[][] a = {{1, 0}};
//        uniquePathsWithObstacles(a);

//        int a = 5;
//        numTrees(a);

//        int[] a = {1, 5, 5, 11, 2, 4, 1, 3};
        int[] a = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};
        System.out.println(canPartition(a));;
    }

    /**
     * 509. 斐波那契数
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n <= 1) return n;
        return fib(n-1) + fib(n-2);
    }

    /**
     * 70. 爬楼梯
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n <=3) return n;
        int[] record = new int[n+1];
        record[0] = 0;
        record [1] = 1;
        record [2] = 2;
        record [3] = 3;
        for (int i=4; i<=n; i++){
            record [i] = record[i-1] + record[i-2];
        }
        return record[n];
    }

    /**
     * 746. 使用最小花费爬楼梯
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] record = new int[cost.length+1];
        record[0] = 0;
        record[1] = 0;
        for (int i=2; i<cost.length+1; i++){
            record[i] = Math.min(record[i-2] + cost[i-2], record[i-1] + cost[i-1]);
        }
        return record[cost.length];
    }

    /**
     * 62. 不同路径
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        /**
         * 可以优化成一维数组
         */
        if (n <=1 || m<=1){
            return 1;
        }
        int[] record = new int[n];

        for(int i=0;i<n;i++){
            record[i] = 1;
        }

        for (int i=1; i<m; i++){
            for (int j=1; j<n; j++){
                record[j] = record[j] + record[j-1];
            }
        }
        return record[n-1];


//        if (n <=1 || m<=1){
//            return 1;
//        }
//        int[][] record = new int[m][n];
//        for(int i=0;i<m;i++){
//            record[i][0] = 1;
//        }
//        for(int i=0;i<n;i++){
//            record[0][i] = 1;
//        }
//
//        for (int i=1; i<m; i++){
//            for (int j=1; j<n; j++){
//                record[i][j] = record[i-1][j] + record[i][j-1];
//            }
//        }
//        return record[m-1][n-1];
    }


    /**
     * 63. 不同路径 II
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {


        /**
         * 节约内存
         */
        // 把到达不了的位置设为1
        int flag = 0;
        for(int i=0; i<obstacleGrid.length; i++){
            if (obstacleGrid[i][0]==1) flag = 1;
            obstacleGrid[i][0] = flag;
        }
        flag = 0;
        for(int i=0; i<obstacleGrid[0].length; i++){
            if (obstacleGrid[0][i]==1) flag = 1;
            obstacleGrid[0][i] = flag;
        }
        for (int i=1; i<obstacleGrid.length; i++){
            for (int j=1; j<obstacleGrid[0].length; j++){
                if (obstacleGrid[i][j-1] == 1 && obstacleGrid[i-1][j] == 1 || obstacleGrid[i][j] == 1){
                    obstacleGrid[i][j] = 1;
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }

        // 反转值
        for (int i=0; i<obstacleGrid.length; i++){
            for (int j=0; j<obstacleGrid[0].length; j++){
                if (obstacleGrid[i][j] == 1){
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = 1;
                }
            }
        }

        for(int i=1; i<obstacleGrid.length; i++){
            for (int j=1; j<obstacleGrid[0].length; j++){
                if (obstacleGrid[i][j] != 0) obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
            }
        }

        return obstacleGrid[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }

    /**
     * 343. 整数拆分
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int [] list = new int[n+1];
        if (n==2) return 1;
        list[0] = 0;
        list[1] = 1;
        list[2] = 1;
        int temp = 0;
//        for (int i=3; i<=n; i++){
//            for (int j = 1; j<i; j++){
//                int right = i-j;
//                temp =  Math.max(temp, Math.max(j, list[j]) * Math.max(right, list[right]));
//            }
//            list[i] = temp;
//        }

        /**
         * 优化循环过程
         */
        for (int i=3; i<=n; i++){
            // 这里的 j 其实最大值为 i-j,再大只不过是重复而已，
            //并且，在本题中，我们分析 dp[0], dp[1]都是无意义的，
            for (int j = 1; j<=i-j; j++){
                int right = i-j;
                temp =  Math.max(temp, Math.max(j*right, j * list[right]));
                // j * (i - j) 是单纯的把整数 i 拆分为两个数 也就是 i,i-j ，再相乘
                // 而j * dp[i - j]是将 i 拆分成两个以及两个以上的个数,再相乘。
                // 如果j再拆成q+p 那么可以退化成 q * (p+i-j)
            }
            list[i] = temp;
        }
        return list[n];
    }


    /**
     * 96. 不同的二叉搜索树
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        if (n==1) return 1;
        if (n==2) return 2;
        int[] list = new int[n+1];
        list[0] = 0;
        list[1] = 1;
        list[2] = 2;
        list[3] = 5;

        for (int i=4; i<=n; i++){
            int temp = 0;
            for (int j=1; j<=i; j++){
                int left = list[j-1]==0? 1: list[j-1];
                int right = list[i-j]==0? 1: list[i-j];
                temp += left * right;
            }
            list[i] = temp;
        }
        return list[n];
    }

    /**
     * 416. 分割等和子集
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((float)sum % 2 != 0) return false;
        sum = sum / 2;

        int[][] list = new int[nums.length + 1][sum+1];

        for (int i=1; i<nums.length+1; i++){    // 重量
            for (int j=1; j<sum+1; j++){    // 容量
                if (nums[i-1] > j){
                    // 如果当前重量比容量大，直接采用上一个重量的最大值
                    list[i][j] = list[i-1][j];
                } else {
                    // 当前容量大于重量，比较前一状态 和 当前容量-当前重量状态 的 最大值
                    list[i][j] = Math.max(list[j-nums[i-1]][j], list[i-1][j]);
                }
            }
        }
        return false;



        /**
         * 尝试回溯 超时
         */
        /*int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((float)sum % 2 != 0) return false;
        boolean x = tracePartition(0, 0, nums, sum/2);
        return x;*/
    }

    /**
     * 回溯回超时
     * @param sum
     * @param start
     * @param nums
     * @param target
     * @return
     */
    private static Boolean tracePartition(int sum, int start, int[] nums, int target){
        if (sum == target) return true;
        if (start>=nums.length) return false;
        boolean result = false;
        System.out.println(start + "+" + nums[start]);
        for (int i=1; i>=0; i--){
            sum += nums[start] * i;
            result = tracePartition(sum, start+1, nums, target);
            if (result) return result;
            sum -= nums[start] * i;
        }
        return result;
    }

}
