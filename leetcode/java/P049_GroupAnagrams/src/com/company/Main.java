package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String[] strs={"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res=Solution.groupAnagrams(strs);
        for (List<String> res1:res){
            for (String str:res1){
                System.out.printf("%s ", str);
            }
            System.out.printf("\n");
        }
    }
}
