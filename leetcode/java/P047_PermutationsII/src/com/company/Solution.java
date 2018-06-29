package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums.length==0) return res;

        Arrays.sort(nums);
        int totUnique=1;
        for (int i=1; i<nums.length; ++i){
            if (nums[i]!=nums[i-1]) totUnique++;
        }

        int[] maxTimesOccurred = new int[totUnique];
        int[] uniqueNums = new int[totUnique];

        int uniqueId = -1;
        for (int i=0; i<nums.length; ++i){
            if (i==0||nums[i]!=nums[i-1]){
                uniqueId++;
                uniqueNums[uniqueId]=nums[i];
            }
            maxTimesOccurred[uniqueId]++;
        }



        int[] posFilled = new int[totUnique];
        int[] stack = new int[nums.length];
        Arrays.fill(stack, -1);
        int end=0;
        stack[end++]=0;
        posFilled[0]++;

        while (true){
            if(end==0) return res;

            if (stack[end-1]==-1){
                end--;
                if (end==0) continue;

                int top = stack[end-1];
                posFilled[top]--;
                top++;


                for (; top<posFilled.length; ++top){
                    if (posFilled[top]<maxTimesOccurred[top]) break;
                }

                if (top==posFilled.length){
                    stack[end-1] = -1;
                }else{
                    stack[end-1] = top;
                    posFilled[top]++;
                }
                continue;
            }

            if(end==stack.length){
                Integer[] res1 = new Integer[end];

                for (int i=0; i<end; ++i){
                    res1[i]=uniqueNums[stack[i]];
                }
                res.add(Arrays.asList(res1));

                posFilled[stack[end-1]]--;
                stack[end-1]=-1;
                continue;
            }

            int top = 0;
            while (posFilled[top]==maxTimesOccurred[top]){top++;}
            posFilled[top]++;
            stack[end++]=top;

        }



    }
}
