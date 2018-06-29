package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int res = Solution.trap(height);
        System.out.printf("res=%d", res);
    }
}
