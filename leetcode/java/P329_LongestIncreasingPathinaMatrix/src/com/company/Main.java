package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[][] nums =
                {
                        {9,9,4},
                        {6,6,8},
                        {2,1,1}
                };

        System.out.printf("max = %d", Solution.longestIncreasingPath(nums));

    }
}
