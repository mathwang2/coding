package com.company;

public class Solution {
    public void setZeroes(int[][] matrix) {
        int i, j;
        boolean row0 = false;
        boolean col0 = false;
        for (j=0;j<matrix[0].length; ++j){
            if (matrix[0][j]==0) {
                row0 = true;
                break;
            }
        }

        for (i=0; i<matrix.length; ++i){
            if (matrix[i][0]==0) {
                col0 = true;
                break;
            }
        }

        for (i=1; i<matrix.length; ++i){
            for (j=1; j<matrix[0].length; ++j){
                if (matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }

        for (j=1; j<matrix[0].length; ++j){
            if (matrix[0][j]==0){
                for (i=1; i<matrix.length;++i){
                    matrix[i][j]=0;
                }
            }
        }

        for (i=1; i<matrix.length; ++i){
            if (matrix[i][0]==0){
                for (j=1; j<matrix[0].length;++j){
                    matrix[i][j]=0;
                }
            }
        }

        if (row0) {
            for (j=0; j<matrix[0].length; ++j){
                matrix[0][j]=0;
            }
        }

        if (col0) {
            for (i=0; i<matrix.length; ++i){
                matrix[i][0]=0;
            }
        }


    }
}
