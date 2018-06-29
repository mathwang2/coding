package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9,10,11,12}
        };

        List<Integer> res = Solution.spiralOrder(matrix);
        for (int i:res){
            System.out.printf("%d ", i);
        }
    }
}
