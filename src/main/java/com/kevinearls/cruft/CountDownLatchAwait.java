package com.kevinearls.cruft;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 2/6/13
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class CountDownLatchAwait {


    public void go() {
        final CountDownLatch done = new CountDownLatch(1);
        final AtomicBoolean ok = new AtomicBoolean(true);
        boolean result=false;
        try {
            ok.set(done.await(30, TimeUnit.SECONDS)) ;
            System.out.println("completed normally");
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println("\tResult: " + result);
        System.out.println("\tOK? " + ok.get());

    }
    public static void main(String[] args) {
        CountDownLatchAwait me = new CountDownLatchAwait();
        for (int i=0; i <100; i++) {
            System.out.println(">>>> Iteration " + i);
            me.go();
        }

    }
}

/*
final CountDownLatch done = new CountDownLatch(1);
        final AtomicBoolean ok = new AtomicBoolean(true);
        final AtomicBoolean first = new AtomicBoolean(true);
        VMTransport t = ((ActiveMQConnection)connection).getTransport().narrow(VMTransport.class);
        t.setTransportListener(new TransportListener() {
            @Override
            public void onCommand(Object command) {
                // block first dispatch for a while so broker backs up, but other connection should be able to proceed
                if (first.compareAndSet(true, false)) {
                    try {
                        ok.set(done.await(35, TimeUnit.SECONDS));
                        LOG.info("Done waiting: " + ok.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
*/