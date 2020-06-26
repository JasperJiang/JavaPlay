package com.demo.io.Stream;

import java.io.*;

public class StreamMain {

    public static void main(String[] args) {
        File file = new File("Java_IO/src/test.txt");
        File file1 = new File("Java_IO/src/Stream/output.txt");


        try (FileInputStream fi = new FileInputStream(file);
             FileOutputStream fo = new FileOutputStream(file1)){
            byte[] b = new byte[fi.available()];

            fi.read(b);

            System.out.println(new String(b));

            fo.write(b);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
