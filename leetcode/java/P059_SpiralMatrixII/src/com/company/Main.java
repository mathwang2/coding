package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int n=4;
        int[][] res = Solution.generateMatrix(n);
        for (int[] row:res){
            for (int i:row){
                System.out.printf("%d ", i);
            }
            System.out.printf("\n");
        }
    }
}
