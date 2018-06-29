package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital){
        int[][] pairs = new int[Capital.length][2];
        for (int i=0; i<Capital.length; ++i){
            pairs[i][0] = Capital[i];
            pairs[i][1] = Profits[i];
        }

        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        PriorityQueue<Integer> Q = new PriorityQueue<>(Capital.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        int s = 0;

        for (int i=0; i<k; ++i){

            while (s<Capital.length && W >= pairs[s][0]){
                Q.add(pairs[s][1]);
                s++;
            }

            if (Q.isEmpty()) return W;
            W+=Q.poll();

        }

        return W;
    }
}
