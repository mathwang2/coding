package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] candidates = {1,2}; int target = 2;
        List<List<Integer>> res = Solution.combinationSum(candidates, target);
        for (List<Integer> l:res){
            for (int i:l){
                System.out.printf("%d ", i);
            }
            System.out.printf("\n");
        }

    }
}
