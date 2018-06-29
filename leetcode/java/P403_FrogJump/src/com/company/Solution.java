package com.company;

import java.util.Arrays;

public class Solution {
    public static boolean canCross(int[] stones) {
        if (stones.length==2 && stones[1]==1) return true;
        int[] firstUnReachable = new int[stones.length];
        firstUnReachable[firstUnReachable.length-1]=firstUnReachable.length;
        for (int i=firstUnReachable.length-2;i>=0;--i){
            int s = Arrays.binarySearch(stones, i+1, firstUnReachable[i+1], stones[i]+i+1);
            if (s>0) firstUnReachable[i]=s+1;
            else firstUnReachable[i]=-s-1;
        }


        int[][] dyTable = new int[stones.length][];
        for (int k=0; k<dyTable.length; ++k){
            dyTable[k] = new int[firstUnReachable[k]-k];
            if (firstUnReachable[k]==stones.length && k<dyTable.length-1){
                int diff = stones[stones.length-1]-stones[k];


                int s;
                s = Arrays.binarySearch(stones, 0, k, stones[k]- diff);
                if (s>=0 && firstUnReachable[s]>k) {
                    dyTable[k][1+dyTable[k][0]]=stones[stones.length-1]-stones[k];
                    ++dyTable[k][0];
                    continue;
                }

                s = Arrays.binarySearch(stones, 0, k, stones[k]-diff+1);
                if (s>=0 && firstUnReachable[s]>k) {
                    dyTable[k][1+dyTable[k][0]]=stones[stones.length-1]-stones[k];
                    ++dyTable[k][0];
                    continue;
                }

                s = Arrays.binarySearch(stones, 0, k, stones[k]-diff-1);
                if (s>=0 && firstUnReachable[s]>k) {
                    dyTable[k][1+dyTable[k][0]]=stones[stones.length-1]-stones[k];
                    ++dyTable[k][0];
                    continue;
                }


            }
        }

        for (int k=dyTable.length-2;k>=0;--k){

            for (int j=1; j<=dyTable[k][0];++j){
                int s;
                s = Arrays.binarySearch(stones, 0, k, stones[k]-dyTable[k][j]);
                if (s>=0 && firstUnReachable[s]>k && (dyTable[s][0]==0 || stones[s]+dyTable[s][dyTable[s][0]]>stones[k])) {
                    dyTable[s][0]++;
                    dyTable[s][dyTable[s][0]]=dyTable[k][j];
                }

                s = Arrays.binarySearch(stones, 0, k, stones[k]-dyTable[k][j]-1);
                if (s>=0 && firstUnReachable[s]>k && (dyTable[s][0]==0 || stones[s]+dyTable[s][dyTable[s][0]]>stones[k])) {
                    dyTable[s][0]++;
                    dyTable[s][dyTable[s][0]]=dyTable[k][j]+1;
                }

                s = Arrays.binarySearch(stones, 0, k, stones[k]-dyTable[k][j]+1);
                if (s>=0 && firstUnReachable[s]>k && (dyTable[s][0]==0 || stones[s]+dyTable[s][dyTable[s][0]]>stones[k])) {
                    dyTable[s][0]++;
                    dyTable[s][dyTable[s][0]]=dyTable[k][j]-1;
                }

            }


        }

        return dyTable[0][0]>=1 && dyTable[0][1]==1;
    }
}
