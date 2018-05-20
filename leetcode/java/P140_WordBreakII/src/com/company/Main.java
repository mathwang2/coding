package com.company;

import java.util.HashMap;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String sentence = "catsanddog";
        String[] dict = {"cat","cats","and","sand","dog"};
        Vector<Vector<String>> sols = wordBreak(sentence, dict);

        for (Vector<String> sol : sols){
            for (String word : sol){
                System.out.printf("%s ", word);
            }
            System.out.printf("\n");
        }

    }

    public static Vector<Vector<Integer>> dynamicTable(String sentence, HashMap<String, Integer> dictionary){
        Vector<Vector<Integer>> dyTable = new Vector<>();
        Vector<Integer> first = new Vector<>();
        first.add(-1);
        dyTable.add(first);

        for(int i=0; i<sentence.length();++i){
            Vector<Integer> ptrs = new Vector<>();
            for (String word : dictionary.keySet()){
                int wl = word.length();
                if (wl<=i+1 && word.equals(sentence.substring(i+1-wl,i+1)) && !dyTable.elementAt(i+1-wl).isEmpty()){
                    ptrs.add(i+1-wl);
                }
            }
            dyTable.add(ptrs);
        }

        return dyTable;
    }

    public static Vector<Vector<String>> readDynamicTable(String sentence, Vector<Vector<Integer>> dyTable, int root){
        Vector<Vector<String>> rst = new Vector<>();
        Vector<Integer> rootPtr = dyTable.elementAt(root);
        if (rootPtr.elementAt(0)==-1){
          Vector<String> sol = new Vector<>();
          sol.add("");
          rst.add(sol);
          return rst;
        }

        for (int nextRoot : rootPtr){
            String word = sentence.substring(nextRoot, root);
            Vector<Vector<String>> nextSols = readDynamicTable(sentence, dyTable, nextRoot);
            for (Vector<String> nextSol : nextSols){
                nextSol.add(word);
                rst.add(nextSol);
            }
        }

        return rst;
    }

    public static Vector<Vector<String>> wordBreak(String sentence, String[] dictionary){
        HashMap<String, Integer> hDic = new HashMap<>();
        for (String word : dictionary){
            hDic.put(word, 0);
        }
        Vector<Vector<Integer>> dyTable = dynamicTable(sentence, hDic);
        Vector<Vector<String>> rst = readDynamicTable(sentence, dyTable, dyTable.size()-1);
        return rst;
    }

}
