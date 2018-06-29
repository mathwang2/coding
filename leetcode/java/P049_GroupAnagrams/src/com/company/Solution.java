package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new LinkedList<>();
        if (strs.length==0) return res;
        int[][][] wordsProcessed = new int[strs.length][][];
        for (int i=0; i<strs.length; ++i){
            wordsProcessed[i]=processStr(strs[i], i);
        }

        Arrays.sort(wordsProcessed, new Comparator<int[][]>() {
            @Override
            public int compare(int[][] o1, int[][] o2) {
                return compare2words(o1, o2);
            }
        });

        List<String> res1=null;

        for (int i=0; i<wordsProcessed.length; ++i){
            if (i==0||compare2words(wordsProcessed[i], wordsProcessed[i-1])!=0){
                if (i>0) res.add(res1);
                res1=new LinkedList<>();
            }
            res1.add(strs[wordsProcessed[i][0][0]]);
        }

        res.add(res1);
        return res;

    }

    public static int compare2words(int[][] o1, int[][] o2){
        if (o1[1].length!=o2[1].length) return o1[1].length-o2[1].length;
        for (int i=0; i<o1[1].length; ++i){
            if (o1[1][i]!=o2[1][i]) return o1[1][i]-o2[1][i];
            if (o1[2][i]!=o2[2][i]) return o1[2][i]-o2[2][i];
        }
        return 0;
    }

    public static int[][] processStr(String str, int id){
        int[][] res = new int[3][];
        res[0]=new int[1];
        res[0][0] = id;

        int[] asInt = new int[str.length()];
        if (asInt.length==0){
            res[1]=new int[0];
            res[2]=new int[0];
            return res;
        }


        for (int i=0; i<asInt.length; ++i){
            asInt[i]=str.charAt(i)-'a';
        }

        Arrays.sort(asInt);
        int uniqueCnt = 1;
        for (int i=1; i<asInt.length; ++i){
            if (asInt[i]!=asInt[i-1]){
                uniqueCnt++;
            }
        }


        res[1] = new int[uniqueCnt];
        res[2] = new int[uniqueCnt];

        int uniqueId = -1;
        for (int i=0; i<asInt.length; ++i){
            if (i==0||asInt[i]!=asInt[i-1]){
                uniqueId++;
                res[1][uniqueId]=asInt[i];
            }
            res[2][uniqueId]++;
        }

        return res;
    }
}
