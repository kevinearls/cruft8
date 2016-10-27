package com.kevinearls.cruft;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 4/10/13
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleRegex {
    public static void main(String[] args) {
        String line = "Attempting  0th  connect to: tcp://Kevins-MacBook-Pro.local:61793";
        System.out.println(line.replaceAll("tcp://.*:[0-9][0-9][0-9][0-9][0-9]", "tcp://host:nnnnn"));
        String line2 = "Running task iteration 5 - Transport Connection to: tcp://192.168.1.4:61807";
        System.out.println(line2.replaceAll("tcp://.*:[0-9][0-9][0-9][0-9][0-9]", "tcp://host:nnnnn"));
    }
}
