package com.company;

import java.util.List;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String[] words = {"oath","pea","eat","rain","aaakrvlfh"};

       // String[] words = {"aaakrvlfh"};
        char[][] board =
                {
                        {'o', 'a', 'a', 'n'},
                        {'e', 't', 'a', 'e'},
                        {'i', 'h', 'k', 'r'},
                        {'i', 'f', 'l', 'v'}
                };

        List<String> sols;
        sols = Solution.findWords(board, words);

        for (String sol : sols){
            System.out.printf("%s ", sol);
        }
    }
}
