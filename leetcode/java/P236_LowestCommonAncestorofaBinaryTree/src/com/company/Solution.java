package com.company;

import java.util.Vector;

public class Solution {

        public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (p==q) return p;
            return lowestCommonAncestor3(root, p, q, new boolean[1]);
        }

    public static TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q, boolean[] found) {
        found[0] = false;
        if (root==null) return null;

        boolean[] foundLeft = new boolean[1];
        boolean[] foundRight = new boolean[1];

        TreeNode left = lowestCommonAncestor3(root.left, p, q, foundLeft);
        TreeNode right = lowestCommonAncestor3(root.right, p, q, foundRight);


        if (left!=null) return left;
        if (right!=null) return right;

        if (root==p||root==q){
            if (foundLeft[0]||foundRight[0]) return root;
            found[0]=true;
            return null;
        }


        if (foundLeft[0] && foundRight[0]) return root;
        found[0] = foundLeft[0]||foundRight[0];
        return null;
    }



        static class Pair{
            TreeNode n=null;
            int totFound=0;
        }

    public static Pair lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
            Pair res = new Pair();
            if (root==null) return res;

            Pair left = lowestCommonAncestor2(root.left, p, q);
            Pair right = lowestCommonAncestor2(root.right, p, q);


            if (left.totFound==2) return left;
            if (right.totFound==2) return right;

            res.totFound = (root==p || root==q)?1:0;
            res.totFound = res.totFound + left.totFound+right.totFound;

            if (res.totFound>0) res.n = root;
            return res;
    }


    static void findPos(TreeNode n, TreeNode[] nodes, String[] nodesPos, int[] cntFound, String common){
        if (cntFound[0] == nodesPos.length) return;
        int i=0;
        while (i<nodes.length){
            if (n==nodes[i]) {
                --cntFound[0];
                nodesPos[i]=common;
                break;
            }
            ++i;
        }


        if (n.left!=null){
            findPos(n.left, nodes, nodesPos, cntFound, common+"0");
        }

        if (n.right!=null){
            findPos(n.right, nodes, nodesPos, cntFound, common+"1");
        }

    }

    static TreeNode pos2Node(TreeNode root, String pos){
        int b = pos.length();
        TreeNode n = root;
        for (int i=1; i<b; ++i){
            if (pos.charAt(i)=='0') {
                n=n.left;
            }else{
                n=n.right;
            }
        }

        return n;
    }



    static String findCommon(String[] posArray){
            StringBuilder sb = new StringBuilder();


            int i=0;
            while(true){
                if (i>=posArray[0].length()) return sb.toString();
                char c = posArray[0].charAt(i);

                for(int j=1; j<posArray.length; ++j){
                    if (i>=posArray[j].length() || posArray[j].charAt(i) != c){
                        return sb.toString();
                    }

                }

                sb.append(c);
                ++i;
            }

        }
}
