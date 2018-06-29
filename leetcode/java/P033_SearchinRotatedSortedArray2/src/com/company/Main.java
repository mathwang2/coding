package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] nums = {1,3, 5};
        int target = 3;
        int res = Solution.search(nums, target);
        System.out.printf("%d", res);
    }
}
