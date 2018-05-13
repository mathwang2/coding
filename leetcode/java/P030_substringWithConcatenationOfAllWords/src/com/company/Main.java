package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String strToSearch = "dkfjajOKOKOKABABOKOKdkfjOKABOKdABOKOKkf";
        String[] wordsToConcat = {"OK", "AB", "OK"};
        SubStrFinder strF = new SubStrFinder(strToSearch, wordsToConcat);
        strF.searching();
        int[] rst = strF.outputRst();

        System.out.printf("str to search: %s \n", strToSearch);
        if (rst!=null){
            for(int i=0; i<rst.length;++i){
                System.out.printf(" %d", rst[i]);
            }
        }
    }
}
