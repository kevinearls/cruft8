package com.kevinearls;

import java.util.TimerTask;


        import java.util.Date;
        import java.util.Timer;
        import java.util.TimerTask;

public class TimerExample extends TimerTask {

    @Override
    public void run() {
        System.out.println("The execution of task started at: " + new Date());
        // put task implementation here

        // put a sleep
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("The execution of task finished at: " + new Date());

    }

    public static void main(String[] args) {

        TimerTask task = new TimerExample();

        // true means : associated thread should run as a daemon
        Timer timer = new Timer(true);

        // Subsequent executions take place at approximately regular intervals,
        // separated by the specified period.
        timer.schedule(task, 0, 5000);
        System.out.println("The schedular has started");

        try {
            // Putting a sleep of 10000 ms so that the task can run twice as it
            // is scheduled to run every 500ms
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

