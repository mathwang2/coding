package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        int i,j,m,n, l=nums.length, sum;

        i=0;


        while (i<l-3){
            if (nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target) return res;
            j=i+1;
            while (j<l-2){
                m=j+1;
                n=l-1;
                if (nums[i]+nums[j]+nums[m]+nums[m+1]<=target && nums[i]+nums[j]+nums[n]+nums[n-1]>=target) {


                    while (m < n) {
                        if (nums[i] + nums[j] + nums[m] + nums[m + 1] > target) break;
                        if (nums[i] + nums[j] + nums[n] + nums[n - 1] < target) break;

                        sum = nums[i] + nums[j] + nums[m] + nums[n];
                        if (sum == target) {
                            res.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));

                            while (m < n && nums[n] == nums[n - 1]) n--;
                            n--;

                            while (m < n && nums[m] == nums[m + 1]) m++;
                            m++;

                        } else if (sum > target) {
                            while (m < n - 1 && nums[n] == nums[n - 1]) n--;
                            n--;
                        } else {
                            while (m + 1 < n && nums[m] == nums[m + 1]) m++;
                            m++;
                        }
                    }
                }

                while (j+1<l-2 && nums[j]==nums[j+1]) j++;
                j++;
            }

            while (i+1<l-3 && nums[i]==nums[i+1]) i++;
            i++;
        }

        return res;

    }
}
