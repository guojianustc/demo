package com.example.demo.answer;

import java.util.HashSet;
import java.util.Set;

public class answer5 {

    public static void main(String[] args) {

         String input="abccbabcc";
        System.out.println(longest(input));

    }
    /*暴力解法*/
    /*public static String longest(String s){
        int n = s.length();
        String res = "";//记录最长子串的长度
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                String subs=s.substring(i,j+1);
                if(!reverse(subs).equals(subs)) continue;
                else {
                    if(subs.length()>res.length()) res=subs;
                }
            }
        }
        return res;
    }
    public static String reverse(String str)
    {
        return new StringBuffer(str).reverse().toString();
    }*/
    public static String longest(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                /*
                * len=len1时，len1为奇数,(int)start=i-(len - 1) / 2; (int)end=i+(len - 1) / 2= i+len / 2
                * len=len2时，len2为偶数,(int)start=i-(len - 2) / 2=i-(len - 1) / 2; (int)end=i+1+(len - 2) / 2= i+len / 2
                *因此
                * start = i-(len - 1) / 2;
                * end = i+len / 2
                * */
                start = i - (len - 1) / 2;
                end = i + len / 2;
//                System.out.println(i+" "+start+" "+end);
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
