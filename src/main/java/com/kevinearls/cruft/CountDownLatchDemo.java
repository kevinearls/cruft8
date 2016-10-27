package com.kevinearls.cruft;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 1/10/13
 * Time: 11:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class CountDownLatchDemo {

    private static final int N = 3;
    CountDownLatch startSignal = new CountDownLatch(1);
    CountDownLatch doneSignal = new CountDownLatch(N);

    private void doSomethingElse() throws Exception {
        System.out.println(">>>>> Start of doSomethingElse");
        Thread.sleep(3 * 1000);
        System.out.println(">>>> Exiting doSomethingElse");

    }

    private void wtf() throws Exception {
        for (int i = 0; i < N; ++i) // create and start threads
        {
            new Thread(new Worker(startSignal, doneSignal, i)).start();
        }

        doSomethingElse();            // don't let run yet
        startSignal.countDown();      // let all threads proceed
        doSomethingElse();
        doneSignal.await();           // wait for all to finish
    }


    public static void main(String[] args) throws Exception {
        CountDownLatchDemo cdld = new CountDownLatchDemo();
        cdld.wtf();

        System.out.println(">>>> Done");
    }

}

class Worker implements Runnable {
    private int id;

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    Worker(CountDownLatch startSignal, CountDownLatch doneSignal, int i) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.id = i;
    }

    public void run() {
        try {
            startSignal.await();
            doWork();
            System.out.println(">>>> Worker " + id + " waiting for done signal");
            doneSignal.countDown();
            System.out.println(">>>> Worker " + id + " done");
        } catch (InterruptedException ex) {} // return;
    }

    void doWork() {
        try {
            System.out.println(">>>> starting a Worker " + id);
            Thread.sleep(id * 2 * 1000);
            System.out.println(">>>> Worker " + id + " exiting doWOrk");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
