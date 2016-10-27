package com.kevinearls.cruft;

import java.net.InetAddress;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 10/23/13
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class LocalHost {
    public static void main(String[] args) throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println(addr.getHostName());

        InetAddress addr1 = InetAddress.getLoopbackAddress();
        System.out.println(addr1.getHostName());

    }
}
