package com.company;

public class ChessPos {
    public int x, y;
    int occupied;
    int numQueensInWay;
    public ChessPos(int x, int y){
        this.x=x;
        this.y=y;
        this.occupied=0;
        this.numQueensInWay=0;
    }
}
