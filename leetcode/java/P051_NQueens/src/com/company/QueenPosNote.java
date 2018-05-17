package com.company;

public class QueenPosNote {
    private int boardSize;
    private boolean[] permittedColPos;
    private boolean[] permittedDiag1Pos;
    private boolean[] permittedDiag2Pos;

    public QueenPosNote(int boardSize){
        this.boardSize = boardSize;

        permittedColPos = new boolean[boardSize];
        permittedDiag1Pos=new boolean[2*boardSize-1];
        permittedDiag2Pos=new boolean[2*boardSize-1];

        for (int i=0; i<permittedColPos.length; ++i){
            permittedColPos[i]=true;
        }

        for (int i=0; i<permittedDiag1Pos.length; ++i){
            permittedDiag1Pos[i]=true;
        }

        for (int i=0; i<permittedDiag2Pos.length; ++i){
            permittedDiag2Pos[i]=true;
        }
    }

    private int diag1Pos(int row, int col){
        return row+col;
    }

    private int diag2Pos(int row, int col){
        return row-col+boardSize-1;
    }

    private boolean isAllowed(int row, int col){
        return permittedColPos[col]
                && permittedDiag1Pos[diag1Pos(row, col)]
                && permittedDiag2Pos[diag2Pos(row, col)];
    }

    private void revokePermit(int row, int col){
        if (col>=0 && col<boardSize) {
            permittedColPos[col] = false;
            permittedDiag1Pos[diag1Pos(row, col)] = false;
            permittedDiag2Pos[diag2Pos(row, col)] = false;
        }
    }

    private void grantPermit(int row, int col){
        if (col>=0) {
            permittedColPos[col] = true;
            permittedDiag1Pos[diag1Pos(row, col)] = true;
            permittedDiag2Pos[diag2Pos(row, col)] = true;
        }
    }

    private int findNextPermittedCol(int row, int col){
        int i;
        for (i=col+1; i<boardSize;++i){
            if (isAllowed(row, i)){
                return i;
            }
        }

        return i;
    }

    public int updatePermit(int row, int col){
        if (col==boardSize) return col;
        int nextCol = findNextPermittedCol(row, col);
        grantPermit(row, col);
        if (nextCol<boardSize) revokePermit(row, nextCol);
        return nextCol;
    }




}
