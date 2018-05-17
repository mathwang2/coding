package com.company;

import org.w3c.dom.Node;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

public class RegExMatcher {
    public NodeOfMatcher[][] nodeMat;
    private NodeOfMatcher nodeHead;
    public RegExMatcher(String str, String regex){

        Vector<String> regexProcessed = processRegEx(regex);

        nodeMat = new NodeOfMatcher[regexProcessed.size()][str.length()];
        nodeHead = new NodeOfMatcher();
        nodeHead.regexPos=-1;
        nodeHead.strPos=-1;

        for (int i=0; i<nodeMat.length; ++i){
            for (int j=0; j<nodeMat[i].length; ++j){
                nodeMat[i][j]=new NodeOfMatcher();
                nodeMat[i][j].regexPos=i;
                nodeMat[i][j].strPos=j;
                nodeMat[i][j].strChar = str.substring(j, j+1);
                nodeMat[i][j].regexChar=regexProcessed.get(i);
            }
        }

        setLinks();
        calcMatch();
    }

    public String[][] getMatch(){
        if (nodeHead.color==NodeOfMatcher.Color.white){
            return null;
        }

        String[][] rst = new String[nodeMat.length][2];

        NodeOfMatcher s = nodeHead;
        int i =-1;

        while(s.parent!=null){
            int pRegexPos = s.regexPos;
            int pStrPos = s.strPos;
            s=s.parent;
            if (pRegexPos==s.regexPos-1){
                ++i;
                rst[i][0]=s.regexChar;

                if (pStrPos==s.strPos-1)
                rst[i][1]=s.strChar;
                else
                    rst[i][1]="";
            }else{
                rst[i][1]+=s.strChar;
            }
        }

        return rst;
    }

    public void printMatch(String[][] match){
        if (match==null){
            System.out.printf("Doesn't match.\n");
        }else{
            for (int i=0; i<match.length; ++i){
                System.out.printf("%s=>%s\n", match[i][0], match[i][1]);
            }
        }

    }



    private void calcMatch(){
        PriorityQueue<NodeOfMatcher> q = new PriorityQueue<NodeOfMatcher>(nodeMat.length * nodeMat[0].length, new Comparator<NodeOfMatcher>() {
            @Override
            public int compare(NodeOfMatcher o1, NodeOfMatcher o2) {
                return o1.d-o2.d;
            }
        });

        NodeOfMatcher s = nodeMat[nodeMat.length-1][nodeMat[nodeMat.length-1].length-1];
        s.d=0;
        s.color = NodeOfMatcher.Color.gray;
        q.add(s);

        while (!q.isEmpty()){
            NodeOfMatcher n = q.poll();
            if (n.links != null){
                for (NodeOfMatcher next:n.links){
                    if (next.color==NodeOfMatcher.Color.white){
                        next.color=NodeOfMatcher.Color.gray;
                        next.d=n.d+1;
                        next.parent=n;
                        q.add(next);
                    }
                }
            }

            n.color = NodeOfMatcher.Color.black;
        }
    }

    private void setLinks(){
        for (int i=0; i<nodeMat.length; ++i){
            for (int j=0; j<nodeMat[i].length; ++j){
                int next_i, next_j;
                NodeOfMatcher curr = nodeMat[i][j];
                if(curr.regexChar.length()==1){
                    if (curr.regexChar.equals(".")||curr.regexChar.equals(curr.strChar)){
                        if (i>0 && j>0) {
                            curr.links = new NodeOfMatcher[1];
                            curr.links[0]=nodeMat[i-1][j-1];
                        }

                        if (i==0 && j==0){
                            curr.links = new NodeOfMatcher[1];
                            curr.links[0] = nodeHead;
                        }
                    }
                }else{
                    if (curr.regexChar.substring(0,1).equals(".")||curr.regexChar.substring(0,1).equals(curr.strChar)){
                        if (i>0 && j>0) {
                            curr.links = new NodeOfMatcher[3];
                            curr.links[0]=nodeMat[i-1][j-1];
                            curr.links[1]=nodeMat[i-1][j];
                            curr.links[2]=nodeMat[i][j-1];
                        }else if (i>0) {
                            curr.links = new NodeOfMatcher[1];
                            curr.links[0]=nodeMat[i-1][j];
                        }else if (j>0){
                            curr.links = new NodeOfMatcher[1];
                            curr.links[0]=nodeMat[i][j-1];
                        }else{
                            curr.links = new NodeOfMatcher[1];
                            curr.links[0]=nodeHead;
                        }
                    }else{
                        if (i>0){
                            curr.links = new NodeOfMatcher[1];
                            curr.links[0]=nodeMat[i-1][j];
                        }
                    }
                }
            }
        }
    }

    private Vector<String> processRegEx(String regex){
        Vector<String> rst = new Vector<String>();
        int i=0;
        while(i<regex.length()){
            if (i+1>=regex.length()||!regex.substring(i+1, i+2).equals("*")){
                rst.add(regex.substring(i, i+1));
                i++;
                continue;
            }

            rst.add(regex.substring(i, i+2));
            i+=2;
        }
        return rst;
    }
}
