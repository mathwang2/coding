package com.company;

import java.util.Arrays;

public class Solution {
    public static int search(int[] nums, int target) {
        if (nums.length==0) return -1;
        if (nums.length==1) return target==nums[0]?0:-1;


        int left = 0, right = nums.length-1, mid, pivot;

        if (nums[left]<nums[right]) pivot=0;
        else {

            while (left < right - 1) {
                mid = (left + right) / 2;
                if (nums[mid] > nums[left]) left = mid;
                else right = mid;
            }

            if (nums[left] < nums[right]) pivot = left;
            else pivot = right;
        }

        int p;

        if (pivot>0 && target>=nums[0]){
            p=Arrays.binarySearch(nums, 0, pivot, target);
        }else{
            p=Arrays.binarySearch(nums, pivot, nums.length, target);
        }

        if (p<0) return -1;
        return p;

    }
}
