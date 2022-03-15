package com.example.testapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/**
 * 二叉树相关算法
 */
public class BinaryTree {
    TreeNode root = new TreeNode();

    /**
     * 新建一个二叉树
     */
    public void createTreeNode() {
        root.val = 1;
        root.left = new TreeNode();
        root.left.val = 2;

        root.right = new TreeNode();
        root.right.val = 3;

        root.left.left = null;
        root.left.right = new TreeNode();
        root.left.right.val = 4;

        root.right.left = new TreeNode();
        root.right.left.val = 5;
        root.right.right = new TreeNode();
        root.right.right.val = 6;
    }


    /**
     * 前序遍历
     * [1, 2, 4, 3, 5, 6]
     */
    @Test
    public void preorder() {
        createTreeNode();
        List<Integer> res = new ArrayList<>();
//        preorder(root, res);
//        System.out.println("递归法遍历为：" + Arrays.toString(res.toArray()));

        preorder2(root, res);
        System.out.println("迭代法遍历为：" + Arrays.toString(res.toArray()));
    }

    // 递归
    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }


    // 迭代
    public void preorder2(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        // 新建1个栈
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (!stack.isEmpty() || node != null) {
            // 当栈不为空并且此时的根结点不为null的时候，把此时根结点的值加入到数组中，根结点入栈，根结点的左子树变为根结点
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
    }

    /**
     * 中序遍历
     * [2, 4, 1, 5, 3, 6]
     */
    @Test
    public void inorder() {
        createTreeNode();
        List<Integer> res = new ArrayList<>();
//        inorder(root, res);
//        System.out.println("递归法遍历为：" + Arrays.toString(res.toArray()));

        inorder2(root, res);
        System.out.println("迭代法遍历为：" + Arrays.toString(res.toArray()));
    }

    // 递归
    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }


    // 迭代
    public void inorder2(TreeNode root, List<Integer> res) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (stack.size() > 0 || root != null) {
            //不断往左子树方向走，每走一次就将当前节点保存到栈中
            //这是模拟递归的调用
            if (root != null) {
                stack.add(root);
                root = root.left;
                //当前节点为空，说明左边走到头了，从栈中弹出节点并保存
                //然后转向右边节点，继续上面整个过程
            } else {
                TreeNode tmp = stack.pop();
                res.add(tmp.val);
                root = tmp.right;
            }
        }
    }

    /**
     * 后序遍历
     * [4, 2, 5, 6, 3, 1]
     */
    @Test
    public void postorderTraversal() {
        createTreeNode();
        List<Integer> res = new ArrayList<>();
//        postorder(root, res);
//        System.out.println("递归法遍历为：" + Arrays.toString(res.toArray()));

        postorder2(root, res);
        System.out.println("迭代法遍历为：" + Arrays.toString(res.toArray()));
    }

    // 递归
    public void postorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }

    public void postorder2(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
    }


}