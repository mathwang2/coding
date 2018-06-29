package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[][] heightMap = {{13,16,15,18,15,15},{14,1,8,9,7,9},{19,5,4,2,5,10},{13,1,7,9,10,3},{17,7,5,10,6,1},{15,9,8,2,8,3}};

        int totWater = Solution.trapRainWater(heightMap);
        int totWater2 = Solution2.trapRainWater(heightMap);
        System.out.printf("tot = %d, tot2 = %d", totWater, totWater2);
    }
}
