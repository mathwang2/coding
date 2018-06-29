package com.company;

public class Solution {
    public static String encode(String s) {
        char[] sChar = new char[s.length()];
        for (int i=0; i<s.length(); ++i){
            sChar[i] = s.charAt(i);
        }

        int[][] maxRep = maxRepetition(sChar);


        StringBuilder[][] dyTable = new StringBuilder[sChar.length][sChar.length];
        /*
        return dyTable[0][sChar.length-1].toString();
        */

        int[][] factors = findFactors(s.length());

        return fillDyTable1Step2(dyTable, 0, sChar.length-1, sChar, maxRep, factors).toString();
    }

    public static int[][] maxRepetition(char[] s){
        int[][] res = new int[s.length][s.length];
        for (int j=0; j<s.length; ++j){
            for (int i=j; i>=0; --i){
                if (j-i+1>i) break;
                boolean hasDiff = false;
                for (int k=j; k>=i; k--){
                    if (s[k]!=s[i-1-(j-k)]){
                        hasDiff=true;
                        break;
                    }
                }

                if (!hasDiff){
                    res[i][j] = res[i-1-(j-i)][i-1]+1;
                }
            }
        }

        return res;
    }



    public static StringBuilder fillDyTable1Step2(StringBuilder[][] dyTable, int i, int j, char[] s, int[][] maxRep, int[][] factors){
        if (dyTable[i][j]!=null) return dyTable[i][j];

        if (i>j-4) {
            StringBuilder sb = new StringBuilder();
            for (int k = i; k<=j; ++k) {
                sb.append(s[k]);
            }
            dyTable[i][j] = sb;
            return dyTable[i][j];
        }

        if (maxRep[i][j]>0){
            dyTable[i][j] = fillDyTable1Step2(dyTable, i-1-(j-i), i-1,s, maxRep, factors);
            return dyTable[i][j];
        }

        for (int k=1; k<factors[j-i+1][0];++k){
            int factor = factors[j-i+1][k];
            if ((1+maxRep[j-factor+1][j])*factor >= j-i+1){
                StringBuilder sb = new StringBuilder();
                StringBuilder sbPart = fillDyTable1Step2(dyTable, i, i+factor-1,s, maxRep, factors);
                sb.append((j-i+1)/factor).append('[').append(sbPart).append(']');
                dyTable[i][j] = sb;
                return dyTable[i][j];
            }
        }

        int minLen = Integer.MAX_VALUE;
        StringBuilder optimumLeft=null, optimumRight=null;
        StringBuilder optimumAbbr=new StringBuilder();


        for (int k = i+1; k<=j; ++k){

            StringBuilder rightPart = fillDyTable1Step2(dyTable, k, j,s, maxRep, factors);
            StringBuilder leftPart = fillDyTable1Step2(dyTable, i, k-1,s, maxRep, factors);

            if (leftPart.length()+rightPart.length()<minLen){
                minLen = leftPart.length()+rightPart.length();
                optimumLeft=leftPart;
                optimumRight=rightPart;
            }
        }

        optimumAbbr.append(optimumLeft).append(optimumRight);
        dyTable[i][j]=optimumAbbr;
        return dyTable[i][j];
    }

    private static int[][] findFactors(int x){
        int[][] res = new int[x+1][x+1];

        for (int i=1; i<=x; ++i){
            int k = 1;
            while(k*i<=x){
                res[k*i][0]++;
                res[k*i][res[k*i][0]]=i;
                k++;
            }
        }

        return res;
    }


}
