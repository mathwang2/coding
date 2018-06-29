package com.company;

public class Solution {
    public static void sortColors(int[] nums) {
        int h = 0, h1=0, t=nums.length-1;
        while (h<=t){
            if (nums[h]==1){h++;}
            else if (nums[h]==2){
                while (nums[t]==2 && h<t){
                    --t;
                }

                if (h==t) return;
                nums[h]=nums[t];
                nums[t]=2;
                --t;
            }
            else {
                if (h==h1){
                    h++;
                    h1++;
                }else{
                    nums[h]=1;
                    nums[h1]=0;
                    h1++;
                }
            }
        }

    }
}
