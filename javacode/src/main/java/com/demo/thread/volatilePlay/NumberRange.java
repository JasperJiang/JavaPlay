package com.demo.thread.volatilePlay;

public class NumberRange {
    private volatile int lower = 0;

    private volatile int upper = 10;

    public int getLower() {
        return lower;
    }

    public int getUpper() {
        return upper;
    }

    public void setLower(int value) {

        if (value > upper)

            throw new IllegalArgumentException("IllegalArgumentException");

        lower = value;

    }

    public void setUpper(int value) {

        if (value < lower)

            throw new IllegalArgumentException("IllegalArgumentException");

        upper = value;

    }


    private static volatile Object[] tabs = new Object[10];

    public static void main(String[] args) {
        tabs[0]=1;

        tabs=new Object[10];
    }

}
