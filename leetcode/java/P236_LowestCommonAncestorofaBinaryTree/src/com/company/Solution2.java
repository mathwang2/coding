package com.company;

public class Solution2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==p||root==q||root==null){
            return root;
        }
        TreeNode curr = root;
        TreeNode left =  lowestCommonAncestor(curr.left, p, q);
        TreeNode right = lowestCommonAncestor(curr.right, p, q);
        if(left!=null && right!=null)
            return curr;
        if(left!=null){
            return left;
        }else{
            return right;
        }
    }

}
