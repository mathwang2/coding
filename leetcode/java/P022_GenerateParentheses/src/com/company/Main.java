package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int n=3;
        List<String> res = Solution.generateParenthesis(n);
        for (String s:res){
            System.out.println(s);
        }
    }
}
