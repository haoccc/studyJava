package study.tree;

import java.util.*;

public class SelfTest {


    /**
     * 94. 二叉树的中序遍历
     * 递归
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> tree = new ArrayList<>();
        // 递归
        getTree(root, tree);
        return tree;
    }

    private void getTree(TreeNode node, List<Integer> tree){
        if (node == null) return;
        getTree(node.left, tree);
        tree.add(node.val);
        getTree(node.right, tree);
    }

    /**
     * 中序遍历
     * 迭代
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> tree = new ArrayList<>();
        if (root == null) return tree;
        // 迭代
        Deque<TreeNode> deque = new LinkedList<>();
        Map<TreeNode, Boolean> map = new HashMap<>();

        deque.push(root);
        map.put(root, true);

        while (!deque.isEmpty()){
            TreeNode node = deque.peek();
            if (node.left != null && map.getOrDefault(node, true)){
                deque.push(node.left);
                map.put(node, false);
            } else {
                tree.add(node.val);
                deque.pop();
                if (node.right != null){
                    deque.push(node.right);
                }
            }
        }
        return tree;
    }
}
