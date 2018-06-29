package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String s = "1-(3+5-2+(3+19-(3-1-4+(9-4-(4-(1+(3)-2)-5)+8-(3-5)-1)-4)-5)-4+3-9)-4-(3+2-5)-10";
        String s2="1 + 1";
        int rst = Solution.calculate(s2);
        System.out.printf("%s=%d\n", s2, rst);
        System.out.printf("%d",1 + 1);
    }
}
