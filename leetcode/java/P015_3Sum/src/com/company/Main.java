package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
       // int[] nums = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        int[] nums = {1,-1,-1,0};
        List<List<Integer>> rst = Solution.threeSum(nums);
        for (List<Integer> g:rst){
            for (Integer i:g){
                System.out.printf("%d ", i);
            }
            System.out.printf("\n");
        }
    }
}
