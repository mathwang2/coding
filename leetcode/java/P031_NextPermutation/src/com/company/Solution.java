package com.company;

import java.util.Arrays;

public class Solution {
    public static void nextPermutation(int[] nums) {
        if (nums.length<=1) return;
        int i;
        for (i=nums.length-1; i>0; --i){
            if (nums[i]>nums[i-1]) break;
        }

        if (i==0) {
            Arrays.sort(nums);
            return;
        }

        int j=i, k=nums.length-1, x;

        while (j<k){
            x = nums[j];
            nums[j]=nums[k];
            nums[k]=x;
            j++;
            k--;
        }



        x = nums[i-1];

        int p = Arrays.binarySearch(nums, i, nums.length, x);
        if (p<0) p=-p-1;
        else {
            while (nums[p]==nums[p+1]) p++;
            p++;
        }


        nums[i-1]=nums[p];
        nums[p]=x;



    }
}
