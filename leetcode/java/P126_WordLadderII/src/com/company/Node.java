package com.company;

import java.util.Vector;

public class Node {
    enum Color{white, gray, black};
    public static final int INFINITE = 99999;
    public String word;
    public int d;
    public Color color;
    public Vector<Node> links;

    public Node(String word){
        this.word=word;
        d=INFINITE;
        color=Color.white;
        links=new Vector<>();
    }


}
