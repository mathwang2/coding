package com.company;

import java.util.*;

public class Solution {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> rst = new LinkedList<>();
        if (nums.length==0) return rst;
        Arrays.sort(nums);
        for (int i=0; i<nums.length; ++i){
            nums[i]*=2;
        }


        int first = nums[0]-2;
        int firstPos = 0;

        while (true){
            firstPos = Arrays.binarySearch(nums, firstPos, nums.length, first+1);
            firstPos = -firstPos-1;
            if (firstPos>=nums.length-2){
                break;
            }

            first = nums[firstPos];
            if (first>0) break;

            int secondPos = firstPos+1;
            int second = nums[secondPos]-2;
            int thirdPos = nums.length;

            while (true) {
                secondPos = Arrays.binarySearch(nums, secondPos, thirdPos, second+1);
                secondPos = -secondPos-1;
                if (secondPos>=thirdPos-1){
                    break;
                }

                second = nums[secondPos];
                int third = 0-first-second;

                if (second>third) break;

                int thirdPos0=thirdPos;
                thirdPos = Arrays.binarySearch(nums, secondPos+1, thirdPos, third-1);
                thirdPos = -thirdPos-1;
                if (thirdPos>=thirdPos0) continue;
                if (thirdPos == secondPos) break;

                third = nums[thirdPos];

                if (first+second+third==0) {
                    rst.add(Arrays.asList(first/2, second/2, third/2));
                }
            }
        }

        return rst;

    }
}
