package com.kevinearls.cruft;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 1/14/13
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class TimeFormat {

    public static void main (String[] args)  throws Exception    {
        long start = System.currentTimeMillis();


        Thread.sleep(68 * 1000);

        long finish = System.currentTimeMillis();




        long millis = finish - start;
        long days = TimeUnit.MILLISECONDS.toDays(millis);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        System.out.println(days + ":" + hours + ":" + minutes + "." + seconds);

        String s = String.format("%2d:%2d.%2d", hours, minutes, seconds);
        System.out.println(s);
    }
    /*
    String.format("%d min, %d sec",
            TimeUnit.MILLISECONDS.toMinutes(millis),
            TimeUnit.MILLISECONDS.toSeconds(millis) -
            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
            );   */
}
