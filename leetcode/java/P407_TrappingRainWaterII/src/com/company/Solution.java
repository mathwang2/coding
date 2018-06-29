package com.company;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {
    public static int trapRainWater(int[][] heightMap) {
        if (heightMap.length==0||heightMap[0].length==0){
            return 0;
        }
        NodeHeight[][] nodesMap = createHNodes(heightMap);
        PriorityQueue<NodeHeight> Q = createQueue(nodesMap);
        return totalRainWater(Q, nodesMap);
    }

    public static class NodeHeight{
        public int x, y, h, d;
        public int color;
        public boolean onBorder;
        public int z;
        public ConnectedComponent cc;



        public NodeHeight(int x, int y, int h, int xlen, int ylen){
            this.x=x;
            this.y=y;
            this.h=h;
            this.d=Integer.MAX_VALUE;
            this.color=0;
            this.z=-1;
            this.cc=null;
            if (x==0 || x==xlen-1 || y==0 || y==ylen-1) this.onBorder=true;
            else this.onBorder=false;
        }
        NodeHeight getNeighbor(NodeHeight[][] nodesMap, int xdiff, int ydiff){
            if (this.x+xdiff<nodesMap.length
                    && this.y+ydiff<nodesMap[0].length
            && this.x+xdiff>=0
                    && this.y+ydiff>=0)
            {
                return nodesMap[this.x+xdiff][this.y+ydiff];
            }else{
                return null;
            }
        }
    }

    public static class ConnectedComponent{
        public ConnectedComponent parent=null;
        public int area=0;
        public boolean isOnBorder = false;
        public ConnectedComponent getRoot(){
            ConnectedComponent root = this;
            while (root.parent!=null){
                root = root.parent;
            }
            if (this!=root) {
                this.parent = root;
            }
            return root;
        }

        public int getArea(){
            return getRoot().area;
        }

        public boolean getIsOnBorder(){
            return getRoot().isOnBorder;
        }
    }

    public static NodeHeight[][] createHNodes(int[][] heightMap){
        NodeHeight[][] rst = new NodeHeight[heightMap.length][heightMap[0].length];
        for (int i=0; i<heightMap.length; ++i){
            for (int j=0; j<heightMap[0].length; ++j){
                rst[i][j]=new NodeHeight(i, j, heightMap[i][j], heightMap.length, heightMap[0].length);
            }
        }

        return rst;

    }

    public static PriorityQueue<NodeHeight> createQueue(NodeHeight[][] nodesMap){
        PriorityQueue<NodeHeight> rst = new PriorityQueue<>(nodesMap.length * nodesMap[0].length, new Comparator<NodeHeight>() {
            @Override
            public int compare(NodeHeight o1, NodeHeight o2) {
                return o1.h-o2.h;
            }
        });

        for (NodeHeight[] n : nodesMap){
            for (NodeHeight m:n){
                rst.add(m);
            }
        }

        return rst;
    }

    public static int totalRainWater(PriorityQueue<NodeHeight> q, NodeHeight[][] nodesMap){
        NodeHeight[] nodesArray = new NodeHeight[q.size()];

        int startPos=0, endPos = 0, currArea=0, totWater=0, prevh=0, currh=0;
        while (!q.isEmpty()) {
            startPos=endPos;
            currh = q.peek().h;
            totWater+=currArea*(currh-prevh);
            while ((!q.isEmpty()) && q.peek().h == currh) {
                nodesArray[endPos] = q.poll();
                endPos++;
            }

            currArea = searchNodesGroup(currArea, nodesArray, startPos, endPos, nodesMap);
            prevh=currh;

        }

        return totWater;
    }

    public static void searchNodesCheck1Neighbor(NodeHeight curr, int xDiff, int yDiff, NodeHeight[][] nodesMap, PriorityQueue<NodeHeight> q, Set<ConnectedComponent> ccs){
        NodeHeight next = curr.getNeighbor(nodesMap, xDiff, yDiff);
        if (next==null) return;

        if (next.cc!=null){
             ccs.add(next.cc.getRoot());
        }

        if (next.h==curr.h && next.color==0){
            next.cc = curr.cc;
            next.d=curr.d+1;
            next.color=1;
            q.add(next);
        }
    }

    public static int searchNodesGroup(int currArea, NodeHeight[] nodes, int s, int e, NodeHeight[][] nodesMap){
        PriorityQueue<NodeHeight> q = new PriorityQueue<>(e - s, new Comparator<NodeHeight>() {
            @Override
            public int compare(NodeHeight o1, NodeHeight o2) {
                return o1.d-o2.d;
            }
        });

        int i=s;
        while(i<e){
            if (nodes[i].color==2){
                ++i;
                continue;
            }

            Set<ConnectedComponent> ccs = new HashSet<>();

            ConnectedComponent cc = new ConnectedComponent();
            ccs.add(cc);
            nodes[i].d=0;
            nodes[i].color=1;
            q.add(nodes[i]);

            while (!q.isEmpty()){
                NodeHeight currNode = q.poll();
                currNode.cc = cc;
                cc.area++;
                if (currNode.onBorder) {
                    cc.isOnBorder=true;
                }

                searchNodesCheck1Neighbor(currNode, -1, 0, nodesMap, q, ccs);
                searchNodesCheck1Neighbor(currNode, 1, 0, nodesMap, q, ccs);
                searchNodesCheck1Neighbor(currNode, 0, -1, nodesMap, q, ccs);
                searchNodesCheck1Neighbor(currNode, 0, 1, nodesMap, q, ccs);
                currNode.color=2;
            }

            if (!cc.isOnBorder){currArea+=cc.area;}

            if (ccs.size()>1) {
                int totInsideArea = 0;
                ConnectedComponent r = new ConnectedComponent();

                for (ConnectedComponent c : ccs) {
                    c.parent = r;
                    r.area += c.area;
                    if (c.isOnBorder) {
                        r.isOnBorder = true;
                    } else {
                        totInsideArea += c.area;
                    }
                }

                if (r.isOnBorder) {
                    currArea -= totInsideArea;
                }
            }

            ++i;

        }

        return currArea;
    }


}
