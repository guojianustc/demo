package com.example.demo.answer;

import java.util.Arrays;

public class answer6 {
    /*2n-2个字符看作一个单元，*/
    public static void main(String[] args) {
        String s="PAYPALISHIRING";
        int n=3;
        System.out.println(N(s,n));
    }
    public static String N(String s,int n) {
        if(n==1) return s;
        int nrow=(n-1)*s.length()/(2*n-2)+n;
        char[][] tem=new char[n][nrow];
        String res="";
        for(int i=0;i<s.length();i++){
            int mod=i%(2*n-2);
            int aliquot=i/(2*n-2);
            if(mod>=0 && mod<=n-2){
                tem[mod][aliquot*(n-1)]=s.charAt(i);
            }
            else {
                tem[2*n-mod-2][aliquot*(n-1)+mod-n+1]=s.charAt(i);
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<nrow;j++){
                if(tem[i][j]!= '\0'){
                    res+=tem[i][j];
                }
            }
        }
        return res;
    }
}
