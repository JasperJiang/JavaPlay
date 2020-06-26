package com.demo.io.Buffered;

import java.io.*;

public class BufferedReaderMain {

    public static void main(String[] args) {

        File file = new File("Java_IO/src/test.txt");
        File file1 = new File("Java_IO/src/Buffered/output.txt");

       try (BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(file1))){
           String line = "";
           while ((line = br.readLine()) != null){
               bw.write(line + "\n");
               System.out.println(line);
           }
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
