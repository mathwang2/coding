package com.company;

public class Solution {
    public static int longestValidParentheses(String s) {
        int sl = s.length();
        int[][] stack = new int[sl][2];
        int endStack = 1;



        int res = 0;
        int sum=0, rsum=0;

        if (sl==0) return 0;

        for (int i=0; i<sl; ++i){
            if (s.charAt(i)=='(') ++sum;
            else {
                --sum;
                rsum++;
                if (sum==-1){
                    sum=0;
                    rsum=0;
                    res = res<stack[0][1]?stack[0][1]:res;
                    stack[0][0]=0;
                    stack[0][1]=0;
                    endStack=1;
                    continue;
                }

                if (i==sl-1 || sum==0 || s.charAt(i+1)=='('){
                    while (endStack>0 && sum<=stack[endStack-1][0]){
                        rsum+=stack[endStack-1][1];
                        endStack--;
                    }

                    stack[endStack][0] = sum;
                    stack[endStack][1] = rsum;
                    rsum=0;
                    endStack++;
                }

            }
        }

        for (int i=0; i<endStack; ++i){
            res = res<stack[i][1]?stack[i][1]:res;
        }




        return res*2;

    }
}
