package com.company;

import java.util.HashMap;
import java.util.Stack;

public class Solution {
    public static int calculate(String s) {
        HashMap<Integer, Integer> pm = pMap(s);
        return calculate(s, 0, s.length(), pm);

    }

    public static HashMap<Integer, Integer> pMap(String s){
        HashMap<Integer, Integer> rst = new HashMap<>();
        Stack<Integer> pStack = new Stack<>();

        for (int i=0; i<s.length(); ++i){
            if (s.charAt(i)=='(') pStack.push(i);
            else if (s.charAt(i)==')'){
                Integer j = pStack.pop();
                rst.put(j, i);
            }
        }

        return rst;

    }



    public static int[] getHead(String s, int st, int e, HashMap<Integer, Integer> pMap){
        int i=st;
        int[] rst = new int[3];
        boolean digitFound=false;
        while (i<e && s.charAt(i)!='(' && s.charAt(i)!='+' && s.charAt(i)!='-'){
            if (s.charAt(i)!=' ') digitFound = true;
            ++i;
        }

        rst[0]=st;
        rst[1]=i;

        if (digitFound) rst[2]=0;
        else if (i==e) rst[2]=-1;
        else {
            rst[0]=i;
            rst[1]=pMap.get(i)+1;
            rst[2]=1;
        }

        return rst;
    }

    public static int evaluator(String s){
        int d = 0;
        for (int i=0; i<s.length(); ++i){
            if (s.charAt(i)>='0' && s.charAt(i)<='9'){
                d=d*10+s.charAt(i)-'0';
            }
        }
        return d;
    }

    public static int calculate(String s, int st, int e, HashMap<Integer, Integer> pMap){

        int d1 = 0;
        char operator = '+';

        int i=st;


        while (i<e){
            int se[] = getHead(s, i, e, pMap);
            int d2;
            if (se[2]==0) d2 = evaluator(s.substring(i, se[1]));
            else if (se[2]==1) d2 = calculate(s, se[0]+1, se[1]-1, pMap);
            else d2=0;
            i = se[1];
            switch (operator){
                case '+': d1+=d2; break;
                default: d1-=d2; break;
            }

            while (i<e && s.charAt(i)==' '){++i;}
            if (i<e) {
                operator = s.charAt(i);
                ++i;
            }
        }

        return d1;

    }
}
