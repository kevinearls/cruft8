package com.kevinearls.cruft;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 3/15/13
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class KillAThreadExample {
    public static void main(String[] args) {
        Thread f = new Fud();
        f.start();
        System.out.println("Called start...");
        try {
            f.join(10 * 1000);
            f.interrupt();
        } catch (InterruptedException e) {
            System.out.println(">>>> In main, thread was interrupted");
        }
        System.out.println("Main finished");

    }
}

class Fud extends Thread {
    @Override
    public void run() {
        System.out.println("Starting at " + new Date().toString());
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            System.out.println("In Fud, thread interrupted");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println("Done at " + new Date().toString());
    }
}
