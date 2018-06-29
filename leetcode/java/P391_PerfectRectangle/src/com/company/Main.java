package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[][] rectangles = {{0,0,4,1},{7,0,8,2},{6,2,8,3},{5,1,6,3},{4,0,5,1},{6,0,7,2},{4,2,5,3},{2,1,4,3},{0,1,2,2},{0,2,2,3},{4,1,5,2},{5,0,6,1}};

        boolean isRectangle = Solution.isRectangleCover(rectangles);
        System.out.printf("%s", isRectangle?"Yes":"No");
    }
}
