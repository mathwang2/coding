package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //int[] candidates = {10,1,2,7,6,1,5}; int target = 8;
        //int[] candidates = {2,5,2,1,2}; int target = 5;
        int[] candidates = {1, 2}; int target = 5;
        List<List<Integer>> res = Solution.combinationSum2(candidates, target);
        for (List<Integer> res1:res){
            for (int i: res1){
                System.out.printf("%d ", i);
            }
            System.out.printf("\n");
        }
    }
}
