package com.company;

import java.util.Arrays;

public class Solution {
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        if (nums[0]+nums[1]+nums[2]>=target) return nums[0]+nums[1]+nums[2];


        int diff = Integer.MAX_VALUE;
        int sum=0, sum2;

        int i=0, first, tnew, j, k;

        while (i<nums.length-2) {

            first = nums[i];
            tnew = target - first;
            j = i + 1;
            k = nums.length - 1;

            while (j < k) {
                sum2 = first + nums[j] + nums[j + 1];
                if (sum2 == target) {
                    return target;
                }

                if (sum2 > target) {
                    if (sum2 - target < diff) {
                        diff = sum2-target;
                        sum = sum2;
                    }
                    break;
                }

                sum2 = first + nums[k - 1] + nums[k];

                if (sum2 == target) return target;
                if (sum2 < target) {
                    if (target - sum2 < diff) {
                        diff = target - sum2;
                        sum = sum2;
                    }
                    break;
                }

                if (nums[j] + nums[k] == tnew) {
                    return target;
                }

                if (nums[j] + nums[k] < tnew) {
                    while (j<k && nums[j] + nums[k] < tnew) {
                        j++;
                    }

                    j--;

                    if (tnew - nums[j] - nums[k] < diff) {
                        sum = first + nums[j] + nums[k];
                        diff = tnew - nums[j] - nums[k];
                    }

                    j++;
                    while (j+1<k && nums[j] == nums[j + 1] ) j++;
                } else {
                    while (j<k && nums[j] + nums[k] > tnew) {
                        k--;
                    }

                    k++;

                    if (nums[j] + nums[k] - tnew < diff) {
                        sum = first + nums[j] + nums[k];
                        diff = nums[j] + nums[k] - tnew;
                    }

                    k--;
                    while (j<k-1 && nums[k] == nums[k - 1]) k--;
                }


            }

            while (i<nums.length-2 && nums[i]==nums[i+1]) i++;
            i++;
        }

        return sum;

    }
}
