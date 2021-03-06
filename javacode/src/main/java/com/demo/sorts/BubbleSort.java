package com.demo.sorts;

import java.util.Arrays;

public class BubbleSort {
    /**
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     *
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     *
     * 针对所有的元素重复以上的步骤，除了最后一个。
     *
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     * @param src
     * @return
     */

    private static void bubbleSort(int[] src){
        for (int i = 0; i < src.length; i++){
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;

            for (int j = src.length-1; j > i; j--){
                if (src[j] < src[j-1]){
                    flag = false;
                    int temp = src[j];
                    src[j] = src[j-1];
                    src[j-1] = temp;
                }
            }

            if (flag){
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] src = {2,3,4,5,21,2,4,5,71,1};

        bubbleSort(src);

        System.out.println(Arrays.toString(src));
    }
}
