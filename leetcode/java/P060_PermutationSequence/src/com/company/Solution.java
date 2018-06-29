package com.company;

public class Solution {
    public static String getPermutation(int n, int k) {
        if (n==1) return "1";
        int[] helper = new int[n];
        int i;
        k-=1;

        int [] factorial = new int[n-1];
        factorial[n-2] = 1;
        for (i=n-3; i>=0; --i){
            factorial[i] = factorial[i+1]*(n-1-i);
        }

        for (i=0; i<n-1; ++i){
            helper[i] = k/factorial[i];
            k%=factorial[i];
        }

        int[] numbers = new int[n];
        for (i=0; i<n; ++i){
            numbers[i] = i+1;
        }

        StringBuilder res = new StringBuilder();
        for (i=0; i<n;++i){
            res.append(numbers[helper[i]]);
            for (int j=helper[i]+1; j<n-i; ++j){
                numbers[j-1]=numbers[j];
            }
        }

        return res.toString();


    }
}
