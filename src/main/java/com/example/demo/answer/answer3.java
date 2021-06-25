package com.example.demo.answer;

import java.util.HashSet;
import java.util.Set;

public class answer3 {

    public static void main(String[] args) {
         String input="abc123abc";
        System.out.println(longest(input));

    }
    /*暴力解法*/
    /*public static int longest(String s) {
        int max=0;
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                String subs=s.substring(i,j+1);
                if(repeat(subs) && subs.length()>max) max=subs.length();
            }
        }
        return max;
    }
    public static boolean repeat(String s){
        Set temp=new HashSet();
        for (int i=0;i<s.length();i++){
            if(temp.contains(s.charAt(i))) return false;
            else temp.add(s.charAt(i));
        }
        return true;
    }*/
    /*Set解法*/
    public static int longest(String s){
        int n = s.length();
        int res = 0;//记录最长子串的长度
        int end=0,start=0;//记录开始和结尾的下标
        Set<Character> set=new HashSet<>();//使用set容器不重复
        while(start<n && end<n){
            if(set.contains(s.charAt(end))){//如果窗口右侧的字符已经存在
                set.remove(s.charAt(start++));//左侧窗口边界向右
            }else{
                set.add(s.charAt(end++));//如果窗口中无重复，窗口右侧向右滑动
                res=Math.max(res,end-start);//同时记录当前最大长度
            }
        }
        return res;
    }
}
