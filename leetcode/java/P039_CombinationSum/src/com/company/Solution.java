package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (candidates.length==0) return res;
        Arrays.sort(candidates);

        int l=target/candidates[0]+1;
        int[] stack = new int[l];
        Arrays.fill(stack, -1);
        if (candidates[0]>target) return res;
        int end = 0;
        stack[end++]=0;
        target-=candidates[stack[end-1]];

        while (true){
            if (end==0) return res;

            if (stack[end-1]==-1){
                end--;
                if (end>0){
                    target+=candidates[stack[end-1]];
                    stack[end-1]++;
                    if (stack[end-1]<candidates.length && target>=candidates[stack[end-1]]){
                        target-=candidates[stack[end-1]];
                    }else{
                        stack[end-1]=-1;
                    }
                }
                continue;
            }

            if (target==0) {
                Integer[] res1=new Integer[end];
                for (int i=0; i<end; ++i){
                    res1[i]=candidates[stack[i]];
                }
                res.add(Arrays.asList(res1));
                target+=candidates[stack[end-1]];
                stack[end-1]=-1;
                continue;
            }

            if (target>=candidates[stack[end-1]]){
                stack[end]=stack[end-1];
                target-=candidates[stack[end]];
                end++;
                continue;
            }

            target+=candidates[stack[end-1]];
            stack[end-1]++;
            if (stack[end-1]<candidates.length && target>=candidates[stack[end-1]]){
                target-=candidates[stack[end-1]];
            }else {
                stack[end - 1] = -1;
            }
        }

    }
}
