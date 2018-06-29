package com.company;

public class Solution {

    public static String longestPalindrome(String s) {
        String rstOdd = longestPalindrome(s, 3);
        String rstEven = longestPalindrome(s, 2);
        return rstEven.length()>rstOdd.length()?rstEven:rstOdd;
    }

    public static String longestPalindrome(String s, int l) {
        int[] pos = new int[1+s.length()];
        for (int i=0; i<pos.length-1; ++i){
            pos[i]=i;
        }

        pos[pos.length-1]=-1;

        while (l<=s.length()){
            int currPos=0, currPos2=0;

            while (pos[currPos]!=-1){
                if (pos[currPos]>0 && pos[currPos]+l-2 < s.length() && s.charAt(pos[currPos]-1)==s.charAt(pos[currPos]+l-2)){
                    pos[currPos2]=pos[currPos]-1;
                    currPos2++;
                }
                currPos++;
            }

            if (currPos2>0){
                pos[currPos2]=-1;
                l+=2;
            }else{
                return s.substring(pos[0], pos[0]+l-2);
            }
        }

        return s.substring(pos[0], pos[0]+l-2);
    }
}
