package com.company;

import java.util.List;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int boardSize = 15;
        /*
        Vector<int[]> rsts = Solution.solveNQueensWithSortedSet(boardSize);
        for (int[] rst : rsts){
            for (int i=0; i<rst.length; ++i){
                System.out.printf("%d ", rst[i]);
            }
            System.out.printf("\n");
        }


        List<List<String>> rsts = Solution.solveNQueens(boardSize);
        for (List<String> rst:rsts){
            for (String row:rst){
                System.out.printf("%s\n", row);
            }
            System.out.printf("\n");
        }*/

        System.out.printf("tot=%d\n", Solution2.totalNQueens(boardSize));

    }
}
