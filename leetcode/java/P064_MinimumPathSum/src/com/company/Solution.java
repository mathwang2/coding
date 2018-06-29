package com.company;

public class Solution {
    public int minPathSum(int[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        int[][] helper = new int[numRows][numCols];

        for (int j=numCols-1;j>=0;--j){
            for (int i=numRows-1;i>=0;--i){
                if (i==numRows-1 && j==numCols-1){
                    helper[i][j]=grid[i][j];
                }else if (j==numCols-1){
                    helper[i][j]=helper[i+1][j]+grid[i][j];
                }else if (i==numRows-1){
                    helper[i][j]=helper[i][j+1]+grid[i][j];
                }else{
                    helper[i][j]=grid[i][j]+Math.min(helper[i+1][j], helper[i][j+1]);
                }
            }
        }

        return helper[0][0];
    }
}
