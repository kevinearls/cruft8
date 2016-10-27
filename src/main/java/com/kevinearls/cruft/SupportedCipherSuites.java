package com.kevinearls.cruft;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 7/18/13
 * Time: 2:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class SupportedCipherSuites{
    public static void main(String[] args) throws IOException {
        ServerSocketFactory sslf = SSLServerSocketFactory.getDefault();
        ServerSocket socket = sslf.createServerSocket();
        System.out.println(">>>> Socket is a " + socket.getClass().getCanonicalName());
        if (socket instanceof SSLServerSocket) {
            SSLServerSocket sslSocket = (SSLServerSocket) socket;
            List<String> protocols = Arrays.asList(sslSocket.getEnabledProtocols());
            for (String protocol : protocols) {
                System.out.println(protocol);
            }
        }

        // "ssl.SocketFactory.provider"
        SocketFactory sf = SSLSocketFactory.getDefault();
        if (sf==null) {
            System.out.println(">>> SocketFactory is null");
        } else {
            System.out.println(">>>> SocketFactory is a " + sf.getClass().getCanonicalName());
        }

        Socket s = SSLSocketFactory.getDefault().createSocket("localhost", 61616);
        System.out.println(">>>> socket? " + s.getLocalPort());


    }
}
