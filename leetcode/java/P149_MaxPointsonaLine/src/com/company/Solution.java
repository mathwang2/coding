package com.company;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static int maxPoints(Point[] points) {
        if (points.length<=2) return points.length;

        PointComparator pointComparator = new PointComparator();

        Arrays.sort(points, pointComparator);
        int nonRepTot=0;
        int[] nonRepId = new int[points.length+1];
        nonRepId[0]=0;
        nonRepTot++;

        for (int i=1; i<points.length; ++i){
            if ( pointComparator.compare(points[i], points[i-1])!=0){
                nonRepId[nonRepTot]=i;
                nonRepTot++;
            }
        }

        nonRepId[nonRepTot]=points.length;
        nonRepTot++;

        if (nonRepTot==2){
            return points.length;
        }


        int k=0;
        int plength = points.length;

        Pair[] pairs = new Pair[(nonRepTot-1)*(nonRepTot-2)];

        for (int i=0; i<nonRepTot-1; ++i){
            for (int j=0; j<nonRepTot-1; ++j){
                if (i==j) continue;
                pairs[k++]=new Pair(points[nonRepId[i]], points[nonRepId[j]], i, nonRepId[i+1]-nonRepId[i], nonRepId[j+1]-nonRepId[j] );
            }
        }

        PairComparator pairComparator = new PairComparator();
        Arrays.sort(pairs, pairComparator);

        int cnt = pairs[0].rep1+pairs[0].rep2;
        int res = 0;
        for (int i=1; i<pairs.length; ++i){
            if (comparePairs(pairs[i], pairs[i-1])!=0){
                res = cnt>res?cnt:res;
                cnt = pairs[i].rep1+pairs[i].rep2;
            }else{
                cnt+=pairs[i].rep2;
            }
        }

        return cnt>res?cnt:res;
    }

    static class Pair{
        Point p1, p2;
        int dx, dy;
        int p1Id;
        int rep1, rep2;
        double direction;
        public Pair(Point p1, Point p2, int p1Id, int rep1, int rep2){
            this.p1=p1;
            this.p2=p2;
            this.p1Id = p1Id;
            this.dx = p2.x-p1.x;
            this.dy = p2.y-p1.y;
            if (this.dx<0){
                this.dx=-this.dx;
                this.dy=-this.dy;
            }
            this.direction = this.dx!=0?  Math.signum(this.dx)* Math.signum(this.dy) * (1-1/(1+Math.abs(((double) this.dy)/this.dx))) : Math.signum(this.dy);
            this.rep1=rep1;
            this.rep2=rep2;
        }
    }

    static class PairComparator implements Comparator<Pair>{
        public int compare(Pair o1, Pair o2){
            return comparePairs(o1, o2);
        }
    }

    static class PointComparator implements  Comparator<Point>{
        public int compare(Point o1, Point o2){
            return o1.x!=o2.x?o1.x-o2.x:o1.y-o2.y;
        }
    }

    static int comparePairs(Pair o1, Pair o2){

        if (o1.p1Id!=o2.p1Id) return o1.p1Id-o2.p1Id;

        int dy1Sign = o1.dy>0?1:-1;
        int dy2Sign = o2.dy>0?1:-1;
        int y1 = Math.abs(o1.dy), y2 = Math.abs(o2.dy);
        int x1 = o1.dx, x2=o2.dx;

        if (x1==0 && x2==0){
            return dy1Sign-dy2Sign;
        }

        if (x1==0){
            return dy1Sign;
        }

        if (x2==0){
            return -dy2Sign;
        }

        if (dy1Sign!=dy2Sign){
            return dy1Sign-dy2Sign;
        }



        int flipped = 1;

        int r1 = y1/x1;
        int r2 = y2/x2;
        int d1 = y1%x1;
        int d2 = y2%x2;

        while (r1==r2 && d1!=0 && d2!=0){
            y1=x1;
            y2=x2;
            x1=d1;
            x2=d2;

            r1 = y1/x1;
            r2 = y2/x2;
            d1 = y1%x1;
            d2 = y2%x2;

            flipped=-flipped;
        }

        int sign = dy1Sign*flipped;
        if (r1!=r2) return sign * (r1-r2);

        return sign*(d1-d2);
    }

    static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }
}


 

 
 