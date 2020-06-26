package com.demo.datatypes;

import java.math.BigDecimal;

public class BigDecimalTest {

    public static double add(double d1, double d2) {
        // 进行加法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        BigDecimal subtract = b1.subtract(b2);
        System.out.println(subtract);
        return b1.add(b2).doubleValue();
    }

    // 进行四舍五入
    public static double round(double d,int len) {
        //操作
        BigDecimal b1 = new BigDecimal(d);
        BigDecimal b2 = new BigDecimal(1);
        // 任何一个数字除以1都是原数字
        // ROUND_HALF_UP是BigDecimal的一个常量，
        //表示进行四舍五入的操作
        return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String args[]){
        System.out.println("加法运算：" + BigDecimalTest.round(BigDecimalTest.add(10.345, 3.333), 1));
    }

}
