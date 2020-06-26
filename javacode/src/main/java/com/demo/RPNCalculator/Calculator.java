package com.demo.RPNCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Stack;
import java.util.regex.Pattern;

public class Calculator {
    private Stack<Stack<Double>> stackBackup = new Stack<>();

    private Stack<Double> stack = new Stack<>();

    private boolean errorFlag = false;

    private void throwException(String operator,int position){
        System.out.println("operator " + operator + " (position: "+ position +"): insucient parameters");
    }

    private void backup(){
        stackBackup.add((Stack<Double>)stack.clone());
    }

    private void undo(int position){
        if (stackBackup.isEmpty()){
            throwException("undo",position);
            errorFlag = true;
            return;
        }
        stack = stackBackup.pop();
    }

    private void plus(int position){
        if (stack.size() < 2){
            throwException("+",position);
            errorFlag = true;
            return;
        }
        backup();
        Double a = stack.pop();
        Double b = stack.pop();
        stack.add(b+a);
    }

    private void minus(int position){
        if (stack.size() < 2){
            throwException("-",position);
            errorFlag = true;
            return;
        }
        backup();
        Double a = stack.pop();
        Double b = stack.pop();
        stack.add(b-a);
    }

    private void multiply(int position){
        if (stack.size() < 2){
            throwException("*",position);
            errorFlag = true;
            return;
        }
        backup();
        Double a = stack.pop();
        Double b = stack.pop();
        stack.add(b*a);
    }

    private void divide(int position){
        if (stack.size() < 2){
            throwException("/",position);
            errorFlag = true;
            return;
        }
        backup();
        Double a = stack.pop();
        Double b = stack.pop();
        stack.add(b/a);
    }

    private void sqrt(int position){
        if (stack.isEmpty()){
            throwException("sqrt",position);
            errorFlag = true;
            return;
        }
        backup();
        Double a = stack.pop();
        stack.add(Math.sqrt(a));
    }

    private void clear(){
        backup();
        stack.clear();
    }

    private void print(){
        DecimalFormat df = new DecimalFormat("#.##########");
        df.setRoundingMode(RoundingMode.FLOOR);
        Object[] doubles = stack.toArray();
        System.out.printf("stack: ");
        for (int i = 0; i < doubles.length; i++) {
            System.out.printf(df.format(doubles[i]) + " ");
        }
        System.out.println();
    }

    void caculator() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] curStr;
        Pattern pattern = Pattern.compile("[0-9]*(\\.[0-9]*)?");
        while (true) {
            String str;
//            System.out.print("input: ");
            str = br.readLine();
            if (Objects.equals(str, "")){
                continue;
            }
            curStr = str.trim().split("\\s+");
            if (Objects.equals(str, "exit")) break;
            for (int i = 0; i < curStr.length; i++) {
                int position = 2*i +1 ;
                String t = curStr[i];
                if (Objects.equals(t, "exit")){
                    break;
                }
                if (pattern.matcher(t).matches()){
                    backup();
                    stack.add(Double.valueOf(t));
                    continue;
                }

                switch (t.toLowerCase()){
                    case "+":
                        plus(position);
                        break;
                    case "-":
                        minus(position);
                        break;
                    case "*":
                        multiply(position);
                        break;
                    case "/":
                        divide(position);
                        break;
                    case "sqrt":
                        sqrt(position);
                        break;
                    case "clear":
                        clear();
                        break;
                    case "undo":
                        undo(position);
                        break;
                    default:
                        throwException(t,position);
                }
                if (errorFlag){
                    errorFlag = true;
                    break;
                }
            }
            print();
        }
    }
}