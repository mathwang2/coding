package com.company;

import java.util.Arrays;
import java.util.Stack;
import java.util.Vector;

public class Solution {

    public static String smallestGoodBase(String n) {
        long nLong = str2Long(n);
        long res = smallestGoodBase(nLong);
        return Long.toString(res);
    }


    static long smallestGoodBase(long n){
        int kLarge = 55;
        for (int k=kLarge; k>=3; --k){
            long upper = (long) Math.floor( Math.pow((double)n, 1/((double)k-1)));
            long lower = (long) Math.ceil(Math.pow((double)n, 1/((double)k)));
            if (lower>upper) continue;

            while (lower < upper-1){
                long mid = (lower+upper)/2;
                int midTest = testBase(n, mid, k);
                if (midTest==0) return mid;
                if (midTest>0) {
                    upper = mid;
                    continue;
                }

                lower = mid;
                continue;
            }

            if (testBase(n, lower, k)==0) return lower;
            if (testBase(n, upper, k)==0) return upper;
        }

        return n-1;

    }

    static int testBase(long n, long base2test, int numDigits){
        long res=0;
        for (int i=0; i<numDigits; ++i){
            res*=base2test;
            res++;
        }

        return res>n?1:res<n?-1:0;
    }

    static long str2Long(String n){
        long res;
        res=0;
        for (int i=0; i<n.length(); ++i){
            res*=10;
            res+=(n.charAt(i)-'0');
        }
        return res;
    }

}
