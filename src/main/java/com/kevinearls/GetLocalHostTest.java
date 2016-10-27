package com.kevinearls;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by kearls on 6/24/14.
 */
public class GetLocalHostTest {
    public static void main(String[] args) {
        try {
            InetAddress localhost = InetAddress.getLocalHost();

            System.out.println(localhost.getCanonicalHostName());

            System.out.println(Inet4Address.getLoopbackAddress().toString());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
