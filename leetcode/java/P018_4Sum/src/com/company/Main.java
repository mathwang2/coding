package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //int[] nums={1, 0, -1, 0, -2, 2};
        int[] nums={0, 0, 0, 0};
        int target = 1;
        List<List<Integer>> res = Solution.fourSum(nums, target);

        for (List<Integer> i:res){
            for (int j:i){
                System.out.printf("%d ", j);
            }
            System.out.printf("\n");
        }

    }
}
