package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] gas  = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        int res = Solution.canCompleteCircuit(gas, cost);
        System.out.println(res);
    }
}
