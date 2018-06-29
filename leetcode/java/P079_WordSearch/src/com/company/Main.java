package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        char[][] board =
                {
                        {'A','B','C','E'},
                        {'S','F','C','S'},
                        {'A','D','E','E'}
                };

        String word="ABCCED";

        boolean res = Solution.exist(board, word);
        System.out.println(res);

    }
}
