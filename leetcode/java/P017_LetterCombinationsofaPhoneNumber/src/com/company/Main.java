package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String digits = "23";
        List<String> res = Solution.letterCombinations(digits);
        for (String s:res){
            System.out.printf("%s \n", s);
        }
    }
}
