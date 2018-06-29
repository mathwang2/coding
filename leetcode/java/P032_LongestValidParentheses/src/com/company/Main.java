package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //String s = "(()";
       // String s=")()())";
       // String s="()(()";
       // String s=")";
        String s="(()(((()";
        int res = Solution.longestValidParentheses(s);
        System.out.printf("res=%d", res);
    }
}
