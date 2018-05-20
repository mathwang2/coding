package com.company;

public class Solution {
    public static String shortestPalindrome(String s){
        if (s.length()<=1) return s;

        int[] failureTable = createFailureTable(s);
        int j = s.length()-1;



        int i = 0;

        while (i<j) {
            if (s.charAt(i)==s.charAt(j)){
                ++i; --j; continue;
            }

            if (i==0) {--j; continue;}

            i = failureTable[i];
        }

        StringBuilder sHead = new StringBuilder();

        for (int k = s.length()-1; k> i+j; --k){
            sHead.append(s.charAt(k));
        }
        sHead.append(s);

        return sHead.toString();
    }

    public static int[] createFailureTable(String s) {
        int[] failureTable = new int[s.length()];

        // build failure table.

        failureTable[0] = 0;
        failureTable[1]=0;

        for (int i=2; i<failureTable.length; ++i){
            int pre = i-1;
            while (true){
                if (failureTable[pre]==0){
                    if (s.charAt(0)==s.charAt(i-1)){
                        failureTable[i]=1;
                    }else{
                        failureTable[i]=0;
                    }
                    break;
                }

                if (s.charAt(failureTable[pre])==s.charAt(i-1)){
                    failureTable[i]=failureTable[pre]+1;
                    break;
                }else{
                    pre = failureTable[pre];
                }
            }
        }

        return failureTable;
    }
}
