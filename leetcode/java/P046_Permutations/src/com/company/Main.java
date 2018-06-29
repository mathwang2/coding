package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] nums={1,2,3};
        List<List<Integer>> res = Solution.permute(nums);
        for(List<Integer> res1:res){
            for(int i:res1){
                System.out.printf("%d ", i);
            }
            System.out.printf("\n");
        }
    }
}
