package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

public class Solution {
    public static String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int[][][][] wallPos = getWallPos(maze);
        Pos[][] mazePos = new Pos[maze.length][maze[0].length];
        for (int i=0; i<maze.length; ++i){
            for (int j=0; j<maze[0].length; ++j){
                if (maze[i][j]==0){
                    mazePos[i][j] = new Pos(i, j);
                }
            }
        }

        for (int i=0; i<maze.length; ++i){
            for (int j=0; j<maze[0].length; ++j){
                if (maze[i][j]==0){
                    mazePos[i][j].linkPos(mazePos, maze, wallPos, hole[0], hole[1]);
                }
            }
        }

        Pos ballPos = mazePos[ball[0]][ball[1]];
        Pos holePos = mazePos[hole[0]][hole[1]];
        findMinPath(mazePos, ballPos, holePos);
        if (holePos.p == null){
            return "impossible";
        }

        StringBuilder res = new StringBuilder();
        for (int num:holePos.directions){
            res.append(dirChar[num]);
        }

        return res.toString();
    }

    public static int compareVec(Vector<Integer> d1, Vector<Integer> d2){
        int l1 = d1.size(),l2= d2.size();
        int l = Math.min(l1, l2);
        for (int i=0; i<l; ++i){
            if (d1.elementAt(i)!=d2.elementAt(i)) return d1.elementAt(i)-d2.elementAt(i);
        }

        return l1-l2;

    }

    public static void findMinPath(Pos[][] mazePos, Pos ball, Pos hole){
        PriorityQueue<Pos> Q = new PriorityQueue<>(100, new Comparator<Pos>() {
            @Override
            public int compare(Pos o1, Pos o2) {
                if (o1.d!=o2.d || o1.d == INFINITY )
                return o1.d-o2.d;

                return compareVec(o1.directions, o2.directions);
            }
        });

        ball.d = 0;
        ball.directions = new Vector<>();
        for (int i=0; i<mazePos.length; ++i){
            for (int j=0; j<mazePos[i].length; ++j){
                if (mazePos[i][j]!=null) Q.add(mazePos[i][j]);
            }
        }

        while (!Q.isEmpty()){
            Pos curr = Q.poll();
            if (curr==hole) return;
            for (int i=0; i<curr.n.length; ++i){
                Pos n = curr.n[i];
                if (n==null||n.d<curr.d+curr.dist[i]) continue;


                if (n.d>curr.d+curr.dist[i]){
                    n.d = curr.d+curr.dist[i];
                    n.p = curr;
                    n.directions = (Vector) curr.directions.clone();
                    n.directions.add(i);
                    Q.remove(n);
                    Q.add(n);
                }else{
                    if (compareVec(curr.directions, n.directions)<0){
                        n.p = curr;
                        n.directions = (Vector) curr.directions.clone();
                        n.directions.add(i);
                        Q.remove(n);
                        Q.add(n);
                    }
                }
            }
        }
    }


    public static class Pos{
        public Pos(int i, int j){
            this.i=i;
            this.j=j;
            this.d = INFINITY;
            this.p=null;
            this.n=new Pos[4];
            this.dist=new int[4];
            this.isHole=false;
            this.directions = null;
        }

        public void linkPos(Pos[][] mazePos, int[][] maze, int[][][][] wallPos, int holeI, int holeJ){
            int leftMax = getMaxExtension(maze, wallPos, this.i, this.j, leftDir);
            int rightMax = getMaxExtension(maze, wallPos, this.i, this.j, rightDir);
            int upMax = getMaxExtension(maze, wallPos, this.i, this.j, upDir);
            int downMax = getMaxExtension(maze, wallPos, this.i, this.j, downDir);

            if (holeI==this.i && holeJ==this.j) {
                isHole=true;
                return;
            }

            if (holeI==this.i){
                if (holeJ<this.j) leftMax=this.j-holeJ<leftMax?this.j-holeJ:leftMax;
                else rightMax=-this.j+holeJ<rightMax?-this.j+holeJ:rightMax;
            }

            if (holeJ==this.j){
                if (holeI<this.i) upMax=this.i-holeI<upMax?this.i-holeI:upMax;
                else downMax=-this.i+holeI<downMax?-this.i+holeI:downMax;
            }

            if (downMax>0) {
                n[0] = mazePos[this.i+downMax][this.j];
                dist[0]=downMax;
            }else{
                n[0] = null;
            }

            if (leftMax>0) {
                n[1] = mazePos[this.i][this.j-leftMax];
                dist[1]=leftMax;
            }else{
                n[1] = null;
            }

            if (rightMax>0) {
                n[2] = mazePos[this.i][this.j+rightMax];
                dist[2]=rightMax;
            }else{
                n[2] = null;
            }

            if (upMax>0) {
                n[3] = mazePos[this.i-upMax][this.j];
                dist[3]=upMax;
            }else{
                n[3] = null;
            }

        }



        int i, j;
        int d;
        Pos p;
        Pos[] n;
        int[] dist;
        boolean isHole;
        Vector<Integer> directions;

    }



    private static final int[] upDir = {-1, 0};
    private static final int[] downDir = {1, 0};
    private static final int[] leftDir = {0, -1};
    private static final int[] rightDir = {0, 1};
    private static final char[] dirChar = {'d', 'l', 'r', 'u'};
    private static final int INFINITY = Integer.MAX_VALUE/2;

    public  static int getMaxExtension(int[][] maze, int[][][][] wallPos, int i, int j, int[] dir){
        if (maze[i][j]==1){return -1;}

        int rowOrCol = dir[0]==0?0:1;
        int startOrEnd = dir[0]==0?(dir[1]==1?0:1):(dir[0]==1?0:1);

        int posUse = rowOrCol==0?j:i;
        int posUse2 = rowOrCol==0?i:j;
        int[] wallPosComponent = wallPos[rowOrCol][startOrEnd][posUse2];
        int k = Arrays.binarySearch(wallPosComponent, posUse);
        return Math.abs(wallPosComponent[-k-1-startOrEnd]-posUse)-1;
    }

    public  static int[][][][] getWallPos(int[][] maze){
        int[][][][] res = new int[2][2][][];
        res[0][0] = new int[maze.length][]; // first 0 for a row
        res[0][1] = new int[maze.length][]; // second: 0 for start, 1 for end
        res[1][0] = new int[maze[0].length][]; // first 1 for a col
        res[1][1] = new int[maze[0].length][];
        for (int row=0; row<maze.length; ++row){
            int[][] rowRes = getWallPos(maze, row, true);
            res[0][0][row] = rowRes[0];
            res[0][1][row] = rowRes[1];
        }

        for (int col=0; col<maze[0].length; ++col){
            int[][] colRes = getWallPos(maze, col, false);
            res[1][0][col] = colRes[0];
            res[1][1][col] = colRes[1];
        }

        return res;
    }

    public static int[][] getWallPos(int[][] maze, int i, boolean workingOnRows){
        int mlength = workingOnRows?maze[0].length:maze.length;
        int whichDim = workingOnRows?1:0;

        int[] wallStarts = new int[mlength+2];
        int[] wallEnds = new int[mlength+2];
        int wallCntS=0, wallCntE = 0;

        for (int k=0; k<=mlength; ++k){

            if (k==mlength){
                if (accessMat1Dim(maze, k-1, i, whichDim)==0) {
                    wallStarts[wallCntS] = k;
                    wallCntS++;
                    wallEnds[wallCntE] = k;
                    wallCntE++;
                }
                continue;

            }

            if (accessMat1Dim(maze, k, i, whichDim)==1 && (k==0 || accessMat1Dim(maze, k-1, i, whichDim)==0)) {
                wallStarts[wallCntS]=k;
                wallCntS++;
                continue;
            }

            if (accessMat1Dim(maze, k, i, whichDim)==0 && (k==0|| accessMat1Dim(maze, k-1, i, whichDim)==1)){
                wallEnds[wallCntE]=k-1;
                wallCntE++;
                continue;
            }
        }

        int[][] res = new int[2][];

        res[0] = Arrays.copyOf(wallStarts, wallCntS);
        res[1] = Arrays.copyOf(wallEnds, wallCntE);
        return res;
    }

    public static int accessMat1Dim(int[][] mat, int pos, int otherPos, int whichDim){
        return whichDim==0?mat[pos][otherPos]:mat[otherPos][pos];
    }

}
