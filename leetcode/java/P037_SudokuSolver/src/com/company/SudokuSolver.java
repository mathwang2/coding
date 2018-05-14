package com.company;

import java.util.*;

public class SudokuSolver {

    enum SolverStatus{SolutionReached, Contradiction, OnGoing};

    SolverStatus solverStatus;

    private Vector<SudokuNode> nodesEmpty;
    private int[][] sudokuArray;
    private SudokuNode[][] sudokuNodes;
    private Stack<SudokuNode> nodesFilled;

    public SudokuSolver(int[][] initSudokuArray){
        sudokuArray = initSudokuArray;
        nodesEmpty = new Vector<SudokuNode>();
        nodesFilled = new Stack<SudokuNode>();
        initilizeNodes();


        for (int x=0; x<9; ++x){
            for (int y=0; y<9; ++y){
                if (SudokuNode.integerToSudokuNum(initSudokuArray[x][y])!=SudokuNum.empty)
                 sudokuNodes[x][y].initCurrentNumber(SudokuNode.integerToSudokuNum(initSudokuArray[x][y]));
            }
        }

        for (int x=0; x<9; ++x){
            for (int y=0; y<9; ++y){
                if (sudokuNodes[x][y].getCurrentNumber()==SudokuNum.empty) nodesEmpty.add(sudokuNodes[x][y]);
                else nodesFilled.push(sudokuNodes[x][y]);
            }
        }

        if (nodesEmpty.isEmpty()) solverStatus = SolverStatus.SolutionReached;
        else solverStatus = SolverStatus.OnGoing;
    }

    private SudokuNode findMinCandidateNode(){
        int currMin = 100;
        SudokuNode currMinNode = null;
        for (SudokuNode n:nodesEmpty){
            if (n.getNumCandidates()==0) return n;
            if (n.getNumCandidates()<currMin){
                currMin=n.getNumCandidates();
                currMinNode = n;
            }
        }
        return currMinNode;
    }

    private void solve1Step(){
        if (nodesEmpty.isEmpty()) {
            solverStatus = SolverStatus.SolutionReached;
            return;
        }

        SudokuNode next = findMinCandidateNode();

        if (next.getNumCandidates()==0) {
             while(true){
                 if (nodesFilled.peek().isNumFixedAtInitialization()){
                     solverStatus = SolverStatus.Contradiction;
                     return;
                 }

                 if(nodesFilled.peek().updateCurrentNumber()==SudokuNum.empty) {
                     nodesEmpty.add(nodesFilled.pop());
                 }else{
                     break;
                 }
             }
        }else{
            next.updateCurrentNumber();
            nodesEmpty.remove(next);
            nodesFilled.push(next);
        }
    }

    public int solve(){
        int totSteps = 0;
        while (solverStatus == SolverStatus.OnGoing){
            solve1Step();
            totSteps++;
        }
        return totSteps;
    }

    public int[][] outputFilled(){
        int[][] filledArray = new int[9][9];
        for (int x=0; x<9;++x){
            for (int y=0; y<9; ++y){
                filledArray[x][y]=SudokuNode.sudokuNumToInt(sudokuNodes[x][y].getCurrentNumber());
            }
        }

        return filledArray;
    }


    private void initilizeNodes(){
        sudokuNodes = new SudokuNode[9][9];
        for (int x=0; x<9; ++x){
            for (int y=0; y<9; ++y){
                sudokuNodes[x][y]=new SudokuNode(x, y);
            }
        }

        for (int x=0; x<9; ++x){
            for (int y=0; y<9; ++y){
                setNodeLinks(x,y);
            }
        }
    }

    private void setNodeLinks(int x, int y){
        int[][] linkedXY = SudokuNode.calcLinkedNodesXY(x, y);

        SudokuNode[] linkedNodes = new SudokuNode[linkedXY.length];
        for (int i=0; i<linkedNodes.length;++i){
            linkedNodes[i]=sudokuNodes[linkedXY[i][0]][linkedXY[i][1]];
        }
        sudokuNodes[x][y].setLinkedNodes(linkedNodes);
    }


}
