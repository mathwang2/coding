package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        String str = "bbaaa";
        String regex = "b*.*a*";

        RegExMatcher m = new RegExMatcher(str, regex);
        m.printMatch(m.getMatch());
    }
}
