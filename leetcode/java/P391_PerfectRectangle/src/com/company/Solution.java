package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    public static boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length<=1) return true;

        Arrays.sort(rectangles, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]!=o2[1]?o1[1]-o2[1]:o1[0]!=o2[0]?o1[0]-o2[0]:o1[3]!=o2[3]?o1[3]-o2[3]:o1[2]-o2[2];
            }
        });

        PriorityQueue<int[]> filled = new PriorityQueue<>(rectangles.length, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[3]!=o2[3]?o1[3]-o2[3]:o1[0]-o2[0];
            }
        });

        int curr_low = rectangles[0][1];
        filled.add(rectangles[0]);

        int i = 1;
        while (i<rectangles.length && rectangles[i][1]==curr_low){
            if (rectangles[i][0]!=rectangles[i-1][2]) return false;
            filled.add(rectangles[i]);
            i++;
        }

        while (i<rectangles.length){
            if (rectangles[i][1]!=filled.peek()[3]||rectangles[i][0]!=filled.peek()[0]) return false;
            int[] cur = filled.poll();
            while ((!filled.isEmpty()) && cur[3]==filled.peek()[3] && cur[2]==filled.peek()[0]){
                cur[2]=filled.poll()[2];
            }
            if (rectangles[i][2]>cur[2]) return false;

            if (rectangles[i][2]==cur[2]){
                cur[3]=rectangles[i][3];
                filled.add(cur);
            }else{
                int[] cur2 = new int[4];
                cur2[0]=rectangles[i][2];
                cur2[1]=cur[1];
                cur2[2]=cur[2];
                cur2[3]=cur[3];

                cur[2]=rectangles[i][2];
                cur[3]=rectangles[i][3];
                filled.add(cur);
                filled.add(cur2);
            }

            i++;
        }

        int h = filled.peek()[3];

        while (!filled.isEmpty()){
            if (filled.poll()[3]!=h) return false;
        }

        return true;
    }
}
