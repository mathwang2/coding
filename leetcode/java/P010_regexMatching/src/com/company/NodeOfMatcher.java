package com.company;

public class NodeOfMatcher {
    public static final int INFINITY=99999;
    enum Color{white, black, gray};
    int regexPos, strPos;
    String strChar;
    String regexChar;
    int d = INFINITY;
    Color color = Color.white;
    NodeOfMatcher[] links=null;
    NodeOfMatcher parent = null;
}
