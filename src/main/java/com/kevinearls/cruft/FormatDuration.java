package com.kevinearls.cruft;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kearls
 * Date: 1/22/13
 * Time: 5:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormatDuration {


    public static String formatDuration(long duration) {
        int hours = (int) (duration / (60 * 60 * 1000));
        int minutes = (int) (duration / (60 * 1000)) % 60;
        int seconds = (int) (duration / 1000) % 60;
        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }


    public static void main(String[] args) {
        System.out.println("wtf");
        Long[]wtf = {5572145L, 0L, 629893L };
        List<Long> durations = Arrays.asList(wtf);

        for (Long duration : durations) {
            System.out.println(duration + ": " + formatDuration(duration));
        }

        String doh="testOfflineSubscriptionWithSelectorAfterRestart";
        System.out.println("Length: " + doh.length());


    }
}
