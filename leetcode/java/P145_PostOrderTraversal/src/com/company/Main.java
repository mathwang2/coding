package com.company;

import java.util.Stack;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    public static Vector<Node> postOrderTraversal(Node root){
        Stack<Node> roots=new Stack<>();
        Node curr=root;
        Vector<Node> rst=new Vector<>();
        while (!roots.isEmpty() && curr!=null){
            if (curr.l!=null && !rst.contains(curr.l)){
                roots.push(curr);
                curr = curr.l;
                continue;
            }

            if (curr.r!=null && !rst.contains(curr.r)){
                roots.push(curr);
                curr = curr.r;
                continue;
            }

            rst.add(curr);
            if (!roots.isEmpty()){
                curr = roots.pop();
            }else{
                curr = null;
            }
        }

        return rst;
    }
}
