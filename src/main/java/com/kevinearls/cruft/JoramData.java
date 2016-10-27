package com.kevinearls.cruft;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 10/25/13
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class JoramData {

    /**
     * lines look like <testcase time="3.337" classname="org.objectweb.jtests.jms.conform.message.headers.MessageHeaderTest" name="testJMSDestination"/>
     *
     * split turns it into:
     * 0 : <testcase
     1 : time="3.337"
     2 : classname="org.objectweb.jtests.jms.conform.message.headers.MessageHeaderTest"
     3 : name="testJMSDestination"/>
     *
     * @throws IOException
     */
    public void go() throws IOException {
        Map<String, Integer> counts = new HashMap<String, Integer>();

        FileReader fr = new FileReader("/Users/kearls/sources/Cruft/resources/joram.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            if (!line.equals("")) {
                line = line.trim();
                String[] parts = line.split(" ");

                String className = parts[2];
                className = className.replace("classname=", "");
                className = className.replace("\"", "");

                Integer count = counts.get(className);
                if (count == null) {
                    counts.put(className, 1);
                } else {
                    count = count+1;
                    counts.put(className, count);
                }

            }

            line = br.readLine();
        }


        for (String className : counts.keySet()) {
            Integer count = counts.get(className);
            System.out.println(className + ": " + count);
        }
    }

    public static void main(String[] args) throws IOException{
        JoramData jd = new JoramData();
        jd.go();
    }
}
