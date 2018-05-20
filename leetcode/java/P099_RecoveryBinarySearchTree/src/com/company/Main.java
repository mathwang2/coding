package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    public void MorrisTraversalCorrection(Node root){
        if (root==null) return;

        int curVal=0, preVal=0;
        Node curN = root, curN2= null, preN=null;

        boolean foundFirst = false;
        Node first = null, second = null;

        while (curN!=null){
            if (curN.l==null){
                curVal = curN.val;
                curN2 = curN;
                curN = curN.r;
            }else{
                Node loop = curN.l;
                while (loop.r!=null||loop.r!=curN){
                    loop = loop.r;
                }

                if (loop.r==null){
                    loop.r=curN;
                    curN=curN.l;
                }else{
                    loop.r=null;
                    curVal = curN.val;
                    curN2 = curN;
                    curN=curN.r;
                }
            }

            if (preN!=null && preVal > curVal){
                if (foundFirst){
                    second = curN2;
                    break;
                }else{
                    first = preN;
                    foundFirst=true;
                }
            }

            preN = curN2;

        }



    }
}
