package hot100.Tree;

import hot100.LinkList.ListNode;
import study.tree.TreeNode;

import javax.swing.*;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.RecursiveAction;

public class main {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return depth(root, 1);
    }

    public int depth(TreeNode node, int n){
        int leftDepth = node.left != null? depth(node.left, n+1): n;
        int rightDepth = node.right != null? depth(node.right, n+1): n;
        return Math.max(leftDepth, rightDepth);
    }

    /**
     * 226. 翻转二叉树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.right = left;
        root.left = right;
        return root;
    }

    /**
     * 101. 对称二叉树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {

//        recursiveIsSymmetric(root.left, root.right);
        while (true){

        }
    }

    public boolean  recursiveIsSymmetric(TreeNode left, TreeNode right){
        if (left == null && right == null) return true;
        else if (right == null || left == null || left.val != right.val) return false;
        return recursiveIsSymmetric(left.left, right.right) & recursiveIsSymmetric(left.right, right.left);
    }

    /**
     * 二叉树的直径
     * @param root
     * @return
     */
    int maxD = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter(root);
        return maxD;
    }

    private int maxDiameter(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = root.left==null? 0: maxDiameter(root.left) + 1;
        int right = root.right == null? 0: maxDiameter(root.right) + 1;
        maxD = Math.max(left + right, maxD);
        return Math.max(left, right);
    }

    /**
     * 102. 二叉树的层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            // 直接读取当前的 queue尺寸
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
/*        if (root == null) return new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList();
        queue.addLast(root);
        queue.addLast(null);
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            if (poll == null){
                list.add(secondList.stream().toList());
                secondList.clear();
                if (!queue.isEmpty()) queue.offer(null);
            }else {
                secondList.add(poll.val);
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
            }
        }
        return list;*/
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        return recursiveBST(nums, 0, nums.length-1);
    }

    private TreeNode recursiveBST(int[] nums, int start, int end){
        if (start == end) return new TreeNode(nums[start]);
        else if (end < start) return null;
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = recursiveBST(nums, start, mid -1);
        root.right = recursiveBST(nums, mid + 1, end);
        return root;
    }

    /**
     * 98. 验证二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        // 中序遍历
        List<Integer> list = new ArrayList<>();
        recursiveIsValidBST(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) return false;
        }
        return true;
    }

    private void recursiveIsValidBST(TreeNode root, List<Integer> list){
        if (root == null) return;
        recursiveIsValidBST(root.left, list);
        list.add(root.val);
        recursiveIsValidBST(root.right, list);
    }

    /**
     * 230. 二叉搜索树中第 K 小的元素
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        // 中序遍历, 迭代

        Deque<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) return root.val;
            root = root.right;
        }
        return 0;
    }


    /**
     * 199. 二叉树的右视图
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        // 层序遍历最后一个
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> stack = new LinkedList<>();
        stack.offer(root);
        while (!stack.isEmpty()){
            Deque<TreeNode> stack2 = new LinkedList<>();
            res.add(stack.getFirst().val);
            while (!stack.isEmpty()){
                TreeNode node = stack.poll();
                if (node.right != null) stack2.offer(node.right);
                if (node.left != null) stack2.offer(node.left);
            }
            stack = stack2;
        }
        return res;
    }

    /**
     * 114. 二叉树展开为链表
     * @param root
     */
    public void flatten(TreeNode root) {
        recursiveFlatten(root);
    }

    private TreeNode recursiveFlatten(TreeNode root){
        if (root == null) return root;
        TreeNode right = recursiveFlatten(root.right);
        TreeNode left = recursiveFlatten(root.left);
        if (left == null) return root;

        root.right = left;
        root.left = null;
        while (left.right != null) left = left.right;
        left.right = right;
        return root;
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 将preorder和inorder转化为list
        List<Integer> preList = new ArrayList<>();
        for (int i : preorder) preList.add(i);
        List<Integer> inList = new ArrayList<>();
        for (int i : inorder) inList.add(i);

        return recursive(preList, inList);
    }

    private TreeNode recursive(List<Integer> preorder, List<Integer> inorder){
        if (preorder.isEmpty() || inorder.isEmpty()) return null;

        int middle = preorder.get(0);
        int index = inorder.indexOf(middle);

        TreeNode root = new TreeNode();
        TreeNode left = recursive(preorder.subList(1, 1 + index), inorder.subList(0, index));
        TreeNode right = recursive(preorder.subList(1 + index, preorder.size()), inorder.subList(index + 1, inorder.size()));
        root.left = left;
        root.right = right;
        root.val = middle;
        return root;
    }

    public static void main(String[] args) {
        main main = new main();

        main.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
//        TreeNode treeNode15 = new TreeNode(15);
//        TreeNode treeNode7 = new TreeNode(7);
//        TreeNode treeNode20 = new TreeNode(20);
//        treeNode20.left = treeNode15;
//        treeNode20.right = treeNode7;
//
//        TreeNode treeNode9 = new TreeNode(9);
//        TreeNode treeNode3 = new TreeNode(3);
//        treeNode3.left = treeNode9;
//        treeNode3.right = treeNode20;
//
//        TreeNode nullNode = null;
//        main.levelOrder(nullNode);
    }
}
