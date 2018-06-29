package com.company;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static List<String> letterCombinations(String digits) {
        char[][] symbols = {
                {'a', 'b', 'c'}
                ,{'d', 'e', 'f'}
                ,{'g', 'h', 'i'}
                ,{'j', 'k', 'l'}
                ,{'m', 'n', 'o'}
                ,{'p', 'q', 'r', 's'}
                ,{'t', 'u', 'v'}
                ,{'w', 'x', 'y', 'z'}
        };
        List<String> res = new LinkedList<>();
        int l = digits.length();
        if (l==0) return res;
        int[] posStack = new int[l];
        int endStack = 0;

        int[] d = new int[l];

        for (int i=0; i<l; ++i){
            d[i] = digits.charAt(i)-'2';
        }

        posStack[endStack++] = 0;



        while (true){
            if (endStack==0){
                return res;
            }

            if (posStack[endStack-1]==symbols[d[endStack-1]].length){
                endStack--;
                if (endStack>0){
                    posStack[endStack-1]++;
                }
                continue;
            }

            if (endStack==l){
                    StringBuilder sb = new StringBuilder();
                    for (int i=0; i<l; ++i){
                        sb.append(symbols[d[i]][posStack[i]]);
                    }
                    res.add(sb.toString());
                    posStack[endStack-1]++;
                    continue;
            }

            posStack[endStack++]=0;
        }
    }
}
