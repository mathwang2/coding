package com.company;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        int[] posStack = new int[n*2];
        int sum=0;
        int endStack=0;
        int pos=n;
        posStack[endStack++]=1;
        sum++;
        pos--;

        while(endStack>0){

            if (posStack[endStack-1]==0){
                endStack--;
                if (endStack==0) continue;
                if (posStack[endStack-1]==-1){
                    posStack[endStack-1]=0;
                    sum++;
                }else if (sum<2){
                    posStack[endStack-1]=0;
                    sum--;
                    pos++;
                }else{
                    posStack[endStack-1]=-1;
                    sum-=2;
                    pos++;
                }
                continue;
            }

            if (endStack==posStack.length){
                StringBuilder sb = new StringBuilder();
                for (int i=0; i<endStack; ++i){
                    sb.append(posStack[i]==1?'(':')');
                }
                res.add(sb.toString());
                posStack[endStack-1] = 0;
                sum++;
                continue;
            }

            if (pos>0){
                posStack[endStack++]=1;
                pos--;
                sum++;
            }else{
                posStack[endStack++]=-1;
                sum--;
            }
        }

        return res;

    }
}
