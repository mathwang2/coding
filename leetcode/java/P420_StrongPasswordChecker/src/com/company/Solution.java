package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

public class Solution {
    public int strongPasswordChecker(String s) {
        int l = s.length();


        int miss;

        int[][] repPos = repetitionPos(s);
        int[][] cumCnt = cumCnt(s);
        int repBreaker = 0;

        if (l<6){
            miss = miss(0,l,cumCnt);
            return Math.max(6-l,miss);
        }

        if (l<=17){
            miss = miss(0,l,cumCnt);
            repBreaker = repBreakerCnt(0, l, repPos);
            return Math.max(repBreaker, miss);
        }

        int cur=Integer.MAX_VALUE;

        for (int st=0;st<l-17; ++st){
            int ed = st+17;
            miss=miss(st, ed, cumCnt);
            repBreaker=repBreakerCnt(st, ed, repPos);
            cur=Math.min(cur, Math.max(repBreaker, miss));
        }

        return cur + l-17;

    }

    int[][] cumCnt(String s){
        int[][] res = new int[s.length()+1][3];
        for (int i=0; i<s.length(); ++i){
            if (s.charAt(i)>='0' && s.charAt(i)<='9') {
                res[0][i+1]=res[0][i]+1;
            }else if (s.charAt(i)>='a' && s.charAt(i)<='z') {
                res[1][i+1]=res[1][i]+1;
            }else if (s.charAt(i)>='A' && s.charAt(i)<='Z') {
                res[2][i+1]=res[2][i]+1;
            }
        }

        return res;
    }

    int miss(int st, int ed, int[][] cumCnt){
        return (cumCnt[0][ed]>cumCnt[0][st]?0:1)+(cumCnt[1][ed]>cumCnt[1][st]?0:1)+(cumCnt[2][ed]>cumCnt[2][st]?0:1);
    }

    int repBreakerCnt(int st, int ed, int[][] repPos){
        int[] pair = new int[2];
        pair[0]=st;
        pair[1]=ed;

        int i = Arrays.binarySearch(repPos, pair, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        int j = Arrays.binarySearch(repPos, pair, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });

        int res = 0;

        if (i<0){
            i=-i-1;
            if (st<repPos[i-1][1]) res+=(repPos[i-1][1]-st)/3;
        }

        if (j<0){
            j=-j-1;
            if (ed>repPos[j][0]) res+=(ed-repPos[j][0])/3;
        }

        for (int k=i; k<j; ++k){
            res+=(repPos[k][1]-repPos[k][0])/3;
        }

        return res;

    }

    int[][] repetitionPos(String s){
        Vector<int[]> repPos = new Vector<>();
        int st=0, ed=0;
        for (int i=0; i<s.length();++i){
            if (i==s.length()-1 || s.charAt(i)!=s.charAt(i+1)){
                ed=i+1;
                if (ed-st>=3){
                    int[] cur = new int[2];
                    cur[0]=st;
                    cur[1]=ed;
                    repPos.add(cur);
                }
                st = i+1;

            }
        }

        return repPos.toArray(new int[0][]);
    }
}
