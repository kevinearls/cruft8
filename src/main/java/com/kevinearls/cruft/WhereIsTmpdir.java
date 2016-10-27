package com.kevinearls.cruft;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 10/17/13
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class WhereIsTmpdir {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        properties.list(System.out);

        String tempDir = System.getProperty("java.io.tmpdir");
        System.out.println(">>> Tempdir " + tempDir);
    }
}
