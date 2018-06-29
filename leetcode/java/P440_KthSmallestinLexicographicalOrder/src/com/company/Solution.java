package com.company;

public class Solution {
    public static int findKthNumber(int n, int k) {
        if (n<=9) return k;
        String nstr = Integer.toString(n);
        int[] digits = new int[nstr.length()];
        for (int i=0; i<nstr.length(); ++i){
            digits[i]=nstr.charAt(i)-'0';
        }
        StringBuilder sb = new StringBuilder();
        findKthNumber(digits, n, 0, k+f(digits.length), sb);

        int res = Integer.parseInt(sb.toString());
        return res;

    }

    public static void findKthNumber(int[] n, int num, int start, int k, StringBuilder sb){
        int l = n.length-start;
        if (k==0) return;
        if (l==1) {
            sb.append(k-1);
            return;
        }
        int pre = f(l)*n[start];
        int post = f(l-1)*(9-n[start]);
        int tot = num % MULT10[l]+f(l);
        if (k<=pre){
            pre(l, k, 0, sb);
            return;
        }

        if (k>tot-post){
            pre(l-1, k-(tot-post), n[start]+1, sb);
            return;
        }

        sb.append(n[start]);

        findKthNumber(n, num, start+1, k-pre-1, sb);

    }

    public static void pre(int l, int k, int firstDigitAdj, StringBuilder sb){
        int a, i=0;
        while (k>0) {
            int digit;
            a = f(l);
            digit=(k - 1) / a;
            digit+=(i==0?firstDigitAdj:0);
            sb.append(digit);
            k = (k - 1) % a ;
            l--;
            i++;
        }

    }




    private static final int[] MULT10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};



    public static int f(int l){
        return (MULT10[l]-1)/9;
    }
}
