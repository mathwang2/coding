package com.company;

import java.util.List;
import java.util.Vector;

public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {

    }

    public static void updateSkyline(int[] building, Vector<int[]> skyline){
        int li = building[0];
        int ri = building[1];
        int hi = building[2];

        int posl = findPos(li, skyline, 0, skyline.size());
        int posr = findPos(ri, skyline, posl, skyline.size());

        boolean lInsert = false, rInsert = false;

        if (posl==0||(skyline.elementAt(posl-1)[1]==0 && skyline.elementAt(posl-1)[0]<li)){
            lInsert = true;
        }

        if (posr==skyline.size()||(skyline.elementAt(posr-1)[1]==0 && skyline.elementAt(posl-1)[0]<ri)){
            rInsert = true;
        }



    }

    public static int findPos(int x, Vector<int[]> skyline, int s, int e){
        if (s+1==e){
            return x<skyline.elementAt(s)[0]?s:e;
        }

        int m = (s+e)/2;
        if (x<skyline.elementAt(m)[0]){
            return findPos(x, skyline, s, m);
        }else{
            return findPos(x, skyline, m, e);
        }
    }

}
