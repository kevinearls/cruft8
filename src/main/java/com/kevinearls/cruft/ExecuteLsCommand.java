package com.kevinearls.cruft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 4/29/13
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExecuteLsCommand {
    public static void main(String[] args) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        String[] wtf = {"ls", "-alF", "/Users/kearls/sources/apache/activemq/activemq-unit-tests/KahaDB/"};
        Process p = runtime.exec(wtf);
        p.waitFor();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
    }

}
