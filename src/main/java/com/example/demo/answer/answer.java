package com.example.demo.answer;

import java.util.Arrays;
import java.util.Map;

public class answer {
    public static void main(String[] args) {
        int[] nums = {11,7,2,11,15};
        int target=9;
        System.out.println(Arrays.toString(sum(nums,target)));
    }
    public static int[] sum(int[] nums,Integer target) {
        int[] result=new int[2];
        if(nums.length<2) return result;
        for(int i=0;i<nums.length-1;i++){
            for (int j=i+1;j<nums.length;j++){
                result= new int[]{i, j};
                if (nums[i]+nums[j]==target) return result;
            }
        }
        return result;
    }
}
