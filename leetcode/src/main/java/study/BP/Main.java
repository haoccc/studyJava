package study.BP;

import java.util.Arrays;
import java.util.SortedMap;

public class Main {

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
                    list[i][j] = Math.max(list[i-1][j], list[i-1][j-nums[i-1]] + nums[i-1]);
                }
            }
        }
        boolean result = list[list.length-1][sum] == sum;
        return result;



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
     * 滚动数组：分割等和子集：一维数组优化
     * @param nums
     * @return
     */
    public static boolean canPartition2(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        int n = nums.length;
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        //总和为奇数，不能平分
        if(sum % 2 != 0) return false;
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for(int i = 0; i < n; i++) {
            for(int j = target; j >= nums[i]; j--) {
                //物品 i 的重量是 nums[i]，其价值也是 nums[i]
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }

            //剪枝一下，每一次完成內層的for-loop，立即檢查是否dp[target] == target，優化時間複雜度（26ms -> 20ms）
            if(dp[target] == target)
                return true;
        }
        return dp[target] == target;
    }

    /**
     * 回溯回超时
     * @param sum 综合
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


    /**
     * 1049. 最后一块石头的重量 II 滚动数组
     * @param stones
     * @return
     */
    public static int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int total = sum;
        sum = sum / 2;
        int[] target = new int[sum+1];
        for (int i = 0; i < stones.length; i++) {      // 石头重量
            for (int j = sum; j >= stones[i]; j--) {    // 容量
                // todo 这里是target[j] 不是j-i
                target[j] = Math.max(target[j], target[j-stones[i]] + stones[i]);
            }
        }
        return total - 2 * target[sum];
    }

    /**
     * 494. 目标和
     * @param nums
     * @param target
     * @return
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int left = sum + target;
        if (left % 2 != 0) return 0;
        if (Math.abs(target) > sum) return 0;
        left = left >> 1;

        int[][] list = new int[nums.length][left+1];

        // 初始化第一行
        if (nums[0] <= left) list[0][nums[0]] = 1;

        // 初始化第一列
        int countZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) countZero++;
            list[i][0] = (int) Math.pow(2, countZero);
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < left+1; j++) {
                if (j<nums[i]){
                    list[i][j] = list[i-1][j];
                } else {
                    // 取当前值 和不取当前值 两种情况
                    list[i][j] = list[i-1][j] + list[i-1][j-nums[i]];
                }
            }
        }
        return list[nums.length-1][left];

    }


    /**
     * 494. 目标和 滚动数组
     * todo 不太懂
     * @param nums
     * @param target
     * @return
     */
    public static int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int left = sum + target;
        if (left % 2 != 0) return 0;
        if (Math.abs(target) > sum) return 0;
        left = left >> 1;

        int[]  list = new int[left+1];

        // 初始化第一行
        list[0] = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = left; j >= nums[i]; j--) {
                // 取当前值 和不取当前值 两种情况
                list[j] = list[j] + list[j-nums[i]];
            }
        }
        return list[left];
    }

    /**
     * 518. 零钱兑换 II
     * @param amount
     * @param coins
     * @return
     */
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;

        /**
         * 先物品再背包
         */
        for (int i = 0; i<coins.length; i++){
            for (int j=coins[i]; j <= amount; j++){
                dp[j] += dp[j-coins[i]];
            }
        }
//        /**
//         * 先背包再物品 ×
//         * 会重复计算，是排列问题
//         */
//        for (int i = 1; i<=amount; i++){
//            for (int j=0; j < coins.length; j++){
//                if (coins[j] <= i){
//                    dp[i] += dp[i-coins[j]];
//                }
//            }
//        }

        return dp[amount];
    }

    /**
     * 377. 组合总和 Ⅳ
     * @param nums
     * @param target
     * @return
     */
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i=0; i<=target; i++){
            for (int j=0; j<nums.length; j++){
                if (i >= nums[j]){
                    dp[i] += dp[i-nums[j]];
                }
            }
        }
        return dp[target];
    }

    /**
     * 322. 零钱兑换
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount==0) return 0;
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
//        dp[0] = 1;
        for (int coin : coins) {
            if (coin <= amount){
                dp[coin] = 1;
            }

        }

        for (int i=0; i<=amount; i++){
            for (int j=0; j<coins.length; j++){
                if (coins[j] <= i){
                    dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
                }
            }
        }
        if (dp[amount] == amount + 1) return -1;
        else return dp[amount];
    }

    /**
     * 279. 完全平方数
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        int x = (int) Math.sqrt((double) n);
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
//        for (int i = 0; i <= x; i++) {
//            dp[i * i] = 1;
//        }
        dp[0] = 0;

        for (int i=0; i<=n; i++){
            for (int j=1; j * j<=i; j++){ // todo 逻辑好
                dp[i] = Math.min(dp[i], dp[i-j*j]+1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 13;
        System.out.println(numSquares(n));
//        int amount = 0;
//        int[] coins = {1};
////        int[] coins = {1, 2, 5};
//        System.out.println(coinChange(coins, amount));

    }




}




