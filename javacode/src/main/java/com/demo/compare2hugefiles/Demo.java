package com.demo.compare2hugefiles;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * 有两个文件A B分别有十亿个url，把其中相同的url找出来
 *
 * 先按照 （hashcode % n） 来拆分成n个文件A_xxx & B_xxx
 * 比较A_1 B_1 中的数据
 * 如果文件名不一样肯定不会有相同的url
 *
 */
public class Demo {

    public static final String SPILT_FILE_NAME_FORMATE = "%s_%s";

    /**
     * split file in to files by hash code
     * @param fileLocationStr raw File location
     * @param splitFileLocationDir splited file location dir
     * @param splitNumber split how many files
     * @param prefix fileTag
     */
    private void splitFile(String fileLocationStr, String splitFileLocationDir, int splitNumber, String prefix){
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileLocationStr)))){
            String temString = null;
            while ((temString = reader.readLine()) != null){
                // split file name eg. A_1
                String splitFileNameStr = String.format(SPILT_FILE_NAME_FORMATE, prefix, temString.hashCode()%splitNumber);
                File splitFile = Paths.get(splitFileLocationDir, splitFileNameStr).toFile();
                // for debug
                System.out.println("split file location: " + splitFile.toPath().toString());
                // write url to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(splitFile))){
                    writer.newLine();
                    writer.write(temString);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param splitFileLocationDir splited file location
     * @param prefixA tag1
     * @param prefixB tag2
     * @param splitNumber  splitNumber
     */
    private void compareFiles(String splitFileLocationDir, String prefixA, String prefixB, int splitNumber){
        for (int i = 0; i < splitNumber; i++) {
            // store the urls in fileA
            Set<String> file1Urls = new HashSet<>();
            // split fileA name
            String fileANameStr = String.format(SPILT_FILE_NAME_FORMATE, prefixA, i);
            // split fileB name
            String fileBNameStr = String.format(SPILT_FILE_NAME_FORMATE, prefixB, i);;
            File fileA = Paths.get(splitFileLocationDir, fileANameStr).toFile();
            File fileB = Paths.get(splitFileLocationDir, fileBNameStr).toFile();
            // for debug
//            System.out.println("split file A location: " + fileA.getPath() + " split file B location: "+ fileB.getPath());
            // if fileA & fileB both exist
            if (fileA.exists() && fileB.exists()){
                // add fileA's urls in to hash set
                try (BufferedReader reader = new BufferedReader(new FileReader(fileA))){
                    String temString = null;
                    while ((temString = reader.readLine()) != null){
                        file1Urls.add(temString);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // compare fileB's url with hashSet from fileA
                try (BufferedReader reader = new BufferedReader(new FileReader(fileB))){
                    String temString = null;
                    while ((temString = reader.readLine()) != null){
                        if (file1Urls.contains(temString)){
                            System.out.println(temString);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void main(String[] args) {
        String splitFileLocation = "/Users/Jack/Documents/Play_Ground/java_test/src/main/demo/split";
        int splitNumber = 1000;
        Demo demo = new Demo();
        // split File A
        demo.splitFile("/Users/Jack/Documents/Play_Ground/java_test/src/main/demo/testA.txt", splitFileLocation, splitNumber, "A");
        // split File B
        demo.splitFile("/Users/Jack/Documents/Play_Ground/java_test/src/main/demo/testB.txt", splitFileLocation, splitNumber, "B");
        //compare
        demo.compareFiles(splitFileLocation, "A", "B", splitNumber);
    }
}
