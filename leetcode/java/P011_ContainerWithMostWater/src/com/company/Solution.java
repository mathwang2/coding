package com.company;

public class Solution {
    public static int maxArea(int[] height) {
        int[][] left = new int[2][height.length];
        int[][] right = new int[2][height.length];

        left[0][0] = height[0];
        left[1][0] = 0;

        int lend = 1;
        for (int i=1; i<height.length; ++i){
            if (height[i]>left[0][lend-1]){
                left[0][lend] = height[i];
                left[1][lend] = i;
                lend++;
            }
        }

        right[0][0] = height[height.length-1];
        right[1][0] = height.length-1;

        int rend = 1;

        for (int i=height.length-2; i>=0; --i){
            if (height[i]>right[0][rend-1]){
                right[0][rend] = height[i];
                right[1][rend] = i;
                rend++;
            }
        }

        int rst = 0;

        int l=0, r=0;

        while (l<lend && r<rend){
            if (left[1][l]>=right[1][r]) return rst;
            int curr = Math.min(left[0][l], right[0][r])*(right[1][r]-left[1][l]);
            rst = curr>rst?curr:rst;
            if (left[0][l]>=right[0][r]) r++;
            else l++;
        }

        return rst;


    }
}
