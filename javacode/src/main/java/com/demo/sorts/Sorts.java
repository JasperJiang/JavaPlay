package com.demo.sorts;

public class Sorts {


    public static int[] bubbleSort1(int[] a){
        int i,j;

        int length = a.length;
        for (i=0; i<length;i++){
            for (j=1; j<length-i;j++){
                if (a[j-1] > a[j]){
                    int temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }

            }
        }
        return a;
    }


    public static int[] bubbleSort2(int[] a){
        int j, k = a.length;
        boolean flag = true;
        while (flag){
            flag=false;
            for(j=1; j<k; j++){
                if(a[j-1] > a[j]){
                    int temp;
                    temp = a[j-1];
                    a[j-1] = a[j];
                    a[j]=temp;
                    flag = true;
                }
            }
            k--;
        }
        return a;
    }



    public static void main(String[] args) {
        int[] a = {9,5,7,99,2,1,34,56,7,77,9,9,0,5,2,1};
        for (int i: Sorts.bubbleSort1(a)) {
            System.out.print(i+ " ");
        }
        System.out.println();
        for (int i: Sorts.bubbleSort2(a)) {
            System.out.print(i+ " ");
        }
    }

}
