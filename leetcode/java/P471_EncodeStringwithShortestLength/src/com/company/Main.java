package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String s = "abcdefabcdefffffffffffffedcbafedcba";
        String abbr = Solution.encode(s);
        System.out.printf("%s=>%s", s, abbr);
    }
}
