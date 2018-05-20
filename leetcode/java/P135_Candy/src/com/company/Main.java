package com.company;

import java.util.Random;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int size = 10;
        /* */
        double[] ranks=new double[size];
        Random r = new Random();
        for (int i=0; i<ranks.length; ++i){
            ranks[i]=r.nextDouble();
        }


       // double[] ranks={0.730968, 0.240536, 0.637417, 0.550437, 0.597545, 0.333218, 0.385189, 0.984842, 0.879183, 0.941249};

        int[] candies = rank2candy(ranks);

        for (int i=0; i<ranks.length; ++i){
            System.out.printf("%f, ", ranks[i]);
        }
        System.out.printf("\n");


        for (int i=0; i<candies.length; ++i){
            System.out.printf("%d ", candies[i]);
        }
        System.out.printf("\n");
    }

    public static int[] rank2candy(double[] ranks){
        Vector<Integer> oneCandyKids = new Vector<>();
        Vector<Integer> maxCandyKids = new Vector<>();

        for (int i=0; i<ranks.length; ++i){
            if (i==0){
                if (ranks[i+1]>ranks[i]) oneCandyKids.add(i);
            }else if (i==ranks.length-1){
                if (ranks[i-1]>ranks[i]) oneCandyKids.add(i);
            }else{
                if (ranks[i-1]>ranks[i] && ranks[i]<ranks[i+1]) oneCandyKids.add(i);
                else if (ranks[i-1]<ranks[i] && ranks[i]>ranks[i+1]) maxCandyKids.add(i);
            }
        }

        int[] candies = new int[ranks.length];
        for (int i=0; i<oneCandyKids.size();++i){
            int j;
            int pos = oneCandyKids.elementAt(i);
            candies[pos] = 1;
            j=pos-1; // go left
            while(true){
                if (j<0) break;
                if (j>0 && ranks[j-1]<ranks[j]) break;
                candies[j]=pos-j+1;
                j--;
            }

            j=pos+1;// go right
            while(true){
                if (j>=candies.length) break;
                if (j+1<candies.length && ranks[j+1]<ranks[j]) break;
                candies[j]=j-pos+1;
                j++;
            }
        }

        for (int i=0; i<maxCandyKids.size();++i){
            int pos = maxCandyKids.elementAt(i);
            candies[pos]=1+Math.max(candies[pos-1], candies[pos+1]);
        }

        return candies;
    }
}
