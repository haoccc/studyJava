package study.BP;

import javax.lang.model.element.NestingKind;
import java.util.*;

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

    /**
     * 139. 单词拆分
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        /**
         * 解决不了["car","ca","rs"], cars 的情况
         * 所以需要用动态规划和溯源。
         */
//        for (int j = 0; j < s.length(); ) {
//            int i = 0;
//            for (; i < wordDict.size(); i++) {
//                int end = wordDict.get(i).length()+j;
//                if (end>s.length()) continue;     // 避免溢出
//                String temp = s.substring(j, end);
//                if (temp.equals(wordDict.get(i))){
//                    j += wordDict.get(i).length();
//                    break;
//                }
//            }
//            if (i>=wordDict.size()) return false;
//        }
//        return true;

        /**
         * 动态规划
         */
        boolean[] bp = new boolean[s.length()+1];
        Arrays.fill(bp, false);
        bp[0] = true;

        // 先遍历背包
        for (int i = 1; i <= s.length(); i++) {     // 注意边界值 {“a”}, a 需要严格控制边界
            for (int j = 0; j < wordDict.size(); j++) {
                int curLength = wordDict.get(j).length();
                if (i-1 + curLength > s.length()) continue;
                boolean equals = s.substring(i - 1, curLength + i - 1).equals(wordDict.get(j));
                bp[i-1 + curLength] = equals & bp[i-1] || bp[i-1 + curLength];
            }
        }
        return bp[s.length()];
    }

    /**
     * 198. 打家劫舍
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int[] bp = new int[nums.length + 2];
        Arrays.fill(bp, 0);
        for (int i = 0; i < nums.length; i++) {
            int j = i + 2; // 在bp中的位置
            bp[j] = Math.max(nums[i] + bp[j-2], bp[j-1]);
        }
        return bp[nums.length + 1];

        /**
         * 优化，当前的状态只依赖前两次的状态
         */

    }

    /**
     * 213. 打家劫舍 II
     * @param nums
     * @return
     */
    public static int rob2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int len = nums.length;
        if (len == 1)
            return nums[0];
        return Math.max(robAction(nums, 0, len - 1), robAction(nums, 1, len));
    }

    static int robAction(int[] nums, int start, int end) {
        int x = 0, y = 0, z = 0;
        for (int i = start; i < end; i++) {
            y = z;
            z = Math.max(y, x + nums[i]);
            x = y;
        }
        return z;
    }

    /**
     * 121. 买卖股票的最佳时机
     * 贪心算法; 动态规划；
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
/*        // 当前值-最小值 和 之前的最大差值进行比较
        int mi = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            mi = Math.min(prices[i], mi);
            result = Math.max(result, prices[i] - mi);
        }
        return result;*/


        /**
         * 动态规划
         * 实际上等于贪心算法
         *  记录最小值
         *  再和最小值相减，判断能否得到最大利润
         */
/*        int[][] dp = new int[2][prices.length];
        dp[0][0] = -prices[0];
        dp[1][0] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[0][i] = Math.max(-prices[i], dp[0][i-1]);
            dp[1][i] = Math.max(dp[1][i-1], dp[0][i] + prices[i]);
        }
        return dp[1][prices.length-1];*/

        /**
         * 动态规划
         * 优化成一维数组
         */
        int[] dp = new int[2];
        dp[0] = -prices[0];
        dp[1] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[0] = Math.max(-prices[i], dp[0]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i]);
        }
        return dp[1];


    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 贪心算法；动态规划
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
/*        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) sum += prices[i] - prices[i-1];
        }
        return sum;*/

        /**
         * 动态规划
         */
        int[] dp = new int[2];
        // 0表示持有，1表示卖出
        dp[0] = -prices[0];
        dp[1] = 0;
        for(int i = 1; i <= prices.length; i++){
            // 前一天持有; 既然不限制交易次数，那么再次买股票时，要加上之前的收益
            dp[0] = Math.max(dp[0], dp[1] - prices[i-1]);
            // 前一天卖出; 或当天卖出，当天卖出，得先持有
            dp[1] = Math.max(dp[1], dp[0] + prices[i-1]);
        }
        return dp[1];
    }

    /**
     * 123. 买卖股票的最佳时机 III
     * 两段 贪心 超时
     *
     * @param prices
     * @return
     */
    public static int maxProfit3(int[] prices) {
        /**
         * 两段贪心
         */
/*        // 当前值-最小值 和 之前的最大差值进行比较
        int mi = Integer.MAX_VALUE;
        int result = 0;
        int sum = 0;
        for (int i = 0; i < prices.length-1; i++) {
            mi = Math.min(prices[i], mi);
            result = Math.max(result, prices[i] - mi);
            int mj = Integer.MAX_VALUE;
            for (int j = i; j < prices.length; j++) {
                mj = Math.min(prices[j], mj);
                sum = Math.max(sum, prices[j] - mj + result);
            }
        }
        return sum;*/

/*        *//**
         * 动态规划
         *//*
        int[][] dp = new int[4][prices.length];
        dp[0][0] = -prices[0];
        dp[1][0] = 0;
        dp[2][0] = -prices[0];
        int m = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            // 第一次交易等于之前的情况
            // 剩余金额
            dp[0][i] = Math.max(dp[0][i-1], -prices[i]);
            // 最大收入
            dp[1][i] = Math.max(dp[1][i-1], prices[i] + dp[0][i]);
            // 第二次交易
            // 持有金额： max(之前剩余最大金额（当前不买入）, 当前买入股票剩余金额)
            dp[2][i] = Math.max(dp[2][i-1], -prices[i] + dp[1][i]);
            dp[3][i] = Math.max(dp[3][i-1], prices[i] + dp[2][i]);
        }
        return dp[3][prices.length-1];
*/
        /**
         * 动态规划：优化成一维数组
         */
        int[] dp = new int[4];
        dp[0] = -prices[0];
        dp[2] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 第一次交易等于之前的情况
            // 剩余金额
            dp[0] = Math.max(dp[0], -prices[i]);
            // 最大收入
            dp[1] = Math.max(dp[1], prices[i] + dp[0]);
            // 第二次交易
            // 持有金额： max(之前剩余最大金额（当前不买入）, 当前买入股票剩余金额)
            dp[2] = Math.max(dp[2], -prices[i] + dp[1]);
            dp[3] = Math.max(dp[3], prices[i] + dp[2]);
        }
        return dp[3];
    }

    /**
     * 188. 买卖股票的最佳时机 IV
     * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * @param k
     * @param prices
     * @return
     */
    public static int maxProfitK(int k, int[] prices) {
        int[] dp = new int[2 * k];
        for (int i = 0; i < k; i++) {
            dp[2 * i] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < k; j++) {
                int x = j * 2; // 持有金额
                int y = j * 2 + 1;  // 当前收入
                int pre_income = 0;
                if (y>2){
                    pre_income = dp[y-2];
                }

                dp[x] = Math.max(dp[x], -prices[i] + pre_income);
                dp[y] = Math.max(dp[y], prices[i] + dp[x]);
            }
        }
        return dp[2 * k -1];
    }

    /**
     * 309. 买卖股票的最佳时机含冷冻期
     * @param prices
     * @return
     */
    public static int maxProfitFreeze(int[] prices) {
        int[] dp = new int[4];
        dp[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 第一次交易等于之前的情况
            // 剩余金额
            dp[0] = Math.max(dp[0], -prices[i]);
            // 最大收入
            dp[1] = Math.max(dp[1], prices[i] + dp[0]);
            // 第二次交易
            // 持有金额： max(之前剩余最大金额（当前不买入）, 当前买入股票剩余金额)
            dp[2] = Math.max(dp[2], -prices[i] + dp[1]);
            dp[3] = Math.max(dp[3], prices[i] + dp[2]);
        }
        return dp[3];

    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfitFee(int[] prices, int fee) {
        int minValue = prices[0];
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minValue) minValue = prices[i];
            if (prices[i] >= minValue && prices[i] <= minValue + fee){
                continue;
            }
            if (prices[i] > minValue + fee) {
                sum += prices[i] - minValue - fee;
                minValue = prices[i] - fee;
            }
        }
        return sum;
    }

    /**
     * 300. 最长递增子序列
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int maxValue = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                if (dp[i] >= maxValue) maxValue = dp[i];
            }
        }
        return maxValue;
    }

    /**
     * 674. 最长连续递增序列
     * @param nums
     * @return
     */
    public static int findLengthOfLCIS(int[] nums) {
/*        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int maxValue = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]){
                dp[i] = dp[i-1] + 1;
            }
            if (dp[i] >= maxValue) maxValue = dp[i];

        }
        return maxValue;*/

        /**
         * 优化成一维
         * 或者叫贪心算法
         */
        if (nums.length == 0) {
            return 0;
        }
        int dp = 1;
        int maxValue = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]){
                dp += 1;
            } else {
                dp = 1;
            }
            if (dp >= maxValue) maxValue = dp;

        }
        return maxValue;
    }


    /**
     * 718. 最长重复子数组
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length][nums2.length];
        for (int[] ints : dp) {
            Arrays.fill(ints, 0);
        }

        int maxValue = 0;

        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] == nums2[0]){
                dp[i][0] = 1;
                maxValue = 1;
            }
        }
        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] == nums1[0]) {
                dp[0][i] = 1;
                maxValue = 1;
            }
        }
//        if (nums1[0] == nums2[0]) dp[0][0] = 1;
        for (int i = 1; i < nums1.length; i++) {
            for (int j = 1; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]){
                    dp[i][j] = Math.max(1, dp[i-1][j-1]+1);
                }
                if (dp[i][j] > maxValue) maxValue = dp[i][j];
            }
        }
        return maxValue;
    }

    /**
     * 1143. 最长公共子序列
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
//        int maxValue = 0;
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + 1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }


    /**
     * 1035. 不相交的线
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]){
                    dp[i+1][j+1] = dp[i][j]+1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

    /**
     * 53. 最大子数组和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        /*// 贪心算法
        int maxValue = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            maxValue = Math.max(maxValue, sum);
            if (sum<=0) {
                sum = 0;
            }
        }
        return maxValue;*/

        // 动态规划
        int dp = nums[0];
        int maxValue = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (dp <=0 ){
                dp = nums[i];
            } else {
                dp += + nums[i];
            }
            maxValue = Math.max(maxValue, dp);
        }
        return maxValue;
    }

    /**
     * 392. 判断子序列
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence(String s, String t) {

        /*
        // 暴力算法， 还可以使用双指针，这个方法近似双指针
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j<t.length()){
                if (t.charAt(j) == s.charAt(i)){
                    break;
                }
                j++;
            }
            if (j == t.length()) return false;
            // 再加一次很重要
            // 两个字符匹配之后 j++, 及时移动到下一个字符 避免 aaaaaa, bbaaa 输出 ture;
            j++;
        }
        return true;*/


        int[][] dp = new int[s.length()+1][t.length()+1];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                if (s.charAt(i) == t.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + 1;
                }
                else {
                    dp[i+1][j+1] = dp[i+1][j];
                }
            }
        }
        return dp[s.length()][t.length()] == s.length();
    }

    /**
     * 115. 不同的子序列
     * @param s
     * @param t
     * @return
     */
    public static int numDistinct(String s, String t) {
        int[][] dp = new int[t.length()+1][s.length()+1];
        Arrays.fill(dp[0], 1);

        for (int i = 0; i < t.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (t.charAt(i) == s.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + dp[i+1][j];
                } else {
                    dp[i+1][j+1] = dp[i+1][j];
                }
            }
        }
        return dp[t.length()][s.length()];
    }


    /**
     * 583. 两个字符串的删除操作
     * 等同于最大相同子串
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int[] ints : dp) {
            Arrays.fill(ints, 0);
        }
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + 1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
                }
            }
        }

        return word1.length()+word2.length()-2*dp[word1.length()][word2.length()];
    }

    /**
     * 72. 编辑距离
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        for (int i = 1; i <= m; i++) {
            dp[i][0] =  i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word1.charAt(i) == word2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j];
                } else {
                    dp[i+1][j+1] = Math.min(dp[i][j+1], dp[i+1][j]);
                    dp[i+1][j+1] = Math.min(dp[i][j], dp[i+1][j+1]) + 1;
                }
            }
        }
        return dp[m][n];
    }


    /**
     * 647. 回文子串
     * 遍历顺序很重要
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        int count = 0;

        for (int i = len-1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i==j){
                    dp[i][j] = 1;
                    count ++;
                } else {
                    if (s.charAt(i) == s.charAt(j)){
                        if (i + 1 == j){
                            dp[i][j] = 1;
                            count ++;
                        } else {
                            if (dp[i+1][j-1] == 1){
                                dp[i][j] = 1;
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }


    /**
     * 516. 最长回文子序列
     * @param s
     * @return
     */
    public static int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        int count = 1;
        for (int i = len-1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                }
                else {
                    if (s.charAt(i) == s.charAt(j)){
                        if (i+1==j) {
                            dp[i][j] = 2;
                            count = Math.max(2, count);
                        } else {
                            dp[i][j] = dp[i+1][j-1] + 2;
                            count = Math.max(dp[i][j], count);
                        }
                    }
                    else {
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    }
                }
            }
        }
        return count;
    }



    public static void main(String[] args) {
        String s = "bbbab";
        longestPalindromeSubseq(s);

//        int[] nums = {1,3,6,7,9,4,10,5,6};
//        lengthOfLIS(nums);?
//        int[] prices = {2, 4, 1};
//        int s = maxProfitK(2, prices);
//        System.out.println(s);

//        int[] prices = {1,3,2,8,4,9};
//        int s = maxProfit3(prices);
//        System.out.println(s);

//        int[] nums = {1};
//        System.out.println(rob2(nums));
//        String s = "a";
////        String s = "leetcode";
//        List<String> wordDict = new ArrayList<>(List.of(new String[]{"a"}));
////        List<String> wordDict = new ArrayList<>(List.of(new String[]{"leet", "code"}));
//        System.out.println(wordBreak(s, wordDict));

//        int n = 13;
//        System.out.println(numSquares(n));
////        int amount = 0;
////        int[] coins = {1};
//////        int[] coins = {1, 2, 5};
////        System.out.println(coinChange(coins, amount));

    }




}




