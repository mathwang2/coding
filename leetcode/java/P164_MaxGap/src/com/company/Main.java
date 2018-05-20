package com.company;

import java.util.Random;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	// write your code here
        double[] nums = {0, 2, 3, 8, 9, 11, 18, 20, 21};
        Random random = new Random(0);

        int totTimes = 10;

        for (int i=0; i<totTimes; ++i) {
            permuteSeq(nums, random);
            double maxGap = findMaxGap(nums);
            System.out.printf("Max gap = %f\n", maxGap);
        }
    }

    public static void permuteSeq(double[] nums, Random random){
        for (int i=0; i<nums.length-1; ++i){
            int next_id = random.nextInt(nums.length-i-1)+i+1;
            double num = nums[i];
            nums[i]=nums[next_id];
            nums[next_id]=num;
        }
    }

    public static double findMaxGap(double[] nums){
        double max=nums[0], min=nums[0];
        for (double i:nums){
            max = Math.max(i, max);
            min = Math.min(i, min);
        }

        int n = nums.length;

        double avgGap = (max-min)/(n-1);
        double[][] buckets = new double[n-1][2];
        for (int i=0; i<buckets.length; ++i){
            buckets[i][0]=avgGap;
            buckets[i][1]=avgGap;
        }

        for (int i=0; i<nums.length; ++i){
            double curr = nums[i];
            int whichBkt = Math.min(n-2, (int)Math.floor((curr-min)/avgGap));
            double leftBoundary = min+whichBkt*avgGap;
            buckets[whichBkt][0] = Math.min(curr-leftBoundary, buckets[whichBkt][0]);
            buckets[whichBkt][1] = Math.min(leftBoundary+avgGap-curr, buckets[whichBkt][1]);
        }

        double maxGap=-1, currSectionGap = 0;

        for (int i=0; i<buckets.length; ++i){
            if (buckets[i][0]+buckets[i][1]>avgGap){ // no points here
                currSectionGap+=avgGap;
            }else{
                currSectionGap+=buckets[i][0];
                if (currSectionGap>maxGap) maxGap=currSectionGap;
                currSectionGap=buckets[i][1];
            }
        }

        if (currSectionGap>maxGap) maxGap=currSectionGap;

        return maxGap;



    }

}
