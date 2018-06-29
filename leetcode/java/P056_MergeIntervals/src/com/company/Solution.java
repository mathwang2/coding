package com.company;

import java.util.*;

public class Solution {
    public static List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new LinkedList<>();
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start!=o2.start?o1.start-o2.start:o2.end-o1.end;
            }
        });

        ListIterator<Interval> currIt = intervals.listIterator();
        if (!currIt.hasNext()) return res;

        Interval curr = currIt.next();
        int s=curr.start, e=curr.end;

        while (currIt.hasNext()){
            curr = currIt.next();
            if (curr.start>s){
                if (curr.start<=e){
                    e=e<curr.end?curr.end:e;
                }else{
                    res.add(new Interval(s,e));
                    s=curr.start;
                    e=curr.end;
                }
            }
        }

        res.add(new Interval(s, e));

        return res;
    }
}
