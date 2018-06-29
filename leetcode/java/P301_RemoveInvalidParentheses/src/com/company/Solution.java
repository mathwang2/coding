package com.company;

import java.util.List;
import java.util.Vector;

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int sum = 0;
        int[] pSum = new int[s.length()+1];
        int pSumSize=1; // pSumSize is the length of pSum used.
        pSum[0]=0;
        for (int i=0; i<s.length(); ++i){
            if (s.charAt(i)=='(') {
                pSum[pSumSize] = pSum[pSumSize-1]+1;
                ++pSumSize;
            }else if (s.charAt(i)==')'){
                pSum[pSumSize]=pSum[pSumSize-1]-1;
                ++pSumSize;
            }

        }

        int[] nodeNegative = new int[pSumSize];
        int nodeNegativeSize = 0;
        int deepNeg = 0;

        for (int i=0; i<pSumSize; ++i){
            if (pSum[i]<deepNeg){
                nodeNegative[nodeNegativeSize]=i;
                nodeNegativeSize++;
                deepNeg = pSum[i];
            }
        }

        int[] RPcnt = new int[nodeNegativeSize];
        for (int i=0; i<RPcnt.length;++i){
            for (int j=(i==0?1:nodeNegative[i-1]+1); j<nodeNegative[i];++j){
                if (pSum[j]<pSum[j-1]) RPcnt[i]++;
            }
        }


        Vector<int[]> deleteRPcnt = new Vector<>();






    }
}
