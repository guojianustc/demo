package com.example.demo.answer;

public class answer877 {
    public static void main(String[] args) {
        int[] input={3,2,10,4};
        System.out.println(stoneGame(input));
    }

    public static boolean stoneGame(int[] input) {
        int start=0;
        int end=input.length-1;
        int n=0,sum1=0,sum2=0;
        while (start<=end){
            if(n%2==0){
                if(input[start]>input[end]){
                    sum1+=input[start++];
                }
                else {
                    sum1+=input[end--];
                }
            }
            else {
                if(input[start]>input[end]){
                    sum2+=input[start++];
                }
                else {
                    sum2+=input[end--];
                }
            }
            n++;
        }
        return sum1>sum2;
    }
}
