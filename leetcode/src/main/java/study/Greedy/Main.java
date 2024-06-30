package study.Greedy;

import study.tree.TreeNode;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        int[] a = {1,7,4,9,2,5};
//        Arrays.sort(a);
//        for (int i : a) {
//            System.out.println(i);
//        }
//        System.out.println(a);
//        int[] gas = {1,2,3,4,5};
//        int[] cost = {3,4,5,1,2};
//        int i = canCompleteCircuit(gas, cost);
//        int[] a = {1,0,2};
//        int[] a = {1,3,4,5,2};
//        int s = candy(a);

//        int[] a = {3, 2, 1};
//        wiggleMaxLength(a);
//        int[][] a = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
//        int[][] a = {{-52, 31}, {-73, -26}, {82, 97}, {-65, -11}, {-62, -49}, {95, 99}, {58, 95}, {-31, 49}, {66, 98}, {-63, 2}, {30, 47}, {-40, -26}};
//        eraseOverlapIntervals(a);

//        String a = "ababcbacadefegdehijhklij";
//        partitionLabels(a);

        int a = 332;
        monotoneIncreasingDigits(a);

    }


    /**
     * 455 分发饼干
     * @param g 胃口值
     * @param s 饼干尺寸
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int index = 0;
        int count = 0;
        for (int i : s) {
            if (i >= g[index]){
                count ++;
                index ++;
            } else {
                continue;
            }
            if (index >= g.length){
                break;
            }
        }
        return count;
    }


    /**
     * 376. 摆动序列
     * @param nums
     * @return
     */
    public static int wiggleMaxLength(int[] nums) {
        if (nums.length == 1){
            return 1;
        } else if (nums.length == 2 && nums[0] != nums[1]) {
            return 2;
        }

        int maxLength = 1;
        int cur = 0;
        int pre = 0;
        for(int i = 1; i<nums.length; i++){
            cur = nums[i] - nums[i-1];
            if (cur > 0 && pre<=0){
                maxLength ++;
                pre = cur;
            } else if (cur< 0 && pre>=0) {
                maxLength ++;
                pre = cur;
            }
        }
        return maxLength;

    }

    /**
     * 53. 最大子数组和
     * @param nums -2,1,-3,4,-1,2,1,-5,4
     * @return
     */
    public static int maxSubArray(int[] nums) {
        /**
         * 版本一
         */
//        int maxSum = nums[0];
//        int sum = nums[0];
//        for(int i=1; i<nums.length; i++){
//            if (sum<0){
//                if (nums[i] > sum){
//                    sum = nums[i];
//                }
//            } else {
//                int newSum = sum + nums[i];
//                if( newSum > 0){
//                    sum = newSum;
//                } else {
//                    sum = nums[i];
//                }
//            }
//            maxSum = Math.max(maxSum, sum);
//
//        }
//        return maxSum;

        /**
         * 版本二
         */
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            maxSum = Math.max(maxSum, sum);
            if (sum < 0){
                sum = 0;
            }
        }
        return maxSum;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 1){
            return 0;
        }
        int profit = 0;
        for(int i=0;i<prices.length -1;i++){
//            if (prices[i] <= prices[i+1]){
//                profit += prices[i+1] - prices[i];
//            } else {
//                continue;
//            }
            profit += Math.max(prices[i+1] - prices[i], 0); //简洁，但是用时长
        }

        return profit;
    }


    /**
     * 55. 跳跃游戏
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int maxIndex = 0;

        for (int i =0; i< nums.length; i++){
            int curIndex = i + nums[i];
            maxIndex = Math.max(curIndex, maxIndex);
            if (maxIndex >=nums.length-1){
                return true;
            }
            if (maxIndex <= i){
                return false;
            }
        }
        return false;
    }

    /**
     * 45. 跳跃游戏Ⅱ
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums.length == 1){
            return 0;
        } else if (nums.length == 2) {
            return 1;
        }
        int curMaxIndex = nums[0];
        int nextMaxIndex = nums[0];
        int count = 1;
        for(int i = 0; i < nums.length; i++){
            nextMaxIndex = Math.max(nextMaxIndex, i + nums[i]);
            if (curMaxIndex >= nums.length -1){
                break;
            }
            if (i == curMaxIndex){
                count ++;
                curMaxIndex = nextMaxIndex;
            }
        }
        return count;
    }

    /**
     * 1005. K 次取反后最大化的数组和
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        for(int i = 0; i<nums.length;i++){
            if (k==0){
                break;
            }
            if(nums[i]<=0){
                nums[i] = -nums[i];
                k--;
            } else {
                break;
            }
        }
        if (k != 0){
            Arrays.sort(nums);
            if (k % 2 == 1){
                nums[0] = -nums[0];
            }
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        return sum;
    }

    /**
     * 134. 加油站
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        int index = 0;
        int minRes = Integer.MAX_VALUE;
        for(int i=0; i<gas.length; i++){
            sum += gas[i] - cost[i];
            if (sum < minRes && sum < 0){
                index = i;
                minRes = sum;
            }
        }
        if (sum < 0){
            return -1;
        } else {
            return (index + 1) % gas.length;
        }

    }

    /**
     * 135. 分发糖果
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings) {
        // [1, 0, 2]
        int[] flag = new int[ratings.length];
        int preVal = 0;
        for (int i=1; i<ratings.length; i++){
            if (ratings[i-1] < ratings[i]){
                flag[i] = ++preVal;
            } else {
                preVal = 0;
            }
        }

        for (int i=ratings.length-2; i>=0; i--){
            if (ratings[i] > ratings[i+1]){
//                preVal += 1;
                flag[i] = Math.max(flag[i], ++preVal);
            } else {
                preVal = 0;
            }
        }
        int sum = 0;
        for (int i : flag) {
            sum += i+1;
        }
        return sum;
    }

    /**
     * 860. 柠檬水找零
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> income = new HashMap<>();
        income.put(10, 0);
        income.put(20, 0);
        income.put(5, 0);
        for (int bill : bills) {
            if (bill == 20){
                if (income.get(10)>0 && income.get(5)>0){
                    income.put(10, income.get(10)-1);
                    income.put(5, income.get(5)-1);
                } else if (income.get(5)>=3){
                    income.put(5, income.get(5)-3);
                } else {
                    return false;
                }
            } else if (bill == 10){
                if (income.get(5)>=1){
                    income.put(5, income.get(5)-1);
                    income.put(10, income.get(10)+1);
                } else {
                    return false;
                }
            } else {
                income.put(5, income.get(5)+1);
            }
        }
        return true;
    }


    /**
     * 406. 根据身高重建队列
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b)->{
            if(a[0] == b[0]){
                return a[1]-b[1];
            } else {
                return b[0] - a[0];
            }
        });
        LinkedList<int[]> que = new LinkedList<>();
        for (int[] person : people) {
            que.add(person[1], person);
        }
        return que.toArray(new int[people.length][people[0].length]);
    }

    /**
     * 452. 用最少数量的箭引爆气球
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
        int count = 1;
        for(int i=1; i<points.length; i++){
            if (points[i][0] <= points[i-1][1]){
                points[i][1] = Math.min(points[i-1][1], points[i][1]);
            } else {
                count ++;
            }
        }
        return count;
    }


    /**
     * 无重叠区间
     * @param intervals
     * @return
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b)->{
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        int count = 1;
        int end = intervals[0][1];
        for(int i=1; i<intervals.length; i++){
            if (end < intervals[i][0]){
                count ++;
                end = intervals[i][1];
            }
        }

        return intervals.length - count;


//        Arrays.sort(intervals, (a, b)->{
//            if (a[0] == b[0]) return a[1] - b[1];
//            return a[0] - b[0];
//        });
//        int count = 0;
//        for(int i=1; i<intervals.length; i++){
//            if (intervals[i][0] < intervals[i-1][1] && intervals[i][1] > intervals[i-1][1]){
//                count ++;
//                intervals[i] = intervals[i-1];
//            } else if (intervals[i][0] < intervals[i-1][1] && intervals[i][1] <= intervals[i-1][1]){
//                count ++;
//            }
//        }
//        return count;
    }

    /**
     * 763. 划分字母区间
     * @param s
     * @return
     */
    public static List<Integer> partitionLabels(String s) {
        char[] ss = s.toCharArray();
        int[] record = new int[26];
        List<Integer> result = new ArrayList<>();
        for (int i=0; i<ss.length; i++) {
            record[ss[i]-'a'] = i;
        }
        int maxIndex = 0;
        int start = -1;
        for (int i=0;i<ss.length;i++) {
            maxIndex = Math.max(record[ss[i]-'a'], maxIndex);
            if (i==maxIndex){
                result.add(maxIndex-start);
                start = i;
            }
        }
        return result;


        // 将最远的字符位置找出来，用空间换时间
//        int maxIndex = 0;
//        int start = -1;
//        List<Integer> result = new ArrayList<>();
//        for(int i=0;i<s.length();i++){
//            int x = s.lastIndexOf(s.substring(i, i+1));
//            maxIndex = Math.max(maxIndex, x);
//            if (i==maxIndex){
//                result.add(maxIndex-start);
//                start = i;
//            }
//        }
//        return result;
    }

    /**
     * 56. 合并区间
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, (a,b)->{
            if (a[0]==b[0]) return a[1] -b[1];
            return a[0] - b[0];
        });

        for (int i=0; i<intervals.length-1;i++){
            if (intervals[i][1] < intervals[i+1][0]){
                result.add(intervals[i]);
            } else {
                intervals[i+1][0] = intervals[i][0];
                intervals[i+1][1] = Math.max(intervals[i][1], intervals[i+1][1]);
            }
        }
        result.add(intervals[intervals.length-1]);
        return result.toArray(new int[result.size()][]);
    }

    /**
     * 738. 单调递增的数字
     * @param n
     * @return
     */
    public static int monotoneIncreasingDigits(int n) {
        List<Integer> nList = new ArrayList<>();
        while (n != 0){
            nList.add(n % 10);
            n = n / 10;
        }
        int flag = 0;
        for(int i=0; i<nList.size()-1;i++){
            if (nList.get(i) < nList.get(i+1)){
                nList.set(i+1, nList.get(i+1)-1);
                flag = i;
            }
        }
        for(int i=0; i<=flag;i++){
            nList.set(i, 9);
        }
        int sum = 0;
        for(int i=nList.size()-1; i>=0;i--){
            sum = sum * 10 + nList.get(i);
        }
        return sum;
    }

    static int RED = 1;
    static int BLACK = 2;
    static int YE = 0;
    int count = 0;
    /**
     * 968. 监控二叉树
     * todo 分析二叉树：先从一个，两个，三个节点 一点点的开始分析；
     * @param root
     * @return
     */
    public int minCameraCover(TreeNode root) {
        int re = recurCover(root);
        if (re==YE) count++;
        return count;
    }
    private int recurCover(TreeNode node){
        int status = BLACK;
        if (node == null) return BLACK;
        if (node.left == null && node.right == null){
            return YE;
        }
        int left = recurCover(node.left);
        int right = recurCover(node.right);
        if (left == YE || right == YE){
            status = RED;
            count ++;
        } else if (left == RED || right == RED){
            status = BLACK;
        } else {
            status = YE;
        }
        return status;
    }
}
