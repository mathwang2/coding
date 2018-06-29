package com.company;

public class Solution {
    public static int countDigitOne(int n) {
        int d = 1;
        int tot=0;
        int i=0;
        int n0=n;
        while(n0>0){

            int k = n0/10;
            int m = n0%10;

            if (m>1){
                tot+=((k+1)*d);
            }else if (m==0){
                tot+=(k*d);
            }else{
                tot+=(k*d+1+(n%d));
            }

            n0/=10;
            d*=10;
            ++i;
        }

        return tot;

    }


}
