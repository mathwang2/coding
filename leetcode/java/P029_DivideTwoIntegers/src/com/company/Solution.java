package com.company;

public class Solution {
    public static int divide(int dividend, int divisor) {
        if (dividend==0) return 0;
        boolean pos = (dividend>0 && divisor>0) || (dividend<0 && divisor<0);

        if (dividend==Integer.MIN_VALUE && divisor == Integer.MIN_VALUE){
            return 1;
        }

        if (divisor == Integer.MIN_VALUE) return 0;

        int extra = 0;

        if (dividend==Integer.MIN_VALUE) {
            if (divisor==-1) return Integer.MAX_VALUE;
            if (divisor==1) return Integer.MIN_VALUE;
            dividend = Integer.MAX_VALUE;
            extra = 1;
        }else {
            dividend = Math.abs(dividend);

        }

        divisor = Math.abs(divisor);

        if (dividend<divisor) return 0;

        int numDigitsDd=0;
        int numDigitsDs=0;

        int dd = dividend, ds=divisor;

        while (dd>0){
            dd>>=1;
            numDigitsDd++;
        }

        while (ds>0){
            ds>>=1;
            numDigitsDs++;
        }

        if (divisor == (1<<(numDigitsDs-1)) && extra==1){
            return pos?1<<(numDigitsDd+1-numDigitsDs):-(1<<(numDigitsDd+1-numDigitsDs));
        }

        int curr = dividend>>(numDigitsDd-numDigitsDs);
        int nextPos = numDigitsDd - numDigitsDs -1;
        int currRst = 0;
        int rst1, remainder, nextDigit, mask;

        while (true){
            if (curr>=divisor) {
                rst1=1;
                remainder = curr-divisor;
            }else{
                rst1=0;
                remainder=curr;
            }
            currRst <<=1;
            currRst+=rst1;

            if (nextPos==-1) return pos?currRst:-currRst;
            mask = 1<<nextPos;
            nextDigit = (mask & dividend) ==0?0:1;
            curr = (remainder<<1)+nextDigit;
            nextPos--;
        }

    }
}
