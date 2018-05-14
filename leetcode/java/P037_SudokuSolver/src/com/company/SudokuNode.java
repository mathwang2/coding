package com.company;

enum SudokuNum{empty, one, two, three, four, five, six, seven, eight, nine};

public class SudokuNode {
    private SudokuNum currentNumber;
    private int x;
    private int y;
    private int z;
    private int numCandidates;
    private boolean numFixedAtInitialization;
    private int numNodesLinked;
    private SudokuNode[] linkedNodes;
    private int[] timesOccurredInLinkedNodes;
    private static final int maxTimesOccurranceAllowed = 3;

    public SudokuNode[] getLinkedNodes(){
        return linkedNodes;
    }

    public void setLinkedNodes(SudokuNode[] linkedNodes){
        this.linkedNodes = linkedNodes;
    }

    public SudokuNum getCurrentNumber(){
        return currentNumber;
    }

    public int getNumCandidates(){
        return numCandidates;
    }

    public SudokuNum updateCurrentNumber(){
        SudokuNum next = findNextPermit();
        removeCurrentNumber();
        setCurrentNumber(next);
        return this.currentNumber;
    }

    public boolean initCurrentNumber(SudokuNum s){
        if (s!=SudokuNum.empty){
            this.numFixedAtInitialization = true;
            return setCurrentNumber(s);
        }
        return true;
    }

    private boolean setCurrentNumber(SudokuNum s){
        if (s==SudokuNum.empty){
            this.currentNumber = s;
            return true;
        }

        this.currentNumber = s;
        return updateTimesOccurredForAllLinkedNodes();
    }

    private boolean removeCurrentNumber(){
        if (this.currentNumber==SudokuNum.empty){
            return true;
        }

        boolean success = reduceTimesOccurredForAllLinkedNodes();
        if (success) this.currentNumber=SudokuNum.empty;
        return success;
    }

    public int getNumNodesLinked(){
        return numNodesLinked;
    }

    public void setNumFixedAtInitialization(){
        numFixedAtInitialization = true;
    }

    public boolean isNumFixedAtInitialization(){
        return numFixedAtInitialization;
    }

    public SudokuNode(int x, int y){
        numNodesLinked = 21;
        this.timesOccurredInLinkedNodes = new int[SudokuNum.values().length];
        this.linkedNodes = new SudokuNode[numNodesLinked];
        this.x=x;
        this.y=y;
        this.z = calcZ(x,y);

        this.currentNumber = SudokuNum.empty;
        numFixedAtInitialization=false;
        numCandidates = SudokuNum.values().length-1;
    }

    public static int[][] calcLinkedNodesXY(int x0, int y0){
        int[][] rst=new int[20][2];

        int i=0;
        for (int x=0; x<9; ++x){
            if (x==x0) continue;
            rst[i][0] =x;
            rst[i][1] = y0;
            i++;
        }

        for (int y=0; y<9; ++y){
            if (y==y0) continue;
            rst[i][0]=x0;
            rst[i][1]=y;
            i++;
        }

        int x3 = x0/3;
        int y3 = y0/3;

        for (int x3_r = 0; x3_r<3; x3_r++){
            for (int y3_r=0; y3_r<3; y3_r++) {
                int x=x3*3+x3_r;
                int y=y3*3+y3_r;
                if (x == x0 || y == y0) continue;
                rst[i][0] = x;
                rst[i][1] = y;
                i++;
            }
        }

        return rst;
    }



    public static int calcZ(int x, int y){
        int x3, y3;
        x3 = x/3;
        y3=y/3;
        return x3*3+y3;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getZ(){
        return z;
    }

    public static boolean related(SudokuNode lhs, SudokuNode rhs){
        return lhs.x==rhs.x||lhs.y==rhs.y||lhs.z==rhs.z;
    }


    public SudokuNode getRelatedNodes(int i){
        return linkedNodes[i];
    }

    private boolean updateTimesOccurredForAllLinkedNodes(){
        if (this.currentNumber==SudokuNum.empty) return true;
        for (SudokuNode n:linkedNodes){
            if (n.currentNumber==this.currentNumber) return false;
            n.timesOccurredInLinkedNodes[sudokuNumToInt(this.currentNumber)]++;

            if (n.timesOccurredInLinkedNodes[sudokuNumToInt(this.currentNumber)]>maxTimesOccurranceAllowed)
                return false;

            if (n.timesOccurredInLinkedNodes[sudokuNumToInt(this.currentNumber)]==1)
                n.numCandidates--;
        }



        return true;
    }

    private boolean reduceTimesOccurredForAllLinkedNodes(){
        if (this.currentNumber==SudokuNum.empty) return true;
        for (SudokuNode n:linkedNodes){
            n.timesOccurredInLinkedNodes[sudokuNumToInt(this.currentNumber)]--;
            if (n.timesOccurredInLinkedNodes[sudokuNumToInt(this.currentNumber)]==0) n.numCandidates++;
            if (n.timesOccurredInLinkedNodes[sudokuNumToInt(this.currentNumber)]<0) return false;
        }

        return true;
    }


    private boolean hasPermit(SudokuNum s){
        return timesOccurredInLinkedNodes[sudokuNumToInt(s)]==0;
    }


    public SudokuNum findNextPermit(SudokuNum currentNum){
        if (numFixedAtInitialization) return SudokuNum.empty;
        SudokuNum rst = SudokuNum.empty;
        for (int i=sudokuNumToInt(currentNum)+1; i<timesOccurredInLinkedNodes.length;++i){
            if (timesOccurredInLinkedNodes[i]==0){
                rst = integerToSudokuNum(i);
                break;
            }
        }

        return rst;
    }

    public SudokuNum findNextPermit(){
        return findNextPermit(currentNumber);
    }

    public static SudokuNum integerToSudokuNum(int i){
        switch (i){
            case 1: return SudokuNum.one;
            case 2:return SudokuNum.two;
            case 3:return SudokuNum.three;
            case 4:return SudokuNum.four;
            case 5:return SudokuNum.five;
            case 6:return SudokuNum.six;
            case 7:return SudokuNum.seven;
            case 8:return SudokuNum.eight;
            case 9:return SudokuNum.nine;
            default:return SudokuNum.empty;
        }
    }

    public static int sudokuNumToInt(SudokuNum s){
        switch (s){
            case one: return 1;
            case two: return 2;
            case three: return 3;
            case four: return 4;
            case five: return 5;
            case six: return 6;
            case seven: return 7;
            case eight: return 8;
            case nine: return 9;
            default: return 0;
        }
    }



}
