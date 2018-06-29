package com.company;

public class Solution {
    public static int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums.length==0) return res;
        res[0] = findLeft(nums, target);
        res[1] = findRight(nums, target);
        return res;
    }

    public static int findLeft(int[] nums, int target){
        int left = 0, right = nums.length-1, mid;
        if (target<nums[0]||target>nums[nums.length-1]) return -1;
        while (left<right-1){
            mid = (left+right)/2;
            if (nums[mid]<target) left = mid;
            else right=mid;
        }

        if (nums[left]==target) return left;
        if (nums[right]==target) return right;
        return -1;
    }

    public static int findRight(int[] nums, int target){
        int left = 0, right = nums.length-1, mid;
        if (target<nums[0]||target>nums[nums.length-1]) return -1;
        while (left<right-1){
            mid = (left+right)/2;
            if (nums[mid]>target) right = mid;
            else left=mid;
        }

        if (nums[right]==target) return right;
        if (nums[left]==target) return left;

        return -1;
    }

}
