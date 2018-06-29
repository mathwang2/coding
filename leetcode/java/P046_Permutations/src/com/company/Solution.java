package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums.length==0) return res;
        boolean[] posFilled = new boolean[nums.length];
        int[] stack = new int[nums.length];
        Arrays.fill(stack, -1);
        int end=0;
        stack[end++]=0;
        posFilled[0]=true;

        while (true){
            if(end==0) return res;

            if (stack[end-1]==-1){
                end--;
                if (end==0) continue;

                int top = stack[end-1];
                posFilled[top++]=false;


                for (; top<posFilled.length; ++top){
                    if (!posFilled[top]) break;
                }

                if (top==posFilled.length){
                    stack[end-1] = -1;
                }else{
                    stack[end-1] = top;
                    posFilled[top]=true;
                }
                continue;
            }

            if(end==stack.length){
                Integer[] res1 = new Integer[end];

                for (int i=0; i<end; ++i){
                    res1[i]=nums[stack[i]];
                }
                res.add(Arrays.asList(res1));

                posFilled[stack[end-1]]=false;
                stack[end-1]=-1;



                continue;
            }

            int top = 0;
            while (posFilled[top]){top++;}
            posFilled[top]=true;
            stack[end++]=top;

        }



    }
}
