package com.company;

public class Solution {
    public static String convert(String s, int numRows) {
        if (numRows>=s.length() || numRows==1) return s;

        StringBuilder sb = new StringBuilder();
        int pos=0;

        while (pos<s.length()){
            sb.append(s.charAt(pos));
            pos=pos+2*numRows-2;
        }

        for (int i=1; i<numRows-1; ++i){
            int remainder = 2*numRows-2-i;
            pos=i;
            while (pos<s.length()){
                sb.append(s.charAt(pos));
                if (remainder<s.length()){
                    sb.append(s.charAt(remainder));
                }
                pos=pos+2*numRows-2;
                remainder=remainder+2*numRows-2;
            }
        }

        pos = numRows-1;

        while (pos<s.length()){
            sb.append(s.charAt(pos));
            pos=pos+2*numRows-2;
        }

        return sb.toString();

    }
}
