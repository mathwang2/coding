package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        if (k>n) return res;

        Integer[] stack = new Integer[k];
        int end=0;

        stack[end++]=1;

        while (true){
            if (end==0) return res;

            if (stack[end-1]==0){
                end--;
                if (end==0) continue;

                if (stack[end-1]<n-k+1+(end-1)){
                    stack[end-1]++;
                }else{
                    stack[end-1]=0;
                }
                continue;
            }

            if (end==k) {
                res.add(Arrays.asList(Arrays.copyOf(stack, stack.length)));
                if (stack[end-1]<n){
                    stack[end-1]++;
                }else{
                    stack[end-1]=0;
                }
                continue;
            }

            stack[end] = stack[end-1]+1;
            end++;
        }


    }
}
