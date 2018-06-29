package com.company;

public class Solution {
    public static int removeDuplicates(int[] nums) {
        if (nums.length<=2) return nums.length;

        int write=1, read=1;
        int cnt=1;

        while (read<nums.length){
            if (nums[read]!=nums[read-1]){
                cnt=1;
            }else{
                ++cnt;
            }

            if (cnt<=2){
                nums[write++]=nums[read];
            }
            ++read;
        }

        return write;

    }
}
