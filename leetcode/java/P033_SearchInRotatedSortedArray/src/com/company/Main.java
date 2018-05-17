package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        int[] array=new int[]{4, 5, 6, 7, 8, 0, 1, 2, 3};
        int pos = search(5, array, 0, array.length);
        System.out.printf("pos = %d\n", pos);

    }

    public static int search(int val, int[] array, int s, int e){
        if (s==e) return -1;
        if (s+1==e){
            if (array[s]==val) return s;
            return -1;
        }
        int m = Math.max(s, Math.min(e-2, (s+e)/2));
        if (array[m]>array[s]){
            if (val>=array[s]&&val<=array[m]){
                return search(val, array, s, m+1);
            }else{
                return search(val, array, m+1, e);
            }
        }else{
            if (val>=array[m+1]&&val<=array[e-1]){
                return search(val, array, m+1, e);
            }else{
                return search(val, array, s, m+1);
            }
        }

    }
}
