package com.company;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix==null) return res;

        int l=matrix.length;

        if (l==0) return res;

        int r=matrix[0].length;

        if (r==0) return res;

        int l1 = (l-1)/2, r1 = (r-1)/2;
        int id = Math.min(l1, r1);

        for (int i=0; i<=id; ++i) {
            spiralOrder1(matrix, i, res);
        }

        return res;


    }

    public static void spiralOrder1(int[][] matrix, int id, List<Integer> res){
        int l=matrix.length, r=matrix[0].length;
        int i;
        for (i=id; i<r-id;++i){
            res.add(matrix[id][i]);
        }

        if (id==l-1-id) return;

        for (i=id+1;i<l-id;++i){
            res.add(matrix[i][r-1-id]);
        }

        if (id==r-1-id) return;

        for (i=r-1-id-1;i>=id;--i){
            res.add(matrix[l-1-id][i]);
        }

        for (i=l-1-id-1;i>=id+1;--i){
            res.add(matrix[i][id]);
        }
    }
}
