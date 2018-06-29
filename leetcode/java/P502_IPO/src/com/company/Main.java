package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int k=2, W=0;
        int[] Profits={1,2,3}, Capital={0,1,1};
        int x=Solution.findMaximizedCapital(k, W, Profits, Capital);
        System.out.printf("res=%d", x);
    }
}
