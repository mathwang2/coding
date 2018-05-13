package com.company;
import java.util.*;

public class SubStrFinder {

    public SubStrFinder(String strToSearch, String[] ArrayOfWords){
        this.strToSearch = strToSearch;
        timesOccurred = new HashMap<String, Integer>();
        maxTimesAllowed = new HashMap<String, Integer>();
        rst = new HashMap<Integer, Integer>();
        totWordsFound = 0;
        searchStartPos = 0;
        searchEndPos=0;
        wordTotal = ArrayOfWords.length;
        toSearchForward = true;
        currWordOverFlowed=null;

        wordLength = ArrayOfWords[0].length();

        for (String w:ArrayOfWords){
            if (!maxTimesAllowed.containsKey(w)){
                maxTimesAllowed.put(w, 1);
            }else{
                int curr = maxTimesAllowed.get(w);
                maxTimesAllowed.put(w, curr+1);
            }
        }

    }

    public int[] outputRst(){
        int[] arrayRst = new int[rst.size()];
        int i=0;
        for (int pos:rst.keySet()){
            arrayRst[i++]=pos;
        }
        return arrayRst;
    }

    public void searching(){
        for (int startPos=0; startPos<wordLength;++startPos){
            searching(startPos);
        }
    }

    private void searching(int startPos){
        searchStartPos = startPos;
        searchEndPos = startPos;
        reStart();

        while (searchStartPos<strToSearch.length()){
            searching1Step();
        }

    }

    private void searching1Step(){
        if (toSearchForward) searchingForward();
        else searchingBackward();
    }

    private void searchingForward(){
        if (totWordsFound==wordTotal){
            rst.put(searchStartPos, 0);
        }

        if (searchEndPos+wordLength>strToSearch.length()){
            searchStartPos = strToSearch.length();
            searchEndPos = strToSearch.length();
            return;
        }

        String nextWord = strToSearch.substring(searchEndPos, searchEndPos+wordLength);
        searchEndPos+=wordLength;

        if (!maxTimesAllowed.containsKey(nextWord)){
            searchStartPos = searchEndPos;
            reStart();
            return;
        }

        incrementTimesOccured(nextWord);
    }



    private void searchingBackward(){
        String wordToDelete = strToSearch.substring(searchStartPos, searchStartPos+wordLength);
        searchStartPos+=wordLength;
        decrementTimesOccured(wordToDelete);
    }

    private void incrementTimesOccured(String w){
       if (maxTimesAllowed.containsKey(w)){
           totWordsFound++;
           if (timesOccurred.containsKey(w)){
               timesOccurred.put(w, timesOccurred.get(w)+1);
           }else{
               timesOccurred.put(w,1);
           }
           if (timesOccurred.get(w)>maxTimesAllowed.get(w)){
               currWordOverFlowed = w;
               toSearchForward=false;
               return;
           }
       }
    }

    private void decrementTimesOccured(String w){
        assert (maxTimesAllowed.containsKey(w));
            assert (timesOccurred.containsKey(w));

            if (w.equals(currWordOverFlowed)) {
                toSearchForward = true;
                currWordOverFlowed=null;
            }

            totWordsFound--;
            timesOccurred.put(w, timesOccurred.get(w)-1);
            if (timesOccurred.get(w)==0){
                    timesOccurred.remove(w);
            }

    }

    private void reStart(){
        totWordsFound = 0;
        timesOccurred.clear();
        toSearchForward=true;
        return;
    }



    private HashMap<String, Integer> timesOccurred;
    private HashMap<String, Integer> maxTimesAllowed;
    private HashMap<Integer, Integer> rst;
    private int wordTotal;
    private int wordLength;
    private int totWordsFound;
    private int searchStartPos, searchEndPos;
    private boolean toSearchForward;
    private String currWordOverFlowed;
    private String strToSearch;


}
