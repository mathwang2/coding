package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[][] intervalArray = {{1,3},{2,6},{8,10},{15,18}};

        List<Interval> intervals = new LinkedList<>();
        for (int[] in:intervalArray){
            intervals.add(new Interval(in[0], in[1]));
        }

        List<Interval> res = Solution.merge(intervals);

        for (Interval in:res){
            System.out.printf("[%d, %d] ", in.start, in.end);
        }

    }
}
