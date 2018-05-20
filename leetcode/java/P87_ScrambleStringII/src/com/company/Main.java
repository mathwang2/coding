package com.company;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class Main {

    public static int cnt =0;

    public static void main(String[] args) {
	// write your code here
        //String str="greatgreat";
        String letters="abcdefghijklmnopqrstuvwxyz";
        Random random = new Random(0);
        String str = createRandomString(10000, letters, random);
        String strScrambled = randomScrambler(str, 0.5, random);
        cnt=0;
        String scrambleSteps = solveScrambling(str, strScrambled);
        /*
        System.out.printf("%s\n", str);
        System.out.printf("%s\n", strScrambled);
        System.out.printf("%s\n", scrambleSteps);
*/
    }

    public static String createRandomString(int size, String letters, Random random){
        String str = "";
        for (int i=0; i<size; ++i){
            int pos = random.nextInt(letters.length());
            str+=letters.substring(pos, pos+1);
        }
        return str;

    }

    public static String randomScrambler(String str, double scrambleProb, Random random){
        if (str.length()<=1) return str;

        int pos = random.nextInt(str.length()-1)+1;
        String lstr = str.substring(0, pos);
        String rstr = str.substring(pos, str.length());
        String lScrambled = randomScrambler(lstr, scrambleProb, random);
        String rScrambled = randomScrambler(rstr, scrambleProb, random);
        if (random.nextDouble()<scrambleProb){
            return rScrambled+lScrambled;
        }else{
            return lScrambled+rScrambled;
        }
    }

    public static String solveScrambling(String str, String scrambledStr){
        HashMap<String, HashMap<Integer, Vector<int[]>>> decomposationTable = createDecomposationTable(str, scrambledStr);
        return readDecomposationTable(str,decomposationTable);
    }

    public static String readDecomposationTable(String str, HashMap<String, HashMap<Integer, Vector<int[]>>> decomposationTable){
        if (!decomposationTable.containsKey(str)) return null;
        if (str.length()==1){
            return "("+str+")";
        }

        HashMap<Integer, Vector<int[]>> decomposation = decomposationTable.get(str);



        int[] cut=null;

        for (int i : decomposation.keySet()){
            cut = decomposation.get(i).elementAt(0);
            break;
        }

        String lp = "(", rp = ")";

        if (cut[1]==1){
            lp="[";
            rp="]";
        }

        String lstr = str.substring(0, cut[0]);
        String rstr = str.substring(cut[0], str.length());

        String ldecomposation = readDecomposationTable(lstr, decomposationTable);
        String rdecomposation = readDecomposationTable(rstr, decomposationTable);

        //return cut[1]==0?lp+ldecomposation+rdecomposation+rp:lp+rdecomposation+ldecomposation+rp;
        return lp+ldecomposation+rdecomposation+rp;
    }

    public static HashMap<String, HashMap<Integer, Vector<int[]>>> createDecomposationTable(String str1, String str2){
        HashMap<Integer, Integer> allowableLen = new HashMap<>();
        HashMap<String, HashMap<Integer, Vector<int[]>>> decomposationTable = initDecomposationTable(str1, str2, allowableLen);

        for (int len=2; len<=str1.length(); ++len){

            for (int i=0; i<=str1.length()-len;++i){
                String currStr = str1.substring(i, i+len);
                if (!decomposationTable.containsKey(currStr)){
                    addToDecomposableTable(currStr, decomposationTable, allowableLen);
                }
            }

            if (len%10==0)
            System.out.printf("Filling decomposation table: len=%d. Size = %d. Steps = %d.\n", len, decomposationTable.size(), cnt);
        }

        return decomposationTable;
    }

    public static void addToDecomposableTable(String str1, HashMap<String, HashMap<Integer, Vector<int[]>>> decomposableTable, HashMap<Integer, Integer> allowableLen){
        if (decomposableTable.containsKey(str1)) return;
        HashMap<Integer, Vector<int[]>> posList = new HashMap<>();
        int strLen = str1.length();
        for (int i : allowableLen.keySet()){
            int rlength = strLen - i;
            if (!allowableLen.containsKey(rlength)) continue;

            String lstr = str1.substring(0, i);
            String rstr = str1.substring(i, strLen);
            int llength = i;

            if (!decomposableTable.containsKey(lstr)||!decomposableTable.containsKey(rstr)) continue;

            HashMap<Integer, Vector<int[]>> lList = decomposableTable.get(lstr);
            HashMap<Integer, Vector<int[]>> rList = decomposableTable.get(rstr);

         //   if (lList.isEmpty() || rList.isEmpty()) continue;

            if (lList.size()<=rList.size()) {
                for (int lpos : lList.keySet()) {
                    cnt++;
                    if (rList.containsKey(lpos + llength)) {
                        int rpos = lpos + llength;
                        if (!posList.containsKey(lpos)) {
                            posList.put(lpos, new Vector<int[]>());
                        }

                        int[] cut = new int[2];
                        cut[0] = llength;
                        cut[1] = 0;
                        posList.get(lpos).add(cut);

                    } else if (rList.containsKey(lpos - rlength)) {
                        int rpos = lpos - rlength;
                        if (!posList.containsKey(rpos)) {
                            posList.put(rpos, new Vector<int[]>());
                        }

                        int[] cut = new int[2];
                        cut[0] = llength;
                        cut[1] = 1;
                        posList.get(rpos).add(cut);

                    }
                }
            }else{
                for (int rpos : rList.keySet()) {
                    cnt++;
                    if (lList.containsKey(rpos - llength)) {
                        int lpos = rpos - llength;
                        if (!posList.containsKey(lpos)) {
                            posList.put(lpos, new Vector<int[]>());
                        }

                        int[] cut = new int[2];
                        cut[0] = llength;
                        cut[1] = 0;
                        posList.get(lpos).add(cut);

                    } else if (lList.containsKey(rpos + rlength)) {
                        int lpos = rpos + rlength;
                        if (!posList.containsKey(rpos)) {
                            posList.put(rpos, new Vector<int[]>());
                        }

                        int[] cut = new int[2];
                        cut[0] = llength;
                        cut[1] = 1;
                        posList.get(rpos).add(cut);

                    }
                }

            }
        }

        if (!posList.isEmpty()) {
            decomposableTable.put(str1, posList);
            if (allowableLen.containsKey(strLen)){
                allowableLen.put(strLen, allowableLen.get(strLen)+1);
            }else{
                allowableLen.put(strLen,1);
            }
        }
    }

    public static HashMap<String, HashMap<Integer, Vector<int[]>>> initDecomposationTable(String str1, String str2, HashMap<Integer, Integer> allowableLen){
        // step 1: initialize with single char. Each substr of str1 maps to a hash table,
        // whose keys are the starting point of scrambled this substr start in str2, and maps to a list of pairs of numbers,
        // denoting where the cut is positioned, and whether scramble is needed(0/1->no scramble/scrambled).

        HashMap<String, HashMap<Integer, Vector<int[]>>> rst = new HashMap<>();
        for (int i=0; i<str1.length(); ++i){
            String currStr = str1.substring(i, i+1);
            if (!rst.containsKey(currStr)){
                HashMap<Integer, Vector<int[]>> posList = new HashMap<>();
                for (int j=0; j<str2.length();++j){
                    cnt++;
                    if (str2.substring(j, j+1).equals(currStr)){
                        Vector<int[]> a = new Vector<>();
                        int[] cut = new int[2];
                        cut[0] = 0;
                        cut[1] = 0;
                        a.add(cut);
                        posList.put(j, a);

                    }
                }
                if (!posList.isEmpty()) {
                    rst.put(currStr, posList);
                    if (allowableLen.containsKey(1)){
                        allowableLen.put(1, allowableLen.get(1)+1);
                    }else{
                        allowableLen.put(1,1);
                    }
                }
            }
        }

        return rst;

    }
}
