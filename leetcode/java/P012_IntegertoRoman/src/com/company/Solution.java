package com.company;

public class Solution {
    public static String intToRoman(int num) {
        char r1 = 'I', r5='V', r10='X', r50='L', r100='C', r500='D', r1000='M';

        char[][] symbols = {{'I', 'V'},{'X', 'L'},{'C', 'D'}, {'M', '\0'}};

        StringBuilder sb = new StringBuilder();
        int t = num/1000;
        for (int i=0; i<t; ++i){
            sb.append(symbols[3][0]);
        }

        int X = 1000;

        for (int pos = 2; pos>=0; --pos) {
            X/=10;

            num = num % (X*10);
            t = num / X;

            if (t == 9) sb.append(symbols[pos][0]).append(symbols[pos+1][0]);
            else if (t == 4) sb.append(symbols[pos][0]).append(symbols[pos][1]);
            else if (t == 5) sb.append(symbols[pos][1]);
            else if (t < 5) {
                for (int i = 0; i < t; ++i) {
                    sb.append(symbols[pos][0]);
                }
            } else {
                sb.append(symbols[pos][1]);
                for (int i = 5; i < t; ++i) {
                    sb.append(symbols[pos][0]);
                }
            }
        }

        return sb.toString();

    }
}
