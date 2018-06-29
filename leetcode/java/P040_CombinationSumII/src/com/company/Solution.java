package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (candidates.length==0) return res;
        Arrays.sort(candidates);
        if (candidates[0]>target) return res;



        int[] stack = new int[candidates.length];
        Arrays.fill(stack, -1);
        int end = 0;
        stack[end++]=0;
        target-=candidates[stack[end-1]];

        while (true) {
            if (end==0) return res;

            if (stack[end-1]==-1) {
                end--;
                if (end==0) continue;
                target+=candidates[stack[end-1]];
                int i = stack[end-1] + 1;
                while (i<candidates.length){
                    if (candidates[i]!=candidates[i-1]) break;
                    ++i;
                }
                if (i==candidates.length||target<candidates[i]){
                    stack[end-1]=-1;
                }else{
                    stack[end-1]=i;
                    target-=candidates[i];
                }
                continue;
            }

            if (target==0) {
                Integer[] res1 = new Integer[end];
                for (int i=0; i<res1.length; ++i){
                    res1[i] = candidates[stack[i]];
                }
                res.add(Arrays.asList(res1));
                target+=candidates[stack[end-1]];
                stack[end-1] = -1;
                continue;
            }

            if (end==candidates.length) return res;
            int i = stack[end-1]+1;
            if (i==candidates.length||target<candidates[i]){
                stack[end++]=-1;
            }else{
                stack[end++] = i;
                target-=candidates[i];
            }
        }





    }
}
