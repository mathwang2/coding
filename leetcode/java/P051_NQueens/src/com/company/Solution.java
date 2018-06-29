package com.company;

import java.util.*;

public class Solution {
    public static List<List<String>> solveNQueens(int n) {
        Vector<List<String>> rst;
        rst = new Vector<>();
        Vector<int[]> rstInts = solveNQueensWithSortedSet(n);
        char[] dots = new char[n];
        for (int i=0; i<n;++i){
            dots[i]='.';
        }
        for (int[] rstInt : rstInts){
            rst.add(Arrays.asList(drawQ(rstInt, dots)));
        }
        return rst;

    }

    public static String[] drawQ(int[] pattern, char[] dots){
        String[] rst = new String[pattern.length];
        int i=0;
        for (int pos:pattern){
            dots[pos]='Q';
            StringBuilder sb = new StringBuilder();
            rst[i]=sb.append(dots).toString();
            dots[pos]='.';
            ++i;
        }
        return rst;
    }

    public static int totalNQueens(int n) {
        int[] filledRow = new int[n];
        for (int i=0; i<n; ++i){
            filledRow[i]=-1;
        }
        int lastRow = -1;
        boolean[] filledDiag1 = new boolean[2*n-1]; // x+y=k
        boolean[] filledDiag2 = new boolean[2*n-1]; //y-x+n-1=k
        TreeSet<Integer> availables = new TreeSet<>();
        for (int i=0; i<n; ++i){
            availables.add(i);
        }

        int rst = 0;

        Integer curr_candidate = 0;

        while (true){
            while (curr_candidate!=null && (filledDiag1[curr_candidate+lastRow+1] || filledDiag2[curr_candidate-lastRow-1+n-1])){
                curr_candidate = availables.higher(curr_candidate);
            }

            if (curr_candidate!=null){
                // fill one more row;
                lastRow++;
                filledRow[lastRow]=curr_candidate;
                filledDiag1[curr_candidate+lastRow] = true;
                filledDiag2[curr_candidate-lastRow+n-1] = true;
                availables.remove(curr_candidate);
                if (lastRow==n-1){
                    curr_candidate = null;
                    ++rst;
                }else{
                    curr_candidate = availables.first();
                }
            }else{
                if (lastRow==-1){
                    return rst;
                }

                int topPoped = filledRow[lastRow];
                filledRow[lastRow]=-1;
                filledDiag1[topPoped+lastRow] = false;
                filledDiag2[topPoped-lastRow+n-1] = false;
                lastRow--;
                curr_candidate = availables.higher(topPoped);
                availables.add(topPoped);

            }

        }


    }

    public static Vector<int[]> solveNQueensWithSortedSet(int n){
        int[] filledRow = new int[n];
        for (int i=0; i<n; ++i){
            filledRow[i]=-1;
        }
        int lastRow = -1;
        boolean[] filledDiag1 = new boolean[2*n-1]; // x+y=k
        boolean[] filledDiag2 = new boolean[2*n-1]; //y-x+n-1=k
        TreeSet<Integer> availables = new TreeSet<>();
        for (int i=0; i<n; ++i){
            availables.add(i);
        }

        Vector<int[]> rst = new Vector<>();

        Integer curr_candidate = 0;

        while (true){
             while (curr_candidate!=null && (filledDiag1[curr_candidate+lastRow+1] || filledDiag2[curr_candidate-lastRow-1+n-1])){
                 curr_candidate = availables.higher(curr_candidate);
             }

             if (curr_candidate!=null){
                 // fill one more row;
                 lastRow++;
                 filledRow[lastRow]=curr_candidate;
                 filledDiag1[curr_candidate+lastRow] = true;
                 filledDiag2[curr_candidate-lastRow+n-1] = true;
                 availables.remove(curr_candidate);
                 if (lastRow==n-1){
                     curr_candidate = null;
                     int[] rst1 = new int[n];
                     for (int i=0; i<n; ++i){
                         rst1[i]=filledRow[i];
                     }
                     rst.add(rst1);
                 }else{
                     curr_candidate = availables.first();
                 }
             }else{
                 if (lastRow==-1){
                     return rst;
                 }

                 int topPoped = filledRow[lastRow];
                 filledRow[lastRow]=-1;
                 filledDiag1[topPoped+lastRow] = false;
                 filledDiag2[topPoped-lastRow+n-1] = false;
                 lastRow--;
                 curr_candidate = availables.higher(topPoped);
                 availables.add(topPoped);

             }

        }

    }

}
