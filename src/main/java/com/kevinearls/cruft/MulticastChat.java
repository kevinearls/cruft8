package com.kevinearls.cruft;

import java.io.*;
import java.net.*;

class ChatThread extends Thread {
    private MulticastSocket msocket;
    private DatagramPacket recv;

    public ChatThread(MulticastSocket msock ,InetAddress group_no , int Port_no) {
        msocket= msock;
        start();
    }

    public void run()  {
        byte[] buf = new byte[1000];
        try{
            for(;;) {
                // Handle the incoming data and print it to stdout.
                recv = new DatagramPacket(buf, buf.length);
                msocket.receive(recv);
                String tmp = new String(recv.getData(),0,recv.getLength());
                System.out.println("\n\nReceived: \""+ tmp + "\"\nMessage Length is: " + tmp.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            msocket.close();
        }
    }
}

public class MulticastChat {

    public static void main(String[] args) throws IOException
    {
        String inputString;
        int flag=1;
        int port;

        if (args.length != 2) {
            System.out.println("Usage: java mchat  mip port");
            System.exit(0);
        }

        port = Integer.parseInt(args[1]); // Convert the Port No. to an integer.
        InetAddress group = InetAddress.getByName(args[0]);
        if (!group.isMulticastAddress()) {
            System.out.println( "The address: " + group + " is not multicast address");
            System.exit(0);
        }

        try {
            MulticastSocket socket = new MulticastSocket(port);

            socket.setReuseAddress(false);
            socket.setLoopbackMode(false);
            socket.setTimeToLive(2);
            socket.joinGroup(group);

            System.out.println("Joined Group: " + args[0] + " Port:" + args[1]);

            // ChatThread will handle the incoming Data and print it out to STDN output.
            new ChatThread(socket,group,port);

            // Now read from STDN input and send the Data to the Group.
            BufferedReader myinput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Type anything followed by RETURN, or Ctrl+D to  terminate the program.");
            for(;;){
                inputString=myinput.readLine();

                if ( inputString==null) break; // User Hit Ctrl+D
                DatagramPacket dp = new DatagramPacket(inputString.getBytes(), inputString.length(),group, port);
                socket.send(dp);
            }

            System.out.println("Leaving the Group...");
            socket.leaveGroup(group);
            socket.close();
        } catch (Exception err){
            System.err.println("ERR: Can not join the group " + err);
            err.printStackTrace();
            System.exit(1);
        }
    }

}
