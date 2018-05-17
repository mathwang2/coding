package com.company;

import java.util.PriorityQueue;
import java.util.Vector;

public class ChessBoard {
    private int boardSize;
    ChessBoardRow[] board;
    QueenPosNote note;
    Vector<int[]> solutions;
    private int cnt;

    public ChessBoard(int boardSize){
        this.boardSize = boardSize;
        board=new ChessBoardRow[boardSize];
        for (int x=0; x<boardSize;++x){
            board[x] = new ChessBoardRow(boardSize);
        }

        note = new QueenPosNote(boardSize);
        solutions = new Vector<int[]>();
    }

    private int fillQueen1Step(int currentRow){
        board[currentRow].queenPos=note.updatePermit(currentRow, board[currentRow].queenPos);
        if (board[currentRow].queenPos==boardSize){
            board[currentRow].queenPos=-1;
            return currentRow-1;
        }else{
            return currentRow+1;
        }
    }

    private int[] save1Solution(){
        int[] rst=new int[boardSize];
        for (int i=0; i<board.length;++i){
            rst[i]=board[i].queenPos;
        }
        return rst;
    }

    public void fillQueen(){
        int currentRow=0;
        cnt = 0;
        while (true){

            /*
            for (ChessBoardRow row : board){
                System.out.printf("%d ", row.queenPos);
            }
            System.out.printf("\n");
            */

            cnt++;

            currentRow=fillQueen1Step(currentRow);
            if (currentRow==boardSize){
                currentRow--;
                solutions.add(save1Solution());
            }

            if (currentRow==-1){
               return;
            }
        }
    }

    public void printSolutions(){
        System.out.printf("%d solutions found in %d runs. \n", solutions.size(), cnt);

        if (solutions.size()<200) {
            for (int[] sol : solutions) {
                for (int pos : sol) {
                    System.out.printf("%d ", pos);
                }
                System.out.printf("\n");
            }
            System.out.printf("%d solutions printed. Found in %d runs. \n", solutions.size(), cnt);
        }
    }

}
