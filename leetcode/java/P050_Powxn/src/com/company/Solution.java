package com.company;

public class Solution {
    public static double myPow(double x, int n) {
        if (x==0) return 0;
        if (x==1) return 1;
        if (x==-1) return n%2==0?1:-1;
        if (n==0) return 1;
        int extra=0;
        if (n==Integer.MIN_VALUE){
            n=Integer.MAX_VALUE;
            x=1/x;
            extra=1;
        }else if (n<0) {
            n=-n;
            x=1/x;
        }
        int k=n;
        int b=0;
        while (k>0){
            k>>=1;
            b++;
        }
        double[] p = new double[b];
        p[0]=x;
        for (int i=1; i<p.length; ++i){
            p[i]=p[i-1]*p[i-1];
        }

        double res=1;

        for (int i=0; i<p.length; ++i){
            if ((n & (1<<i))>0){
                res=res*p[i];
            }
        }

        if (extra==1) res*=x;

        return res;

    }
}
