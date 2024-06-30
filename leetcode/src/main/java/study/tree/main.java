package study.tree;


import java.util.*;

public class main {
    public static void main(String[] args) {
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode root = new TreeNode(3, left, right);

        levelOrder1(root);

    }


    // 前序遍历 递归；迭代
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> result = new LinkedList<>();
        if (root == null){
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode temp = stack.pop();
            result.add(temp.val);
            if(temp.right!=null){
                stack.push(temp.right);
            }
            if(temp.left!=null){
                stack.push(temp.left);
            }
        }
        return result;
    }


    /**
     * 中序遍历，需要额外的指针记录当前值
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if(root == null){
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;

    }

    /**
     * 二叉树的层序遍历:迭代 todo
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;

        Queue<TreeNode> stack = new LinkedList<>();
        stack.offer(root);
        while (!stack.isEmpty()){
            List<Integer> tempResult = new LinkedList<>();
            int length = stack.size();
            while(length>0){
                TreeNode temp = stack.poll();
                if (temp.left != null) stack.offer(temp.left);
                if (temp.right != null) stack.offer(temp.right);
                length --;
                tempResult.add(temp.val);
            }
            result.add(tempResult);
        }
        return result;
    }

    /**
     * 二叉树的层序遍历:递归
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        levelOrderCheck(root, 0, result);
        return result;
    }

    public static void levelOrderCheck(TreeNode node, int deep, List<List<Integer>> result){
        if(node == null) return;

        if(result.size() <= deep){
            result.add(new LinkedList<>());
        }
        result.get(deep).add(node.val);
        levelOrderCheck(node.left, deep +1, result);
        levelOrderCheck(node.right, deep + 1, result);
    }


    /**
     * 二叉树的右视图: 层序遍历输出结果的最右边
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        levelOrderCheck(root, 0, result);
        List<Integer> x = new ArrayList<>();
        for (List<Integer> list : result) {
            x.add(list.get(list.size()-1));
        }
        return x;
    }


    /**
     * 对称二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        return recursiveIsSymmetric(root.left, root.right);

    }
    public boolean  recursiveIsSymmetric(TreeNode left, TreeNode right){
        if(left == null && right != null) return false;
        else if(left != null && right == null) return false;
        else if (left == null && right == null) return true;
        else if(left.val != right.val) return false;

        boolean outside = recursiveIsSymmetric(left.left, right.right);
        boolean inside = recursiveIsSymmetric(left.right, right.left);
        return outside & inside;
    }

    /**
     * 二叉树的最大深度: 迭代法
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int depth = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int length = queue.size();
            while(length>0){
                TreeNode temp = queue.poll();
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
                length --;
            }
            depth ++;
        }
        return depth;

    }

    /**
     * 二叉树的最大深度: 递归法
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        return maxDepthRecursive(root, 0);


    }

    public int maxDepthRecursive(TreeNode node, int depth){
        if(node == null){
            return depth;
        }
        int leftDepth = maxDepthRecursive(node.left, depth + 1);
        int rightDepth = maxDepthRecursive(node.right, depth + 1);

        return Math.min(leftDepth, rightDepth);
    }

    /**
     * 二叉树最小深度
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if(root.left == null && root.right == null){
            return 1;
        }
        return minDepthRecursive(root, 0);
    }
    public int minDepthRecursive(TreeNode node, int depth){
        if(node.left == null && node.right == null){
            return depth;
        }
        int leftDepth = Integer.MAX_VALUE;
        int rightDepth = Integer.MAX_VALUE;

        if (node.left != null){
            leftDepth = minDepthRecursive(node.left, depth + 1);
        }
        if (node.right != null){
            rightDepth = minDepthRecursive(node.right, depth + 1);
        }
        return Math.min(leftDepth, rightDepth);
    }


    /**
     * 110.平衡二叉树.
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return isBalancedRecursive(root) != -1;
    }

    public int isBalancedRecursive(TreeNode node) {
        if (node == null){
            return 0;
        }
        int left = isBalancedRecursive(node.left);
        // todo 判定 -1表示已经不是平衡二叉树了
        if (left == -1) return -1;
        int right = isBalancedRecursive(node.right);
        if (right == -1) return -1;

        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }



    /**
     * 257.二叉树的所有路径
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> resultOfBinaryTreePaths = new LinkedList<>();
        if (root == null){
            return resultOfBinaryTreePaths;
        }
        List<Integer> tempResult = new ArrayList<Integer>();
        tempResult.add(root.val);
        binaryTreePathsRecursive(root, tempResult, resultOfBinaryTreePaths);
        return resultOfBinaryTreePaths;
    }
    public void binaryTreePathsRecursive(TreeNode node, List<Integer> tempResult, List<String> resultOfBinaryTreePaths){
        if (node.left == null && node.right == null){
            StringJoiner sj = new StringJoiner("->");
            for (Integer i : tempResult) {
                sj.add(i.toString());
            }
            resultOfBinaryTreePaths.add(sj.toString());
        }
        if (node.left != null){
            tempResult.add(node.left.val);
            binaryTreePathsRecursive(node.left, tempResult, resultOfBinaryTreePaths);
            tempResult.remove(tempResult.size()-1);
        }
        if (node.right != null){
            tempResult.add(node.right.val);
            binaryTreePathsRecursive(node.right, tempResult, resultOfBinaryTreePaths);
            tempResult.remove(tempResult.size()-1);
        }
    }
}
