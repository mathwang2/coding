package com.company;

public class Solution {
    public static int myAtoi(String str) {
        int i=0;
        while (i<str.length() && str.charAt(i)==' '){
            i++;
        }

        if (i==str.length()) return 0;

        boolean isNegative = false;

        if (str.charAt(i)=='-'){
            isNegative=true;
            i++;
        }else if (str.charAt(i)=='+'){
            i++;
        }

        int rst=0;

        while (i<str.length()){
            int digit = str.charAt(i)-'0';
            if (digit<0 || digit>9) return isNegative?-rst:rst;

            if (rst>(Integer.MAX_VALUE-digit)/10) return isNegative?Integer.MIN_VALUE:Integer.MAX_VALUE;

            rst=rst*10+digit;
            ++i;
        }

        return isNegative?-rst:rst;

    }
}
