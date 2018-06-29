package com.company;

public class Solution2 {



    public static int totalNQueens(int n) {
        int[] filledRow = new int[n];
        for (int i=0; i<n; ++i){
            filledRow[i]=-1;
        }
        int lastRow = -1;
        boolean[] filledCol = new boolean[n];
        boolean[] filledDiag1 = new boolean[2*n-1]; // x+y=k
        boolean[] filledDiag2 = new boolean[2*n-1]; //y-x+n-1=k

        int rst = 0;

        int curr_candidate = 0;

        while (true){
            if (curr_candidate==n){
                if (lastRow==-1){
                    return rst;
                }

                int topPoped = filledRow[lastRow];
                filledRow[lastRow]=-1;
                filledDiag1[topPoped+lastRow] = false;
                filledDiag2[topPoped-lastRow+n-1] = false;
                filledCol[topPoped]=false;
                lastRow--;
                curr_candidate = topPoped+1;
            }else if (filledCol[curr_candidate]||filledDiag1[curr_candidate+lastRow+1] || filledDiag2[curr_candidate-lastRow-1+n-1]) {
                curr_candidate++;
            }else{
                // fill one more row;
                lastRow++;
                filledRow[lastRow]=curr_candidate;
                filledDiag1[curr_candidate+lastRow] = true;
                filledDiag2[curr_candidate-lastRow+n-1] = true;
                filledCol[curr_candidate]=true;
                if (lastRow==n-1){
                    curr_candidate=n;
                    ++rst;
                }else{
                    curr_candidate = 0;
                }
            }

        }

    }
}
