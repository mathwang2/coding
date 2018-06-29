package com.company;

public class Solution {
    public static boolean exist(char[][] board, String word) {
        int numsRow = board.length, numsCol=board[0].length;
        int len = word.length();
        if (len==0) return true;
        int[][] stack = new int[len][3];

        int end=0;

        boolean[][] filled=new boolean[numsRow][numsCol];

        int firstI=0, firstJ=0;
        int iPrev, jPrev;

        while (firstI<numsRow && board[firstI][firstJ]!=word.charAt(end)){
            ++firstJ;
            if (firstJ==numsCol){
                firstJ=0;
                ++firstI;
            }
        }

        if (firstI<numsRow) {
            stack[end][0] = firstI;
            stack[end][1] = firstJ;
            stack[end][2] = -1;
            filled[firstI][firstJ] = true;
        }else{
            return false;
        }

        ++end;

        while (true){
            if (end==0){
                return false;
            }

            if (stack[end-1][2]==4){
                --end;
                filled[stack[end-1][0]][stack[end-1][1]]=false;
                if (end==1) {
                    ++firstJ;
                    if (firstJ==numsCol){
                        firstJ=0;
                        ++firstI;
                    }

                    while (firstI<numsRow && board[firstI][firstJ]!=word.charAt(end-1)){
                        ++firstJ;
                        if (firstJ==numsCol){
                            firstJ=0;
                            ++firstI;
                        }
                    }

                    if (firstI<numsRow) {
                        stack[end - 1][0] = firstI;
                        stack[end - 1][1] = firstJ;
                        stack[end - 1][2] = -1;
                        filled[firstI][firstJ] = true;
                    }else{
                        --end;
                    }
                    continue;
                }



                iPrev=stack[end-1][0]-directions[stack[end-1][2]][0];
                jPrev=stack[end-1][1]-directions[stack[end-1][2]][1];

                stack[end-1][2]++;
                while (stack[end-1][2]<4){
                    if (getChar(board, filled, iPrev, jPrev, stack[end-1][2], numsRow, numsCol)==word.charAt(end-1)) {
                        stack[end-1][0]=iPrev+directions[stack[end-1][2]][0];
                        stack[end-1][1]=jPrev+directions[stack[end-1][2]][1];
                        filled[stack[end-1][0]][stack[end-1][1]]=true;
                        break;
                    }
                    stack[end-1][2]++;
                }
                continue;
            }

            if (end==len) {
                return true;
            }


            iPrev=stack[end-1][0];
            jPrev=stack[end-1][1];
            ++end;

            stack[end-1][2]=0;

            while (stack[end-1][2]<4){
                if (getChar(board, filled, iPrev, jPrev, stack[end-1][2], numsRow, numsCol)==word.charAt(end-1)) {
                    stack[end-1][0]=iPrev+directions[stack[end-1][2]][0];
                    stack[end-1][1]=jPrev+directions[stack[end-1][2]][1];
                    filled[stack[end-1][0]][stack[end-1][1]]=true;
                    break;
                }
                stack[end-1][2]++;
            }
        }
    }

    public static final int[][] directions = {
            {0, -1}
            ,{-1, 0}
            ,{0, 1}
            ,{1, 0}
    };

    static char getChar(char[][] board, boolean[][] filled, int i, int j, int d, int numsRow, int numsCol){
        i+=directions[d][0];
        j+=directions[d][1];
        if (i>=0 && i<numsRow && j>=0 && j<numsCol){
            return filled[i][j]?'\0':board[i][j];
        }
        return '\0';
    }

}
