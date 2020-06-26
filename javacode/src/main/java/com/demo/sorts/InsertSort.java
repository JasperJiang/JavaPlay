package com.demo.sorts;

import java.util.Arrays;

public class InsertSort {
    public static void InsertSort(int[] src){
        for (int i = 1; i < src.length; i++) {
            int tmp = src[i];
            int j = i;

            while (j>0 && src[j-1]>tmp){
                src[j] = src[j-1];
                j--;
            }

            src[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] src = {2,3,4,5,21,2,4,5,71,1};
        InsertSort(src);

        System.out.println(Arrays.toString(src));
    }
}
