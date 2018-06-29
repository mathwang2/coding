package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] nums={2,0,1};
        Solution.sortColors(nums);
        for (int i=0; i<nums.length; ++i){
            System.out.printf("%d ", nums[i]);
        }
    }
}
