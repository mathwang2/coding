package com.company;

public class Solution {
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i=0; i<=(n-1)/2; ++i){
            for (int j=0; j<=n/2-1; ++j){
                rotate1(matrix, i, j, n);
            }
        }
    }

    public static void rotate1(int[][] matrix, int i, int j, int n){
        int i0 = i, j0=j, i1=j, j1=n-1-i, i2=n-1-i, j2=n-1-j, i3=n-1-j, j3=i;
        int x = matrix[i0][j0];
        matrix[i0][j0]=matrix[i3][j3];
        matrix[i3][j3]=matrix[i2][j2];
        matrix[i2][j2]=matrix[i1][j1];
        matrix[i1][j1]=x;
    }
}
