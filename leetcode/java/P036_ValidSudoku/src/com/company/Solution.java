package com.company;

public class Solution {
    public static boolean isValidSudoku(char[][] board) {
        boolean[][] rowFilled = new boolean[9][9];
        boolean[][] colFilled = new boolean[9][9];
        boolean[][] sqFilled = new boolean[9][9];

        int sq, digit;

        for (int i=0; i<9; ++i){
            for (int j=0; j<9; ++j){
                if (board[i][j]=='.') continue;
                digit = board[i][j]-'1';

                sq = (j/3)*3+i/3;
                if (rowFilled[i][digit]) return false;
                rowFilled[i][digit] = true;

                if (colFilled[j][digit]) return false;
                colFilled[j][digit] = true;

                if (sqFilled[sq][digit]) return false;
                sqFilled[sq][digit] = true;
            }
        }

        return true;

    }




}
