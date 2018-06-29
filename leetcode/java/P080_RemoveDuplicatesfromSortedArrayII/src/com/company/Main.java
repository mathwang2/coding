package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] nums = {0,0,1,1,1,1,2,3,3};
        int l = Solution.removeDuplicates(nums);
        for (int i=0;i<l;++i){
            System.out.printf("%d ", nums[i]);
        }
    }
}
