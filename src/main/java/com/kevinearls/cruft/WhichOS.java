package com.kevinearls.cruft;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 3/15/13
 * Time: 10:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class WhichOS {

    public static void main(String[] args) {
        WhichOS me = new WhichOS();
        System.out.println(System.getProperty("os.name"));
    }
}
