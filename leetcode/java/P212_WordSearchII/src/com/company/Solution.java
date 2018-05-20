package com.company;

import org.w3c.dom.Node;

import java.util.*;

public class Solution {

        public static List<String> findWords(char[][] board, String[] words) {
            Vector<String> rst = new Vector<String>();
            Stack<NodeLetter> letterStack = new Stack<>();
            NodeLetter lHead = buildLetterGraph(board);
            NodeWordPart whead = buildWordTree(words);

            NodeLetter currl = lHead;
            NodeWordPart currw = whead;


            while (true){

                    if (currw.isFullWord){
                        rst.add(currw.wordPart);
                    }

                    currl.updateMatchPos();
                    int currwPos = -1;

                    while(!(currl.matchPos==-1)) {
                        currwPos = currw.findNextChar(currl.links.elementAt(currl.matchPos).letter);
                        if (currwPos>-1) break;
                        currl.updateMatchPos();
                    }

                    if (currl.matchPos>-1) { // next match found.
                        currl.used=true;
                        letterStack.push(currl);
                        currw = currw.links.elementAt(currwPos);
                        currl = currl.links.elementAt(currl.matchPos);
                        continue;
                    }else{
                        if (letterStack.isEmpty()){
                            Collections.sort(rst);
                            Vector<String> rstUnique = new Vector<>();
                            if (rst.isEmpty()) return rst;
                            rstUnique.add(rst.elementAt(0));

                            for (int i=1; i<rst.size();++i){
                                if (!(rst.elementAt(i).equals(rst.elementAt(i-1)))){
                                    rstUnique.add(rst.elementAt(i));
                                }
                            }

                            return rstUnique;
                        }

                        currl = letterStack.pop();
                        currl.used = false;
                        currw = currw.parent;
                    }

            }


        }

        public static NodeLetter buildLetterGraph(char[][] board){
            int bLen = board.length;
            int bWid = board[0].length;
            NodeLetter[][] letterGraph = new NodeLetter[bLen][bWid];
            NodeLetter head = new NodeLetter('\0', -1, -1);
            for (int x=0; x<bLen; ++x){
                for (int y=0; y<bWid; ++y){
                    letterGraph[x][y] = new NodeLetter(board[x][y], x, y);
                    head.links.add(letterGraph[x][y]);
                }
            }

            // link together;

            for (int x=0; x<bLen; ++x){
                for (int y=0; y<bWid; ++y){
                    if (x>0) {
                        letterGraph[x][y].links.add(letterGraph[x-1][y]);
                    }

                    if (y>0) {
                        letterGraph[x][y].links.add(letterGraph[x][y-1]);
                    }

                    if (x<bLen-1) {
                        letterGraph[x][y].links.add(letterGraph[x+1][y]);
                    }

                    if (y<bWid-1) {
                        letterGraph[x][y].links.add(letterGraph[x][y+1]);
                    }

                }
            }


            return head;


        }

        public static NodeWordPart buildWordTree(String[] words){
            HashMap<String, NodeWordPart> wordMap = new HashMap<>();
            for (String word:words){
                if (!wordMap.containsKey(word)){
                    wordMap.put(word, new NodeWordPart(word, true));
                }else {
                    wordMap.get(word).isFullWord = true;
                }

                for (int i=word.length()-1; i>0; --i){
                    String substr = word.substring(0, i);
                    if (!wordMap.containsKey(substr)){
                        wordMap.put(substr, new NodeWordPart(substr, false));
                    }
                }
            }

            NodeWordPart head = new NodeWordPart("", false);
            wordMap.put("", head);

            // link together


            for (String word:words){
                if (!head.links.contains(wordMap.get(word.substring(0,1)))){
                    head.links.add(wordMap.get(word.substring(0,1)));
                }

                for (int i=1; i<word.length(); ++i){
                    String wordPart = word.substring(0, i);
                    String wordPart2 = word.substring(0, i+1);

                    if (!wordMap.get(wordPart).links.contains(wordMap.get(wordPart2))){
                        wordMap.get(wordPart).links.add(wordMap.get(wordPart2));
                    }

                }
            }

            for (String word : wordMap.keySet()){
                NodeWordPart n = wordMap.get(word);
                for (int i=0; i<n.links.size(); ++i){
                    n.nextCharPos.put(n.links.elementAt(i).lastChar, i);
                }

                if (word.length()==1){
                    n.parent = head;
                }else if (word.length()>1){
                    n.parent = wordMap.get(word.substring(0, word.length()-1));
                }


            }

            return head;

        }

        public static class NodeWordPart{
            public String wordPart;
            public char lastChar;
            public boolean isFullWord;

            HashMap<Character, Integer> nextCharPos;
            Vector<NodeWordPart> links;
            NodeWordPart parent;
            public int findNextChar(char c){
                if (!nextCharPos.containsKey(c)) return -1;
                return nextCharPos.get(c);
            }

            public NodeWordPart(String wordPart, boolean isFullWord){
                this.wordPart = wordPart;
                this.isFullWord = isFullWord;
                this.lastChar = wordPart.length()>0?wordPart.charAt(wordPart.length()-1):'\0';
                nextCharPos = new HashMap<>();
                links = new Vector<>();
                parent = null;
            }
        }

        public static class NodeLetter{
            public char letter;
            public boolean used;
            public int x, y;
            public int matchPos;
            Vector<NodeLetter> links;

            public void updateMatchPos(){
                matchPos++;
                for (;matchPos<links.size(); ++matchPos){
                    NodeLetter n = links.elementAt(matchPos);
                    if (!(n.used)){
                        break;
                    }
                }


                if (matchPos==links.size()) matchPos=-1;

            }

            public NodeLetter(char letter, int x, int y){
                this.letter = letter;
                this.x=x;
                this.y=y;
                this.used = false;
                this.matchPos=-1;
                links = new Vector<NodeLetter>();
            }
        }

}
