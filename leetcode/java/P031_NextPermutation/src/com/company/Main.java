package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] nums = {1,5, 1};
        Solution.nextPermutation(nums);
        for (int i=0; i<nums.length; ++i){
            System.out.printf("%d ", nums[i]);
        }
    }
}
