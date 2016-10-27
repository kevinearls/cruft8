package com.kevinearls.cruft;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.Buffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 9/18/13
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class ByteBufferExample {

    private String createBigAssString() {
        int limit = (64 * 12) / 8;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < limit; i++) {
            builder.append("1234567-");
        }
        builder.append("*");

        System.out.println(">>>> Size: " + builder.toString().getBytes().length + " bytes");
        return builder.toString();
    }

    /*
    void pumpProtonToSocket() {
        try {
            int size = 1024 * 64;
            byte data[] = new byte[size];
            boolean done = false;
            while (!done) {
                int count = protonTransport.output(data, 0, size);
                if (count > 0) {
                    final Buffer buffer;
                    buffer = new Buffer(data, 0, count);
                    // System.out.println("writing: " + buffer.toString().substring(5).replaceAll("(..)", "$1 "));
                    amqpTransport.sendToAmqp(buffer);
                } else {
                    done = true;
                }
            }
            // System.out.println("write done");
        } catch (IOException e) {
            amqpTransport.onException(e);
        }
    }
    */

    private void go() throws Exception {
        String stuff = createBigAssString();
        ByteBuffer bb = ByteBuffer.wrap(stuff.getBytes());
        System.out.println(">>> bb.limit " + bb.limit());

        int maxLength = 39;
        byte data[] = new byte[maxLength];
        boolean done=false;

        try {
        while (!done) {
            int length = Math.min(maxLength, (bb.limit() - bb.position()));
            if (length != data.length) {
                System.out.println(">>>>>  Using final length of " + length);
                data = new byte[maxLength];
            }
            bb.get(data, 0, length);
            System.out.println(">>> [" + new String(data) + "]");

            if (bb.position() >= bb.limit()) {
                done = true;
            }
        }
        } catch (BufferUnderflowException e) {
            System.out.println("***** dying at limit " + bb.limit());
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) throws Exception {
        ByteBufferExample bbe=new ByteBufferExample();
        bbe.go();
    }
}
