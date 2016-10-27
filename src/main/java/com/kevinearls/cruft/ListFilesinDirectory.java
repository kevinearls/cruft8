package com.kevinearls.cruft;

import java.io.File;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kearls on 3/19/14.
 */
public class ListFilesinDirectory {


    public void printFileSizes(String directoryName) throws Exception {
        NumberFormat nf = NumberFormat.getIntegerInstance();
        File root = new File(directoryName);
        File[] files = root.listFiles();
        List<File> fileList = Arrays.asList(files);
        for (File f : fileList) {
            if (!f.isDirectory())  {
                System.out.println(f.getAbsolutePath() + " Length: " + nf.format(f.length()));
            } else {
                printFileSizes(f.getAbsolutePath());
            }
        }
    }


    public static void main(String[] args) throws Exception {
        ListFilesinDirectory me = new ListFilesinDirectory();
        String directoryName = "/Users/kearls/sources/clean/apache/activemq/activemq-unit-tests/target/LevelDBCleanupTest";
        me.printFileSizes(directoryName);
    }
}
