package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //int[] stones={0,1,3,5,6,8,12,17};

        //int[] stones = {0,1,2,3,4,8,9,11};
       // int[] stones = {0,1,3,4,5,7,9,10,12};
        int[] stones={0,1};
        boolean rst = Solution.canCross(stones);
        System.out.printf("%s", rst?"Yes":"No");

    }
}
