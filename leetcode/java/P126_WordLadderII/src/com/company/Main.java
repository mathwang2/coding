package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String[] words = {"hit","hot","dot","dog","lot","log","cog"};
        Vector<Vector<String>> ladders = findWordLadder(words);
        for (Vector<String> ladder : ladders){
            for (String word : ladder){
                System.out.printf("%s ", word);
            }
            System.out.printf("\n");
        }
    }

    public static Vector<Vector<String>> findWordLadder(String[] words){
        // put words into Nodes;
        Node[] nodes = new Node[words.length];
        for (int i=0; i<words.length; ++i){
            nodes[i]=new Node(words[i]);
        }

        // set links to nodes;

        for (int i=0; i<nodes.length; ++i){
            for (int j=0; j<nodes.length; ++j){
                if (i==j) continue;

                if (isLinked(nodes[i].word, nodes[j].word)){
                    nodes[i].links.add(nodes[j]);
                }

            }
        }

        // start from first. set d = 0;

        nodes[0].d=0;
        nodes[0].color=Node.Color.gray;

        // priority queue.

        PriorityQueue<Node> q = new PriorityQueue<>(nodes.length, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.d-o2.d;
            }
        });

        q.add(nodes[0]);

        while(!q.isEmpty()){
            Node curr = q.poll();
            for (Node n : curr.links){
                if (n.color==Node.Color.white){
                    n.color=Node.Color.gray;
                    n.d=curr.d+1;
                    q.add(n);
                }
                curr.color=Node.Color.black;
            }
        }

        Vector<Vector<String>> ladders = collectWordLadders(nodes[nodes.length-1]);
        return ladders;

    }

    public static Vector<Vector<String>> collectWordLadders(Node endNode){
        Vector<Vector<String>> ladders = new Vector<Vector<String>>();
        if (endNode.color==Node.Color.white) return ladders;

        if (endNode.d==0){
            Vector<String> ladder = new Vector<>();
            ladder.add(endNode.word);
            ladders.add(ladder);
            return ladders;
        }

        for (Node n : endNode.links){
            if (n.d==endNode.d-1){
                Vector<Vector<String>> partLadders = collectWordLadders(n);
                for (Vector<String> partLadder : partLadders){
                    partLadder.add(endNode.word);
                    ladders.add(partLadder);
                }
            }
        }

        return ladders;
    }

    public static boolean isLinked(String w1, String w2){
        if (w1.length()!=w2.length()) return false;
        int cnt=0;
        for(int i=0; i<w1.length(); ++i){
            if (w1.charAt(i)!=w2.charAt(i)) cnt++;
            if (cnt==2) return false;
        }
        return cnt==1;
    }

}
