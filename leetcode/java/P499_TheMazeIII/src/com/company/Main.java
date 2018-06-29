package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        /*
        int[][] maze = {
                {0, 0, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0}
        };

        int[] ball = {4, 3};
        int[] hole = {3, 0};
        */

        int[][] maze = {
                {0, 0},
                {0, 0},
                {0, 0}
        };

        int[] ball = {0, 0};
        int[] hole = {2, 1};

        String path = Solution.findShortestWay(maze, ball, hole);
        System.out.printf("path=%s", path);
    }
}
