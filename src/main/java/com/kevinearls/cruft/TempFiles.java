package com.kevinearls.cruft;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 2/26/13
 * Time: 10:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class TempFiles {
    public static void main(String[] args) {
        try {
            File ioUtilsTestDir = new File("ioUtilsTest");
            File tmpDir = new File("ioUtilsTest/tmp");
            boolean first = ioUtilsTestDir.mkdir();
            System.out.println("First? " + first);
            boolean second = tmpDir.mkdir();
            System.out.println("Second? " + second);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
