package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int boardSize = 15;
        ChessBoard chessBoard=new ChessBoard(boardSize);
        chessBoard.fillQueen();
        chessBoard.printSolutions();
    }
}
