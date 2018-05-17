package com.company;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int l1=1000, l2=1000;
        int[] a1=new int[l1];
        int[] a2=new int[l2];
        int[] combine = new int[l1+l2];

        Random r = new Random();
        for (int i=0; i<a1.length; ++i){
            a1[i]=r.nextInt(a1.length*3);
            combine[i]=a1[i];
        }

        for (int i=0; i<a2.length; ++i){
            a2[i]=r.nextInt(a2.length*3);
            combine[l1+i]=a2[i];
        }

        Arrays.sort(a1);
        Arrays.sort(a2);
        Arrays.sort(combine);

        cnt=0;
        int m = findByRank(a1, 0, a1.length, a2, 0, a2.length, (a1.length+a2.length)/2);
        int m2 = combine[combine.length/2-1];

        System.out.printf("m=%d, m2=%d, cnt=%d", m, m2, cnt);



    }

    public static int cnt;



    public static int findByRank(int[] s1, int start1, int end1, int[] s2, int start2, int end2, int rank){
        cnt++;
        if (start1>=end1) return s2[start2+rank-1];
        if (start2>=end2) return s1[start1+rank-1];
        if (rank==1) return Math.min(s1[start1], s2[start2]);
        if (rank==(end1-start1+end2-start2)) return Math.max(s1[end1], s2[end2]);

        double ratio = (double) rank/(end1-start1+end2-start2);

        int r1 = Math.min(rank-1, Math.max(1, (int) Math.round(ratio*(end1-start1))));
        int r2 = rank-r1;

        if (s1[start1+r1-1]==s2[start2+r2-1]){
            return s1[start1+r1-1];
        }

        if (s1[start1+r1-1]<s2[start2+r2-1]){
            return findByRank(s1, start1+r1, end1, s2, start2, start2+r2, r2);
        }

        return findByRank(s1, start1, start1+r1, s2, start2+r2, end2, r1);

    }
}
