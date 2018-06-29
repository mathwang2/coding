package com.company;

public class Solution {
    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int start = 1;

        int posMax = (n-1)/2;

        for (int pos=0; pos<=posMax; ++pos){
            start = fill(res, pos, start, n, n);
        }

        return res;

    }

    public static int fill(int[][] matrix, int pos, int start, int numRow, int numCol){
        for (int j=pos; j<numCol-pos;++j){
            matrix[pos][j]=start;
            start++;
        }

        if (pos==numRow-pos-1) return start;

        for (int i=pos+1; i<numRow-pos; ++i){
            matrix[i][numCol-1-pos]=start;
            start++;
        }

        if (pos==numCol-pos-1) return start;

        for (int j=numCol-pos-2; j>=pos; --j){
            matrix[numCol-1-pos][j]=start;
            start++;
        }

        for (int i=numRow-pos-2; i>=pos+1;--i){
            matrix[i][pos]=start;
            start++;
        }

        return start;
    }
}
