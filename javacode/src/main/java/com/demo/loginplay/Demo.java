package com.demo.loginplay;

import java.io.*;
import java.util.Scanner;

public class Demo {
    static Scanner scanner = new Scanner(System.in);
    static String user = null;
    public static void main(String[] args) throws IOException {
        while(true){
            //本地文件的位置
            File file = new File("Play/src/loginplay/user.txt");
            //true设置为可以继续写 而不是清除后再写
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);


            String key = null;x
            System.out.println("请选择功能:a登录  b注册  q退出");
            while(scanner.hasNext()){
                key=scanner.next();
                break;
            }
            switch(key){
                case("a"):login(br);break;
                case("b"):register(bw);break;
                case("q"):System.exit(0);
            }
        }


    }
    public static void login(BufferedReader br) throws IOException{
        boolean flag = false;
        String id = null;String password = null;
        System.out.println("请输入账号");
        while(scanner.hasNext()){
            id=scanner.next();
            break;
        }
        System.out.println("请输入密码");
        while(scanner.hasNext()){
            password=scanner.next();
            break;
        }
        String line = null;
        while((line=br.readLine())!=null&&flag==false){
            String[] str =line.split(" ");
            if(str[0].equals(id)){
                if(str[1].equals(password)){
                    flag=true;
                }
            }
        }
        if(flag==true){
            flag=false;
            System.out.println("登陆成功");
        }else{
            System.out.println("您的账号或者密码有误");
        }
        br.close();
    }
    public static void register(BufferedWriter bw) throws IOException{
        System.out.println("请输入账号:");
        while(scanner.hasNext()){
            user=scanner.next();
            bw.write(user);
            bw.flush();
            break;

        }
        System.out.println("请输入密码:");
        while(scanner.hasNext()){
            user=scanner.next();
            bw.write(" ");
            bw.write(user);
            bw.newLine();
            bw.flush();
            break;
        }
        bw.close();
    }
}
