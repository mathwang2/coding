package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int dividend = -2147483648, divisor = -2;
        int res = Solution.divide(dividend, divisor);
        System.out.printf("%d", res);
    }
}
