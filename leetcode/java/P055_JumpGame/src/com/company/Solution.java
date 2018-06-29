package com.company;

public class Solution {
    public static boolean canJump(int[] nums) {
        if (nums.length<=1) return true;
        int required=0;
        for (int i=nums.length-2; i>=0; --i){
            if (nums[i]>required) required=0;
            else required++;
        }

        return required==0;


    }

    public static boolean getHelper(int[] nums, int[] helper, int id){
        if (helper[id]==1) return true;
        if (helper[id]==-1) return false;
        if (id+nums[id]>=nums.length-1){
            helper[id]=1;
            return true;
        }

        for (int i=nums[id]; i>0; --i){
            if (getHelper(nums, helper, id+i)){
                helper[id]=1;
                return true;
            }
        }

        helper[id]=-1;
        return false;
    }


}
