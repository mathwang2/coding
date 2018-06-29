package com.company;

import java.util.Arrays;

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length==0 || matrix[0].length==0) return false;
        int[] h = new int[matrix.length];
        for (int i=0; i<matrix.length; ++i){
            h[i]=matrix[i][0];
        }

        int r = Arrays.binarySearch(h, 0, h.length, target);
        if (r>=0) return true;
        r=-r-1-1;
        if (r<0) return false;
        int r2 = Arrays.binarySearch(matrix[r], 0, matrix[0].length, target);
        return r2>=0;
    }
}
