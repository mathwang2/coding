package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        Solution.rotate(matrix);
        for (int[] row:matrix){
            for (int i:row){
                System.out.printf("%d ", i);
            }
            System.out.printf("\n");
        }
    }
}
