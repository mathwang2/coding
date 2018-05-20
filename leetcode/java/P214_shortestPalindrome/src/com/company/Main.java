package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String str = "aaaaabcaabc";

        /*
        int[] failureTable = Solution.createFailureTable(str);

        for (int i=0; i<failureTable.length; ++i){
            System.out.printf("%d ", failureTable[i]);
        }
        System.out.printf("\n");
        */

        String sol = Solution.shortestPalindrome(str);
        System.out.printf("%s", sol);

    }
}
