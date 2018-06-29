package com.company;

public class Solution {
    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix==null || matrix.length==0||matrix[0]==null||matrix[0].length==0) return 0;
        int[][] dyTable =new int[matrix.length][matrix[0].length];
        int curr = MIN;
        for (int i=0; i<matrix.length; ++i){
            for (int j=0; j<matrix[0].length; ++j){
                curr = Math.max(curr, fillInDyTable(matrix, dyTable, i, j));
            }
        }

        return curr;

    }

    public static final Integer MIN = 0;

    public static int fillInDyTable(int[][] matrix, int[][] dyTable, int x, int y){
        if (dyTable[x][y]>MIN) return dyTable[x][y];

        int cur = -1;

        if (x-1 >=0 && matrix[x-1][y]>matrix[x][y]){
            cur = Math.max(cur, fillInDyTable(matrix, dyTable, x-1, y));
        }

        if (y-1 >=0 && matrix[x][y-1]>matrix[x][y]){
            cur = Math.max(cur, fillInDyTable(matrix, dyTable, x, y-1));
        }

        if (x+1 <matrix.length && matrix[x+1][y]>matrix[x][y]){
            cur = Math.max(cur, fillInDyTable(matrix, dyTable, x+1, y));
        }

        if (y+1 <matrix[0].length && matrix[x][y+1]>matrix[x][y]){
            cur = Math.max(cur, fillInDyTable(matrix, dyTable, x, y+1));
        }

        if (cur==-1){
            dyTable[x][y]=1;
            return 1;
        }

        dyTable[x][y]=1+cur;
        return 1+cur;

    }
}
