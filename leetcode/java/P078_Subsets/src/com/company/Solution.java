package com.company;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        int l=nums.length;
        if (l==0) return res;
        int tot = 1<<l;
        for (int i=0; i<tot; ++i){
            List<Integer> res1 = new LinkedList<>();
            for (int j=0; j<nums.length; ++j){
                if ((i & (1<<j))>0){
                    res1.add(nums[j]);
                }
            }
            res.add(res1);
        }
        return res;

    }
}
