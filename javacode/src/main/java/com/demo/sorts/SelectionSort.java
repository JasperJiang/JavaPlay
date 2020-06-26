package com.demo.sorts;

import java.util.Arrays;

public class SelectionSort {
    public static void selectionSort(int[] src){
        for (int i = 0; i < src.length; i++) {
            int minTag = i;
            for (int j = i+1; j<src.length; j++){
                if (src[minTag] > src[j]){
                    minTag = j;
                }
            }

            if (minTag != i){
                int tmp = src[minTag];
                src[minTag] = src[i];
                src[i] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] src = {2,3,4,5,21,2,4,5,71,1};
        selectionSort(src);

        System.out.println(Arrays.toString(src));
    }
}
