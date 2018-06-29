package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] nums = {2,2}; int target = 2;
        int[] res = Solution.searchRange(nums, target);
        System.out.printf("%d, %d", res[0], res[1]);

    }
}
