package com.company;

import java.util.Arrays;
import java.util.Stack;
import java.util.Vector;

public class Solution {
    public static int findMinStep(String board, String hand) {
        SnapShot initSnapShot = new SnapShot(board, hand);
        return runSnapShots(initSnapShot);
    }

    public static int runSnapShots(SnapShot initSnapShot){
        Stack<SnapShot> s = new Stack<>();
        SnapShot curr = initSnapShot;
        int initBallsInHand = curr.numBallsInHand();
        int minBalls = Integer.MAX_VALUE;
        while (true){
            if (curr.boardClear()) {
                int ballsUsed = initBallsInHand-curr.numBallsInHand();
                if (ballsUsed<minBalls) minBalls=ballsUsed;
            }

            SnapShot next = curr.update();

            if (next==null){
                if (s.isEmpty()){
                    break;
                }

                curr = s.pop();
                continue;
            }

            s.add(curr);
            curr = next;
        }

        return minBalls==Integer.MAX_VALUE?-1:minBalls;
    }

    public static class SnapShot{
        Vector<int[]> board;
        int[] hand;
        int curr;

        public SnapShot(String board, String hand){
            this.board = board2Vec(board);
            this.hand = hand2Array(hand);
            this.curr = -1;
        }

        public SnapShot(Vector<int[]> board, int[] hand){
            this.board = board;
            this.hand = hand;
            this.curr = -1;
        }

        boolean boardClear(){
            return board.size()==0;
        }

        public int numBallsInHand(){
            int res = 0;
            for (int h:hand){
                res+=h;
            }
            return res;
        }

        SnapShot update(){
            if (boardClear()) return null;
            this.curr++;
            if (curr == board.size()) return null;
            while(curr<board.size()){
                int color = board.elementAt(curr)[0];
                int cnt = board.elementAt(curr)[1];
                if (cnt+hand[color]<3) {
                    curr++;
                    continue;
                }
                Vector<int[]> newBoard = removeBalls(board, curr);
                int[] newHand = Arrays.copyOf(hand, hand.length);
                newHand[color]-=(3-cnt);
                SnapShot newSnapShot = new SnapShot(newBoard, newHand);
                return newSnapShot;
            }
            return null;
        }


    }

    static Vector<int[]> removeBalls(Vector<int[]> board, int curr){
        int[][] helper = new int[board.size()][];

        for (int i=0; i<helper.length; ++i){
            helper[i] = Arrays.copyOf(board.elementAt(i), 2);
        }



        helper[curr][1]=0;
        int st = curr-1, ed = curr+1;
        while (st>=0 && ed<board.size()){
            if (helper[st][0]!=helper[ed][0]) break;
            int tot = helper[st][1]+helper[ed][1];
            if (tot<3) break;

                helper[st][1]=0;
                helper[ed][1]=0;
                st--;
                ed++;



        }

        Vector<int[]> res = new Vector<>();
        if (helper.length==0) return res;



        int i=0;

        for (;i<helper.length;++i){
            if (helper[i][1]>0) break;
        }

        if (i==helper.length) return  res;

        int[] h = helper[i];
        i++;

        for (;i<helper.length;++i){
            if (helper[i][1]==0) continue;
            if (helper[i][0]!=h[0]){
                res.add(h);
                h=helper[i];
                continue;
            }
            h[1]+=helper[i][1];
        }

        res.add(h);

        return res;
    }

    static Vector<int[]> board2Vec(String board){
        Vector<int[]> res = new Vector<>();
        int prev = -1;
        int [] x = null;
        for (int i=0; i<board.length(); ++i){
            int curr = color2int(board.charAt(i));
            if (curr!=prev){
                if (x!=null) res.add(x);
                x = new int[2];
                x[0] = curr;
                x[1] = 1;
                prev = curr;
            }else{
                x[1]++;
            }
        }
        res.add(x);
        return res;
    }

    static int[] hand2Array(String hand){
        int[] res = new int[5];
        for (int i=0; i<hand.length(); ++i){
            res[color2int(hand.charAt(i))]++;
        }
        return res;
    }

    static int color2int(char color){
        switch (color){
            case 'R': return 0;
            case 'Y': return 1;
            case 'B': return 2;
            case 'G': return 3;
            case 'W': return 4;
            default: return -1;
        }
    }

}
