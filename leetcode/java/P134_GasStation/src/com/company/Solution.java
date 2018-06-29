package com.company;

public class Solution {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int[] gasLeft = new int[gas.length];
        for (int i=1; i<gasLeft.length; ++i){
            gasLeft[i] = gasLeft[i-1]+gas[i-1]-cost[i-1];
        }
        gasLeft[0] = gasLeft[gasLeft.length-1]+gas[gas.length-1]-cost[cost.length-1];

        if (gasLeft[0]<0) return -1;

        int currMin = Integer.MAX_VALUE;
        int minPos = -1;

        for (int i=0; i<gasLeft.length; ++i){
            if (gasLeft[i]<currMin){
                currMin = gasLeft[i];
                minPos = i;

            }
        }

        return minPos;

    }

}
