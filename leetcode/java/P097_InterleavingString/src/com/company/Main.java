package com.company;

import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        String s4 = "aadbbbaccc";

        Vector<Integer> rst = interleaving(s1, s2, s4);

        if (rst!=null){

            for (int i:rst){
                System.out.printf("%d", i);
            }
            System.out.printf("\n");
        }else{
            System.out.printf("Not interleaving.\n");
        }

    }

    private static Vector<Integer> interleaving(String str1, String str2, String str){
        //todo: length = 0 etc;

        String str3;
        int i1=1, i2=2;
        if (str1.length()>str2.length()){
            str3=str1;
            str1=str2;
            str2=str3;
            i1=2;
            i2=1;
        }

        int[][] mat = new int[str1.length()+1][str.length()];

        if (str2.substring(0,1).equals(str.substring(0,1))){
            mat[0][0]=i2;
        }

        for (int i=1; i<str2.length();++i){
            if (str2.substring(i, i+1).equals(str.substring(i, i+1))){
                if (mat[0][i-1]!=0) mat[0][i]=i2;
            }
        }

        for (int i=1; i<mat.length; ++i){
            for (int j=i-1; j<i+str2.length(); ++j){
                if (str1.substring(i-1,i).equals(str.substring(j,j+1))){
                    if (j==0 || mat[i-1][j-1]!=0) mat[i][j]=i1;
                }else if (j>=i && str2.substring(j-i,j-i+1).equals(str.substring(j, j+1))){
                    if (mat[i][j-1]!=0) mat[i][j]=i2;
                }
            }
        }

        if (mat[mat.length-1][mat[mat.length-1].length-1]==0) return null;

        Vector<Integer> rst=new Vector<>();

        int i=mat.length-1, j=mat[mat.length-1].length-1;
        while (i>0 || j>0){
            int w = mat[i][j];
            rst.insertElementAt(w,0);
            if (w==i1){i--; j--;}
            else {j--;}
        }

        return rst;

    }
}
