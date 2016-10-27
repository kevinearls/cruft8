package com.kevinearls.cruft;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

/**
 * Created by kearls on 2/26/14.
 */
public class ByteBufferConcatenateExample {
    protected static final Logger LOG = Logger.getLogger(ByteBufferConcatenateExample.class.getName());
    private final ByteBuffer magic = ByteBuffer.allocate(8);
    protected ByteBuffer currentBuffer;

    public static void main(String[] args) {
        ByteBuffer buffer1 = ByteBuffer.wrap("one".getBytes());
        ByteBuffer buffer2 = ByteBuffer.wrap("two".getBytes());

        System.out.println("One: " + new String(buffer1.array()));

        String wtf = new String(buffer2.array()) + new String("+more -stuff");
        ByteBuffer buffer3 = ByteBuffer.wrap(wtf.getBytes());
        System.out.println("3: " + new String(buffer3.array()));

        byte[] zero = {'0'};
        ByteBuffer buffer0 = ByteBuffer.wrap(zero);

        ByteBuffer big = ByteBuffer.allocate(100);
        int i=1;
        while (big.hasRemaining())  {
            big.put("1abcdefghi".getBytes());
            i++;
            LOG.info("Big limit " + big.limit() + " big.position " + big.position() + " big.remaining " + big.remaining() + " big.hasRemaining " + big.hasRemaining() );
        }
    }
}
