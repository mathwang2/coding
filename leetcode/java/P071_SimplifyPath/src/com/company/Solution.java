package com.company;

public class Solution {
    public static String simplifyPath(String path) {
        int i=0, pl = path.length();
        boolean started = false;
        int cnt=0;
        while (i<pl){
            if (path.charAt(i)!='/' && !started){
                cnt++;
                started=true;
            }else if (path.charAt(i)=='/'){
                started=false;
            }
            i++;
        }

        String[] names = new String[cnt];

        i=0;
        int i2;
        int end=0;
        while (i<pl){
            if (path.charAt(i)!='/'){
                i2=i;
                while(i2<pl && path.charAt(i2)!='/'){
                    i2++;
                }
                if (path.charAt(i)=='.'){
                    if (i2==i+2 && path.charAt(i+1)=='.') {
                        if (end>0) end--;
                        i=i2;
                        continue;
                    }

                    if (i2==i+1){
                        i=i2;
                        continue;
                    }
                }

                names[end++]=path.substring(i, i2);
                i=i2;
            }else{
                i++;
            }
        }

        if (end==0) return "/";
        StringBuilder sb = new StringBuilder();
        for (i=0;i<end;++i){
            sb.append('/').append(names[i]);
        }

        return sb.toString();

    }
}
