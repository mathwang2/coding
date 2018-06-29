package com.company;

public class Solution {
    public static String multiply(String num1, String num2) {
        int[] digit1 = new int[num1.length()];
        int[] digit2 = new int[num2.length()];
        int l1 = num1.length();
        int l2 = num2.length();

        for (int i=l1-1; i>=0; --i){
            digit1[l1-1-i] = num1.charAt(i)-'0';
        }

        for (int i=l2-1; i>=0; --i){
            digit2[l2-1-i] = num2.charAt(i)-'0';
        }

        int[] res = new int[l1+l2];

        for (int pos=0; pos<digit2.length; ++pos){
            multThenAdd(res, digit1, digit2, pos);
        }
        int i;
        for (i=res.length-1; i>=0; --i){
            if (res[i]>0) break;
        }

        if (i<0) return "0";

        StringBuilder sb = new StringBuilder();
        for (;i>=0;--i){
            sb.append(res[i]);
        }

        return sb.toString();

    }

    public static void multThenAdd(int[] res, int[] digit1, int[] digit2, int posDigit2){
        int d = digit2[posDigit2];
        if (d==0) return;
        int toNext = 0;
        int currRes;
        int i;
        for (i=0; i<digit1.length; ++i){
            currRes = d*digit1[i]+res[i+posDigit2]+toNext;
            res[i+posDigit2]=currRes%10;
            toNext=currRes/10;
        }

        i+=posDigit2;

        for (;i<res.length;++i){
            currRes = res[i]+toNext;
            res[i]=currRes%10;
            toNext=currRes/10;
        }

    }


}
