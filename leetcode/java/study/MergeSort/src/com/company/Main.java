package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        long sum[] = {0, 1, 3, 1, 5, 2};
        int k = 6;
        int ans = Solution.mergeSort(sum, 0, sum.length, k);
        System.out.printf("ans=%d", ans);
    }
}
