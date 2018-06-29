package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        TreeNode p = root.left, q = root.right;
        TreeNode c = Solution.lowestCommonAncestor(root, p, q);
        System.out.println(c.val);
    }
}
