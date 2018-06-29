package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
      //  String board = "WRRBBW", hand= "RB";


        String board = "WWGWGW", hand= "GWBWR";
        int res = Solution.findMinStep(board, hand);
        System.out.printf("res=%d", res);
    }
}
