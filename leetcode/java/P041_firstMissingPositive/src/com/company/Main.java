package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //int[] test = new int[] {3, 4, -1, 1};

        int[] test = new int[] {1, 2, 0};
        int rst = firstMissingPositive(test);
        System.out.printf("rst=%d", rst);
    }

    public static int firstMissingPositive(int[] array){
        int i=0;
        int last_i=array.length-1;
        while (i<=last_i){
            if (array[i]==i+1) {++i; continue;}
            if (array[i]<=0||array[i]>=last_i+1) {
                int s=array[i];
                array[i]=array[last_i];
                array[last_i]=s;
                last_i--;
                continue;
            }
            int s = array[array[i]-1];
            array[array[i]-1]=array[i];
            array[i]=s;
        }

        for (int k=0; k<array.length; ++k){
            if (array[k]!=k+1) return k+1;
        }

        return array.length+1;
    }
}
